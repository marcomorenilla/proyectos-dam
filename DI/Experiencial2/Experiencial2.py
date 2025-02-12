import sys
import oracledb as cx_Oracle
import requests
from PySide6 import QtWidgets, QtGui, QtCore
from PySide6.QtCore import QThread, Signal
import Connection

def conectar_bd():
    """
    Establece la conexión con la base de datos y crea las tablas necesarias si no existen.
    """
    conexion = cx_Oracle.connect(user=Connection.DB_USER, password=Connection.DB_PASSWORD, dsn=Connection.DB_DSN, mode=cx_Oracle.SYSDBA)
    cursor = conexion.cursor()

    # Creación de la tabla de usuarios
    cursor.execute("""
        BEGIN
            EXECUTE IMMEDIATE 'CREATE TABLE usuarios_DI (
                id_usuario NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                nombre_usuario VARCHAR2(50) UNIQUE NOT NULL,
                password VARCHAR2(100) NOT NULL
            )';
        EXCEPTION
            WHEN OTHERS THEN
                IF SQLCODE != -955 THEN RAISE; END IF;  -- Ignorar error si la tabla ya existe
        END;
    """)

    # Creación de la tabla de favoritos
    cursor.execute("""
        BEGIN
            EXECUTE IMMEDIATE 'CREATE TABLE favoritos_DI (
                id_favorito NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                id_usuario NUMBER REFERENCES usuarios_DI(id_usuario),
                url_imagen VARCHAR2(255) NOT NULL
            )';
        EXCEPTION
            WHEN OTHERS THEN
                IF SQLCODE != -955 THEN RAISE; END IF;
        END;
    """)

    conexion.commit()
    return conexion


# Hilo para obtener imágenes desde Unsplash API
class ObtenerImagenesThread(QThread):
    imagenes_obtenidas = Signal(list)

    def run(self):
        api_key = '_rZqlRinW3KRyLKdNc9r41gk4Qn-6i4W3SHbRLqawSU'  # Clave de la API de Unsplash
        url = f'https://api.unsplash.com/photos?client_id={api_key}&per_page=6'

        try:
            respuesta = requests.get(url)
            respuesta.raise_for_status()  # Verifica si la solicitud fue exitosa
            imagenes = respuesta.json()
            urls_imagenes = [imagen['urls']['regular'] for imagen in imagenes]
            self.imagenes_obtenidas.emit(urls_imagenes)  # Emitir las URLs de imágenes
        except requests.exceptions.RequestException as e:
            print(f"Error al obtener las imágenes: {e}")
            self.imagenes_obtenidas.emit([])


