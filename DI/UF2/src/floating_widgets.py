# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

import platform, os
from PySide6.QtCore import Qt
from PySide6.QtGui import QAction, QIcon, QKeySequence
from PySide6.QtWidgets import QApplication, QMainWindow, QToolBar, QLabel, QDockWidget, QTextEdit

class FloatingWidgets (QMainWindow):

    def __init__(self):
        super().__init__()
        self.setWindowTitle('FloatingWidgets')

        # Barra de menú

        menu_bar = self.menuBar()
        menu_menu = menu_bar.addMenu('&Menu')
        
        #Icono
        icon_path = os.path.join(os.path.dirname(__file__), 'command-line.png')

        # Acción

        print_action = QAction(QIcon(icon_path),'&Print',self)
        print_action.setWhatsThis('Imprime un mensaje por consola')
        print_action.setShortcut(QKeySequence('Ctrl+p'))
        print_action.setStatusTip('Mensaje de barra de estado')
        print_action.triggered.connect(self.print_message)
        
        # Acción en barra de menú

        menu_menu.addAction(print_action)

        # Barra de herramientas

        toolbar = QToolBar('Barra de herramientas')
        toolbar.addAction(print_action)

        self.addToolBar(toolbar)

        # Barra de estado

        statusbar = self.statusBar()
        statusbar.addPermanentWidget(QLabel(platform.system()))

        # TODO: Añadir el resto del ejemplo


    def print_message(self):
        print('Mensaje impreso por print_action')

### Fin de clase ###

if __name__=='__main__':
    app = QApplication([])

    app.setAttribute(Qt.AA_DontShowIconsInMenus)

    window = FloatingWidgets()
    window.show()

    app.exec()