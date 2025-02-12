import sys
from PySide6.QtGui import QAction
from PySide6.QtWidgets import QMainWindow, QApplication, QVBoxLayout, QWidget, QPushButton, QDialog, QLineEdit, \
    QMessageBox, QMenuBar, QMenu

class Contactos(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Contactos")
        self.setGeometry(300, 300, 400, 200)
        self.lista_contactos = ListaContactos()

        # Central widget
        central_widget = QWidget(self)
        self.setCentralWidget(central_widget)

        # Layout
        layout = QVBoxLayout()
        central_widget.setLayout(layout)

        # Botón de agregar usuario
        btn_usuario = QPushButton("Usuario")
        layout.addWidget(btn_usuario)
        btn_usuario.clicked.connect(self.action_clicked)

        # Menú
        menu_bar = QMenuBar(self)
        self.setMenuBar(menu_bar)

        #TODO implementar funcionalidades menu bar

        # Opción añadir
        add_contacto = QAction("Añadir contacto",self)
        menu_bar.addAction(add_contacto)
        add_contacto.triggered.connect(self.action_clicked)

        #Opción modificar
        modificar_contacto =QAction("Modificar contacto",self)
        menu_bar.addAction(modificar_contacto)
        modificar_contacto.triggered.connect(self.action_clicked)

        #Opción eliminar
        eliminar_contacto = QAction("Eliminar contacto",self)
        menu_bar.addAction(eliminar_contacto)
        eliminar_contacto.triggered.connect(self.action_clicked)



    # Lanza pantalla para registrar personas en la lista
    def action_clicked(self):
        btn = self.sender()
        if btn.text() == "Usuario":
            self.form = UserForm(self.lista_contactos)
            self.form.show()
        elif btn.text() == "Añadir contacto":
            QMessageBox.information(self,"Añadiendo contacto","Contacto añadido")
        elif btn.text() == "Modificar contacto":
            QMessageBox.information(self, "Modificando", "Usuario modificado")
        else:
            QMessageBox.information(self,"Eliminando contacto", "Contacto eliminado")


class UserForm(QWidget):
    def __init__(self, lista_contactos):
        super().__init__()
        self.setWindowTitle("Formulario usuario")
        self.lista = lista_contactos

        # Layout
        layout = QVBoxLayout()
        self.setLayout(layout)

        # Edit
        self.txt_usuario = QLineEdit(self)
        self.txt_usuario.setPlaceholderText("Introduce tu nombre")
        layout.addWidget(self.txt_usuario)

        # btn
        btn_submit = QPushButton("Registrar")
        layout.addWidget(btn_submit)
        btn_submit.clicked.connect(self.registrar)

    def registrar(self):
        nombre = self.txt_usuario.text()

        if not nombre:
            QMessageBox.warning(self, "Error", "Introduce un nombre")
        else:
            self.lista.addContacto(nombre)
            print(self.lista.lista)
            QMessageBox.information(self, "Info", "Usuario registrado correctamente")
            self.close()


class ListaContactos():
    def __init__(self):
        self.lista = list()

    def addContacto(self, name):
        self.lista.append(name)

    def get_list(self):
        print(self.lista)
        return self.lista


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Contactos()
    window.show()
    sys.exit(app.exec())
