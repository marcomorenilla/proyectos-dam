# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso
from PySide6.QtWidgets import QApplication, QHBoxLayout, QPushButton, QWidget

class VentanaHorizontal(QWidget):
    
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Layout horizontal')

        layout = QHBoxLayout()

        layout.addWidget(QPushButton('Uno'))
        layout.addWidget(QPushButton('Dos'))
        layout.addWidget(QPushButton('Tres'))
        layout.addWidget(QPushButton('Cuatro'))

        self.setLayout(layout)

######## Fin de la clase ######## 

if __name__ == '__main__':
    app =  QApplication()
    
    ventana =  VentanaHorizontal()
    ventana.show()

    app.exec()