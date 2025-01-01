# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso
# Clase de principal + ejecuciones

from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton
from custom_dialog import CustomDialog

class MainWindow(QMainWindow):

    def __init__(self):
        super().__init__()
        self.setWindowTitle('MainWindow')

        # Añadimos botón

        button =  QPushButton('Pulsa aquí')
        button.clicked.connect(self.show_dialog)
        
        self.setCentralWidget(button)

    def show_dialog(self):

        # Creamos el dialogo y almacenamos el resultado
        
        dialog_window = CustomDialog(self)
        result = dialog_window.exec()
        
        print('Pulsado botón: ', result)

        if result:
            print('Pulsado Ok')
        else:
            print('Pulsado Cancel')

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication([])
    window = MainWindow()
    window.show()
    app.exec()
        