# Ventana de Bienvenida
class VentanaBienvenida(QtWidgets.QWidget):
    def __init__(self, nombre_usuario):
        super().__init__()
        self.setWindowTitle("Bienvenido")
        self.setGeometry(100, 100, 600, 400)
        self.nombre_usuario = nombre_usuario

        layout = QtWidgets.QGridLayout()
        self.setLayout(layout)

        self.thread_imagenes = ObtenerImagenesThread()
        self.thread_imagenes.imagenes_obtenidas.connect(self.mostrar_imagenes)
        self.thread_imagenes.start()

    def mostrar_imagenes(self, imagenes):
        conexion = conectar_bd()
        cursor = conexion.cursor()
        cursor.execute(
            "SELECT url_imagen FROM favoritos_DI INNER JOIN usuarios_DI ON favoritos_DI.id_usuario = usuarios_DI.id_usuario WHERE usuarios_DI.nombre_usuario = :1",
            (self.nombre_usuario,))
        favoritos = {row[0] for row in cursor.fetchall()}
        conexion.close()

        for i, url in enumerate(imagenes):
            etiqueta = QtWidgets.QLabel(self)
            pixmap = QtGui.QPixmap()
            pixmap.loadFromData(requests.get(url).content)
            etiqueta.setPixmap(pixmap.scaled(150, 150))
            self.layout().addWidget(etiqueta, i // 3, (i % 3) * 2)

            boton_fav = QtWidgets.QPushButton("★")
            boton_fav.setCheckable(True)
            boton_fav.setChecked(url in favoritos)
            boton_fav.clicked.connect(lambda checked, u=url: self.guardar_favorito(u, checked))
            self.layout().addWidget(boton_fav, i // 3, (i % 3) * 2 + 1)

    def guardar_favorito(self, url, checked):
        conexion = conectar_bd()
        cursor = conexion.cursor()
        if checked:
            cursor.execute(
                "INSERT INTO favoritos_DI (id_usuario, url_imagen) VALUES ((SELECT id_usuario FROM usuarios_DI WHERE nombre_usuario = :1), :2)",
                (self.nombre_usuario, url))
        else:
            cursor.execute(
                "DELETE FROM favoritos_DI WHERE id_usuario = (SELECT id_usuario FROM usuarios_DI WHERE nombre_usuario = :1) AND url_imagen = :2",
                (self.nombre_usuario, url))
        conexion.commit()
        conexion.close()


# Ventana de Login
class VentanaLogin(QtWidgets.QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Login")
        self.setGeometry(100, 100, 300, 200)
        layout = QtWidgets.QVBoxLayout()

        # Campo para ingresar el nombre de usuario
        self.usuario = QtWidgets.QLineEdit(self)
        self.usuario.setPlaceholderText("Usuario")
        layout.addWidget(self.usuario)

        # Campo para ingresar la contraseña
        self.password = QtWidgets.QLineEdit(self)
        self.password.setPlaceholderText("Contraseña")
        self.password.setEchoMode(QtWidgets.QLineEdit.Password)
        layout.addWidget(self.password)

        # Botón para iniciar sesión
        self.boton_login = QtWidgets.QPushButton("Login")
        self.boton_login.clicked.connect(self.verificar_credenciales)
        layout.addWidget(self.boton_login)

        # Botón para registrarse
        self.boton_registro = QtWidgets.QPushButton("Registrarse")
        self.boton_registro.clicked.connect(self.abrir_registro)
        layout.addWidget(self.boton_registro)

        self.setLayout(layout)

    def verificar_credenciales(self):
        """Verifica si el usuario y la contraseña son correctos."""
        conexion = conectar_bd()
        cursor = conexion.cursor()
        cursor.execute("SELECT * FROM usuarios_DI WHERE nombre_usuario = :1 AND password = :2",
                       (self.usuario.text(), self.password.text()))
        usuario = cursor.fetchone()

        if usuario:
            self.ventana_bienvenida = VentanaBienvenida(usuario[1])
            self.ventana_bienvenida.show()
            self.close()
        else:
            QtWidgets.QMessageBox.warning(self, "Error", "Credenciales incorrectas")

        conexion.close()

    def abrir_registro(self):
        ventana_registro = VentanaRegistro()
        ventana_registro.show()
        ventana_registro.exec()


# Ventana de Registro
class VentanaRegistro(QtWidgets.QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Registro")
        self.setGeometry(200, 200, 300, 200)
        layout = QtWidgets.QVBoxLayout()

        self.usuario = QtWidgets.QLineEdit(self)
        self.usuario.setPlaceholderText("Usuario")
        layout.addWidget(self.usuario)

        self.password = QtWidgets.QLineEdit(self)
        self.password.setPlaceholderText("Contraseña")
        self.password.setEchoMode(QtWidgets.QLineEdit.Password)
        layout.addWidget(self.password)

        self.boton_registrar = QtWidgets.QPushButton("Registrar")
        self.boton_registrar.clicked.connect(self.registrar_usuario)
        layout.addWidget(self.boton_registrar)

        self.setLayout(layout)

    def registrar_usuario(self):
        conexion = conectar_bd()
        cursor = conexion.cursor()
        try:
            cursor.execute("INSERT INTO usuarios_DI (nombre_usuario, password) VALUES (:1, :2)",
                           (self.usuario.text(), self.password.text()))
            conexion.commit()
            QtWidgets.QMessageBox.information(self, "Éxito", "Usuario registrado correctamente")
            self.close()
        except cx_Oracle.IntegrityError:
            QtWidgets.QMessageBox.warning(self, "Error", "El usuario ya existe")
        conexion.close()
# Main
if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    login = VentanaLogin()
    login.show()
    sys.exit(app.exec())
