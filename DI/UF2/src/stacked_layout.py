# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QWidget, QLabel, QPushButton, QVBoxLayout, QHBoxLayout, QMainWindow, QStackedLayout

class StackedLayout(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('StackedLayout')

        #Creamos layout y ventana
        layout_horizontal = QHBoxLayout()
        widget = QWidget()

        #Añadimos layout a ventana y ventana a componente principal
        widget.setLayout(layout_horizontal)
        self.setCentralWidget(widget)

        #Metemos atributo pila como StackedLayout y le añadimos 4 capas
        self.pila  =  QStackedLayout()
        self.pila.addWidget(QLabel('Capa 1'))
        self.pila.addWidget(QLabel('Capa 2'))
        self.pila.addWidget(QLabel('Capa 3'))
        self.pila.addWidget(QLabel('Capa 4'))

        #Metemos 4 botones en layout vertical
        #Creamos layout
        #Creamos botones
        #Añadimos función a botones
        #Añadimos botones a layout

        layout_vertical = QVBoxLayout()

        boton_capa1 = QPushButton('Capa 1')
        boton_capa2 = QPushButton('Capa 2')
        boton_capa3 = QPushButton('Capa 3')
        boton_capa4 = QPushButton('Capa 4')

        boton_capa1.clicked.connect(self.capa1_activar)
        boton_capa2.clicked.connect(self.capa2_activar)
        boton_capa3.clicked.connect(self.capa3_activar)
        boton_capa4.clicked.connect(self.capa4_activar)

        layout_vertical.addWidget(boton_capa1)
        layout_vertical.addWidget(boton_capa2)
        layout_vertical.addWidget(boton_capa3)
        layout_vertical.addWidget(boton_capa4)

        #Añadimos pila como 1er elemento de layout_horizontal
        layout_horizontal.addLayout(self.pila)

        #Añadimos layout de botones como 2o elemento de layout_horizontal
        layout_horizontal.addLayout(layout_vertical)

    #Implementamos funciones de botones
    def capa1_activar(self):
        self.pila.setCurrentIndex(0)

    def capa2_activar(self):
        self.pila.setCurrentIndex(1)

    def capa3_activar(self):
        self.pila.setCurrentIndex(2)

    def capa4_activar(self):
        self.pila.setCurrentWidget(self.pila.widget(3))

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication()

    ventana = StackedLayout()
    ventana.show()

    app.exec()


