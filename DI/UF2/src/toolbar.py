# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

import os
from PySide6.QtWidgets import QApplication, QMainWindow, QToolBar
from PySide6.QtGui import QAction, QKeySequence, QIcon

class ToolBar(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle('Toolbar')

        #Crear barra de menú
        #Añadir opciones a la barra

        menu_bar = self.menuBar()
        option_menu = menu_bar.addMenu('&Menu')
        
        #Creamos acción con icono
        #Agregamos descripción
        #Incluímos atajo de teclado
        #Funcionalidad a la acción

        icon_path = os.path.join(os.path.dirname(__file__),'command-line.png')
        action = QAction(QIcon(icon_path),'&Imprimir por consola',self)
        action.setWhatsThis('Imprime texto por consola')

        action.setShortcut(QKeySequence('Ctrl+p'))
        action.triggered.connect(self.icon_action)

        #Metemos la acción en la opción menú

        option_menu.addAction(action)

        #Creamos ToolBar
        toolbar = QToolBar('Barra de herramientas')

        #Metemos acción en ToolBar
        toolbar.addAction(action)

        #Añadimos ToolBar a MainWindow
        self.addToolBar(toolbar)



    def icon_action(self):
        print('Estás pulsando el icono para imprimir este texto por consola')

### Fin de clase ###

if __name__== '__main__':
    app = QApplication()
    ventana  = ToolBar()
    ventana.show()
    app.exec()