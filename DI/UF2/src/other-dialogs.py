# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton, QHBoxLayout, QVBoxLayout, QWidget

class OtherDialogs (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Other Dialogs')
        self.setMinimumHeight(200)
        self.setMinimumWidth(200)

        # Creamos contenedor
        container = QWidget()

        # Definimos layouts
        layout = QVBoxLayout()
        row_1 = QHBoxLayout()
        row_2 =  QHBoxLayout()

        layout.addLayout(row_1)
        layout.addLayout(row_2)


        # Creamos botones
        button_open = QPushButton('Abrir archivo')
        button_save =  QPushButton('Guardar archivo')
        button_color =  QPushButton('Elige color')

        button_open.clicked.connect(self.open_file)
        button_save.clicked.connect(self.save_file)
        button_color.clicked.connect(self.color_selection)


        # Añadimos botones a layouts

        row_1.addWidget(button_open)
        row_1.addWidget(button_save)
        row_1.addWidget(button_color)

        # Conectamos QWidget con layouts y lo añadimos a QMainWindow
        container.setLayout(layout)
        self.setCentralWidget(container)



    def open_file(self):
        print('código para abrir archivo')

    def save_file(self):
        print('Código para salvar archivo')

    def color_selection(self):
        print('Código para seleccionar color')

if __name__ == '__main__':
    app = QApplication([])
    window = OtherDialogs()
    window.show()
    app.exec()
        