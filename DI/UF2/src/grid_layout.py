# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QWidget, QPushButton, QGridLayout, QMainWindow

class GridLayout (QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('GridLayout')

        layout =  QGridLayout()

        layout.addWidget(QPushButton('Uno'),0,0)
        layout.addWidget(QPushButton('Dos'),0,1)
        layout.addWidget(QPushButton('Tres'),0,2)
        layout.addWidget(QPushButton('Cuatro'),1,0,1,2)
        layout.addWidget(QPushButton('Cinco'),1,2,1,1)
        layout.addWidget(QPushButton('Seis'),2,0,1,3)

        widget = QWidget()
        widget.setLayout(layout)

        self.setCentralWidget(widget)

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication()

    ventana = GridLayout()
    ventana.show()

    app.exec()