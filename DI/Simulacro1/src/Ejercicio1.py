import sys

from PySide6.QtWidgets import QWidget, QLineEdit, QVBoxLayout, QPushButton, QLabel, QApplication


class Ejercicio1(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Ejercicio 1')
        self.setGeometry(300, 300, 250, 150)

        self.layout = QVBoxLayout()
        self.setLayout(self.layout)

        self.edit_nombre = QLineEdit(self)
        self.edit_nombre.setPlaceholderText(' Introduce tu Nombre')
        self.layout.addWidget(self.edit_nombre)

        self.btn_nombre = QPushButton("Pulsa aquí")
        self.btn_nombre.clicked.connect(self.pulsar)
        self.layout.addWidget(self.btn_nombre)

        self.lbl_nombre = QLabel(self)
        self.layout.addWidget(self.lbl_nombre)

    def pulsar(self):
        nombre = self.edit_nombre.text()
        self.lbl_nombre.setText(f"Hola {nombre}")


if __name__ == '__main__':
    app = QApplication([])
    ex = Ejercicio1()
    ex.show()
    sys.exit(app.exec())
