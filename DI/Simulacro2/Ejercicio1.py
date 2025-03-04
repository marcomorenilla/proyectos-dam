import sys

from PySide6.QtWidgets import QWidget, QVBoxLayout, QLineEdit, QPushButton, QLabel, QApplication


class Edad(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Edad')
        self.setGeometry(300, 300, 250, 150)

        layout = QVBoxLayout()
        self.setLayout(layout)

        self.line_edit = QLineEdit(self)
        self.line_edit.setPlaceholderText('Introduce tu edad')
        layout.addWidget(self.line_edit)

        btn_calcular = QPushButton('Calcular')
        btn_calcular.clicked.connect(self.calculate)
        layout.addWidget(btn_calcular)

        self.lbl_edad = QLabel(self)
        layout.addWidget(self.lbl_edad)

    def calculate(self):
        edad = int(self.line_edit.text())
        if edad < 0:
            self.lbl_edad.setText("No existen edades negativas")
        elif edad < 18:
            self.lbl_edad.setText("Eres menor de edad")
            print(self.lbl_edad.text())
        else:
            self.lbl_edad.setText("Eres mayor de edad")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Edad()
    window.show()
    sys.exit(app.exec())