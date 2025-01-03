# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton, QHBoxLayout, QVBoxLayout, QWidget, QFileDialog, QColorDialog, QFontDialog, QInputDialog


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


        # Creamos botones fila 1
        button_open = QPushButton('Abrir archivo')
        button_save =  QPushButton('Guardar archivo')
        button_color =  QPushButton('Elección de color')

        button_open.clicked.connect(self.open_file)
        button_save.clicked.connect(self.save_file)
        button_color.clicked.connect(self.color_selection)

        # Creamos botones fila 2
        button_font =  QPushButton('Selección de fuente')
        button_input =  QPushButton('Introducción de datos')
        

        button_font.clicked.connect(self.font_selection)
        button_input.clicked.connect(self.input_window)
        


        # Añadimos botones a layouts

        row_1.addWidget(button_open)
        row_1.addWidget(button_save)
        row_1.addWidget(button_color)

        row_2.addWidget(button_font)
        row_2.addWidget(button_input)
        

        # Conectamos QWidget con layouts y lo añadimos a QMainWindow
        container.setLayout(layout)
        self.setCentralWidget(container)



    def open_file(self):
        print('código para abrir archivo')

        # Creamos un QFileDialog y accedemos al método getOpenFileName
        # Devuelve una tupla con los archivos seleccionados y accedemos a ellos por su posición

        dialog = QFileDialog.getOpenFileName(self,caption='Abrir archivo...',
        dir='.',
        filter='Texto (*.txt);; PDF (*.pdf)',
        selectedFilter='PDF (*.pdf)')

        file_1 = dialog[0]
        # A partir de aquí trabajamos con el archivo

    def save_file(self):
        print('Código para salvar archivo')

        #Creamos un QFileDialog y accedemos al método getSaveFileName

        dialog = QFileDialog.getSaveFileName(self, caption='Guardar archivo...',
        dir='.',
        filter='Texto (*.txt);;PDF (*.pdf)',
        selectedFilter='PDF (*.pdf)')

        file_1 = dialog[0]
        print(file_1)
        

    def color_selection(self):
        print('Código para seleccionar color')
    
        color = QColorDialog.getColor()
        print(color)

        # TODO Averiguar como asignar color a botón
    
    def font_selection(self):
        print('Códio para selección de fuente')

        font =  QFontDialog.getFont()
        print(font)

        # TODO Averiguar como asignar fuente a botón

    
    def input_window(self):
        print('Código para introducción de datos')
        # Devuelve  una tupla[str, bool], el str se asigna a mes y el bool a selection

        month, selection = QInputDialog.getItem(self,'Meses', 'Meses',
        ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'])

        if selection:
            print(month)

   
        

if __name__ == '__main__':
    app = QApplication([])
    window = OtherDialogs()
    window.show()
    app.exec()
        