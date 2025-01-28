import sqlite3
import sys
from base64 import b64encode

import bcrypt
from PySide6.QtWidgets import QMainWindow, QApplication, QVBoxLayout, QLabel, QLineEdit, QPushButton, QWidget, \
    QMessageBox, QTableWidget, QTableWidgetItem, QAbstractItemView, QCheckBox


def crear_base_datos():

    conexion = sqlite3.connect("usuarios.db")
    cursor = conexion.cursor()
    cursor.execute('''
    CREATE TABLE IF NOT EXISTS usuarios(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        usuario TEXT NOT NULL UNIQUE,
        contrasena TEXT NOT NULL,
        es_admin INTEGER NOT NULL DEFAULT 0
    )
    ''')
    conexion.commit()
    conexion.close()

class LoginForm(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Inicio de Sesion")
        self.setGeometry(100,100,300,250)


        ## LAYOUT PRINCIPAL EN VERTICAL
        main_layout = QVBoxLayout()
        title = QLabel("Inicio de Sesion")
        main_layout.addWidget(title)

        # Widget principal
        central_widget = QWidget()
        central_widget.setLayout(main_layout)
        self.setCentralWidget(central_widget)

        ## ENTRADA DE TEXTO PARA EL USUARIO
        self.username_input = QLineEdit()
        self.username_input.setPlaceholderText("Nombre de usuario")
        main_layout.addWidget(self.username_input)

        ## ENTRADA DE TEXTO PARA LA CONTRASEÑA
        self.password_input = QLineEdit()
        self.password_input.setPlaceholderText("Contraseña")
        self.password_input.setEchoMode(QLineEdit.Password)
        main_layout.addWidget(self.password_input)

        ## BOTON PARA INICIO DE SESION
        login_button = QPushButton("Iniciar sesion")
        login_button.clicked.connect(self.iniciar_sesion)
        main_layout.addWidget(login_button)

        ## BOTON PARA PASAR A LA VENTANA DE REGISTRO
        register_button = QPushButton("Registrar")
        register_button.clicked.connect(self.volver_a_registro)
        main_layout.addWidget(register_button)

        ## ESTABLECER EL LAYOUT PRINCIPAL
        central_widget = QWidget()
        central_widget.setLayout(main_layout)
        self.setCentralWidget(central_widget)

    def volver_a_registro(self):
        self.login = RegisterForm()
        self.login.show()
        self.close()

    def iniciar_sesion(self):
        usuario = self.username_input.text()
        contrasena = self.password_input.text()

        if not usuario or not contrasena:
            QMessageBox.warning(self, "Error", "Todos los campos son obligatorios")

        conexion = sqlite3.connect("usuarios.db")
        cursor = conexion.cursor()
        cursor.execute("SELECT contrasena, es_admin FROM usuarios WHERE usuario = ?", (usuario, ))
        resultado = cursor.fetchone()
        conexion.close()

        if resultado and bcrypt.checkpw(contrasena.encode(), resultado[0]):
            self.bienvenida = WelcomeForm(usuario, resultado[1])
            self.bienvenida.show()
            self.close()
        else:
            QMessageBox.critical(self, "Error", "Usuario o contraseña incorrectos")
            self.username_input.clear()
            self.password_input.clear()

class RegisterForm(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Registro")
        self.setGeometry(100,100,400,400)

        ## LAYOUT PRINCIPAL EN VERTICAL
        main_layout = QVBoxLayout()
        title = QLabel("Registro")
        main_layout.addWidget(title)

        ## ENTRADA DE TEXTO PARA EL USUARIO
        self.username_input = QLineEdit()
        self.username_input.setPlaceholderText("Nombre de usuario")
        main_layout.addWidget(self.username_input)

        ## ENTRADA DE TEXTO PARA LA CONTRASEÑA
        self.password_input = QLineEdit()
        self.password_input.setPlaceholderText("Contraseña")
        self.password_input.setEchoMode(QLineEdit.Password)
        main_layout.addWidget(self.password_input)

        ## CHECK BOX PARA MARCAR SI ES ADMINISTRADOR

        self.admin_checkbox = QCheckBox("¿Es administrador?")
        main_layout.addWidget(self.admin_checkbox)

        ## BOTON PARA REGISTRO DE USUARIO
        register_button = QPushButton("Registrar")
        register_button.clicked.connect(self.registrar_usuario)
        main_layout.addWidget(register_button)

        ## BOTON PARA PASAR A LA VENTANA DE REGISTRO
        back_button = QPushButton("Iniciar sesion")
        back_button.clicked.connect(self.volver_a_login)
        main_layout.addWidget(back_button)

        ## ESTABLECER EL LAYOUT PRINCIPAL
        central_widget = QWidget()
        central_widget.setLayout(main_layout)
        self.setCentralWidget(central_widget)

    def volver_a_login(self):
        self.login = LoginForm()
        self.login.show()
        self.close()

    def registrar_usuario(self):
        usuario = self.username_input.text()
        contrasena = self.password_input.text()
        es_admin = 1 if self.admin_checkbox.isChecked() else 0

        if not usuario or not contrasena:
            QMessageBox.warning(self, "Error", "Todos los campos son obligatorios")

        else:
            ## ENCRIPTA LA CONTRASEÑA
            contrasena_encriptada = bcrypt.hashpw(contrasena.encode(), bcrypt.gensalt())
            try:
                conexion = sqlite3.connect("usuarios.db")
                cursor = conexion.cursor()
                cursor.execute("INSERT INTO usuarios(usuario, contrasena,es_admin) VALUES (?,?,?)", (usuario, contrasena_encriptada, es_admin))
                conexion.commit()
                conexion.close()
                QMessageBox.information(self, "Exito", "Usuario ha sido registrado")
                self.volver_a_login()
            except sqlite3.IntegrityError:
                QMessageBox.critical(self, "Error", "El usuario ya existe")

class WelcomeForm(QMainWindow):
    def __init__(self, usuario2, es_admin):
        super().__init__()
        self.setWindowTitle("Bienvenida")
        self.setGeometry(100,100,600,400)

        self.usuario2 = usuario2
        self.es_admin = es_admin

        ## LAYOUT PRINCIPAL
        main_layout = QVBoxLayout()

        ## ETIQUETA PRINCIPAL
        self.welcome_label = QLabel(f"¡Bienvenido,{usuario2}!")
        main_layout.addWidget(self.welcome_label)

        ## PINTAR LA TABLA

        self.table = QTableWidget()
        self.table.setColumnCount(2)
        self.table.setHorizontalHeaderLabels(["Usuario", "Contraseña_Encriptada"])
        self.table.setSelectionBehavior(QAbstractItemView.SelectRows)
        self.table.setSelectionMode(QAbstractItemView.SingleSelection)
        main_layout.addWidget(self.table)
        self.cargar_usuarios()

        if es_admin == 1:
            ## BOTON PARA ELIMINAR UNA CUENTA

            delete_button = QPushButton("Borrar cuenta seleccionada")
            delete_button.clicked.connect(self.borrar_cuenta)
            main_layout.addWidget(delete_button)

        cerrar_sesion = QPushButton("Cerrar sesión")
        cerrar_sesion.clicked.connect(lambda : self.close())
        main_layout.addWidget(cerrar_sesion)
        ## ESTABLECER EL LAYOUT PRINCIPAL COMO EL CENTRAL

        central_widget = QWidget()
        central_widget.setLayout(main_layout)
        self.setCentralWidget(central_widget)

    def cargar_usuarios(self):
        conexion = sqlite3.connect("usuarios.db")
        cursor = conexion.cursor()
        cursor.execute("SELECT usuario, contrasena FROM usuarios")
        usuarios = cursor.fetchall()
        conexion.close()
        self.table.setRowCount(len(usuarios))

        for row, (usuario, contrasena) in enumerate(usuarios):
            self.table.setItem(row, 0, QTableWidgetItem(usuario))
            contrasena_legible = b64encode(contrasena).decode("utf-8")
            self.table.setItem(row, 1, QTableWidgetItem(contrasena_legible))

    def borrar_cuenta(self):
        selected_row = self.table.currentRow()
        if selected_row == -1:
            QMessageBox.warning(self, "Error", "Selecciona una cuenta para borrarla")
        else:
            usuario = self.table.item(selected_row, 0).text()
            respuesta = QMessageBox.question(
                self,
                "Confirmacion",
                f"¿Estas seguro de borrar la cuenta de '{usuario}'",
                QMessageBox.Yes | QMessageBox.No
            )
            if respuesta == QMessageBox.Yes:
                conexion = sqlite3.connect("usuarios.db")
                cursor = conexion.cursor()
                cursor.execute("DELETE FROM usuarios WHERE usuario = ?", (usuario,))
                conexion.commit()
                conexion.close()
                QMessageBox.information(self, "Exito", "Cuenta eliminada con exito")
                self.cargar_usuarios()
                if usuario == self.usuario2:
                    QMessageBox.information(self, "Exito", "Sesion finalizada")
                    self.login = LoginForm()
                    self.login.show()
                    self.close()

if __name__ == "__main__":
    crear_base_datos()
    app = QApplication(sys.argv)
    ventana = RegisterForm()
    ventana.show()
    sys.exit(app.exec())



    def cargar_usuarios(self):
        """Carga todos los usuarios en la tabla."""
        conexion = sqlite3.connect("usuarios.db")
        cursor = conexion.cursor()
        cursor.execute("SELECT usuario, contrasena, es_admin FROM usuarios")
        usuarios = cursor.fetchall()
        conexion.close()

        self.table.setRowCount(len(usuarios))
        for row, (usuario, contrasena, es_admin) in enumerate(usuarios):
            self.table.setItem(row, 0, QTableWidgetItem(usuario))
            contrasena_legible = b64encode(contrasena).decode("utf-8")
            self.table.setItem(row, 1, QTableWidgetItem(contrasena_legible))
            self.table.setItem(row, 2, QTableWidgetItem("Sí" if es_admin else "No"))

    def borrar_cuenta(self):
        """Elimina la cuenta seleccionada de la base de datos, solo si es administrador."""
        selected_row = self.table.currentRow()
        if selected_row == -1:
            QMessageBox.warning(self, "Error", "Selecciona una cuenta para borrar")
            return

        usuario = self.table.item(selected_row, 0).text()
        respuesta = QMessageBox.question(
            self,
            "Confirmación",
            f"¿Estás seguro de que deseas borrar la cuenta de '{usuario}'?",
            QMessageBox.Yes | QMessageBox.No
        )
        if respuesta == QMessageBox.Yes:
            conexion = sqlite3.connect("usuarios.db")
            cursor = conexion.cursor()
            cursor.execute("DELETE FROM usuarios WHERE usuario = ?", (usuario,))
            conexion.commit()
            conexion.close()

            QMessageBox.information(self, "Éxito", "Cuenta eliminada correctamente")
            self.cargar_usuarios()

    def salir(self):
        """Cierra la sesión y regresa al inicio de sesión."""
        self.login = LoginForm()
        self.login.show()
        self.close()


if __name__ == "__main__":
    # Crear la base de datos si no existe
    crear_base_datos()

    # Iniciar la aplicación
    app = QApplication(sys.argv)
    ventana = LoginForm()
    ventana.show()
    sys.exit(app.exec())
