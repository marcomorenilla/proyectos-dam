# Crea una ventana que permita al usuario ingresar un nombre de usuario y una contraseña.
# Muestra un mensaje en la consola cuando el usuario presione el botón de iniciar sesión.
import sys

from PySide6.QtWidgets import QWidget, QLineEdit, QLabel, QVBoxLayout, QApplication, QPushButton


class Login(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Login")
        self.setGeometry(300, 300, 250, 150)

        vlayout = QVBoxLayout()

        self.user_edit = QLineEdit(self)
        self.user_pass = QLineEdit(self)
        user_label =QLabel(self,text='Introduce tu nombre:')
        pass_label =QLabel(self,text='Introduce tu password:')
        btn_login = QPushButton('Login',self)

        btn_login.clicked.connect(self.login)

        vlayout.addWidget(user_label)
        vlayout.addWidget(self.user_edit)
        vlayout.addWidget(pass_label)
        vlayout.addWidget(self.user_pass)
        vlayout.addWidget(btn_login)

        self.setLayout(vlayout)

    def login(self):
        print('Usuario [',self.user_edit.text(),'] ', 'Password [',self.user_pass.text(),']')



if __name__ == '__main__':
    app = QApplication(sys.argv)
    login = Login()
    login.show()
    app.exec()