# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QMainWindow
from PySide6.QtGui import QAction, QKeySequence

class Menu (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Menú con acciones')

        #Creamos barra de menú 
        #Añadimos opciones de Print a menú
        #El símbolo <<&>> facilita el acceso mediante alt + <inicial>

        menu_bar = self.menuBar()
        option = menu_bar.addMenu('&Print')

        #Definimos acción
        #Asignamos atajo
        #Definimos función que tiene la acción en método aparte

        action = QAction('&Imprimir por consola',self)
        action.setShortcut(QKeySequence('Ctrl+p'))
        action.triggered.connect(self.consoleprint)

        #Añadimos la opción a menú
        option.addAction(action)

    def consoleprint(self):
        print('Acción realizada')

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication()

    ventana = Menu()
    ventana.show()

    app.exec()