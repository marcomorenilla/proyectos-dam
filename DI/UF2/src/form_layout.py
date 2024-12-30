# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

#QLineEdit contiene una línea de entrada de texto
#QTextEdit contiene un bloque de entrada de texto

from PySide6.QtWidgets import QApplication, QFormLayout, QWidget, QLabel, QLineEdit, QSpinBox, QDoubleSpinBox, QMainWindow, QPushButton

class FormLayout (QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('FormLayout')

        #Creamos layout y ventana
        layout = QFormLayout()
        widget = QWidget()

        #Añadimos layout a ventana y ventana a QMainWindow
        widget.setLayout(layout)
        self.setCentralWidget(widget)

        #Metemos elementos de formulario en filas al Layout
        layout.addRow(QLabel('Texto: '), QLineEdit())
        layout.addRow(QLabel('Número entero: '), QSpinBox())
        layout.addRow(QLabel('Número decimal: '),QDoubleSpinBox())
        layout.addRow(QPushButton('Enviar'), QPushButton('Cancelar'))

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication()

    ventana = FormLayout()
    ventana.show()

    app.exec()

