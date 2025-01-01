# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

import os, platform
from PySide6.QtWidgets import QApplication, QMainWindow, QToolBar, QLabel
from PySide6.QtGui import QAction, QIcon, QKeySequence
from PySide6.QtCore import Qt

class StatusBar (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('StatusBar')

        # Creamos barra de menú
        # Añadimos acción de menú a la barra

        menu_bar = self.menuBar()
        menu_menu = menu_bar.addMenu('&Menu')

        # Definimos ruta al icono

        icon_path = os.path.join(os.path.dirname(__file__),'command-line.png')

        # Creamos acción

        action_menu = QAction(QIcon(icon_path), '&Print', self)
        action_menu.setWhatsThis('Al pulsar se imprime por pantalla un mensaje')
        action_menu.setShortcut(QKeySequence('Ctrl+P'))
        action_menu.triggered.connect(self.print_message)

        # Incluímos status tip para mostrar en status bar
        action_menu.setStatusTip('Imprime mensaje por pantalla')

        # Añadimos acción a menú
        menu_menu.addAction(action_menu)

        # Creamos barra de herramientas
        # Añadimos acción a la barra de herramientas

        toolbar = QToolBar('ToolBar')
        toolbar.addAction(action_menu)

        # Añadimos barra de herramientas a la ventana

        self.addToolBar(toolbar)

        # Creamos barra de estado
        # Mostramos mensaje por 3 segundos
        # platform.system() devuelve el sistema operativo

        statusbar = self.statusBar()
        statusbar.addPermanentWidget(QLabel(platform.system()))
        statusbar.showMessage('Bienvenido a la GUI', 3000)




    def print_message(self):
        print('Mensaje impreso por pantalla')    

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication([])

    ## Si quisiéramos quitar el icono del menu

    app.setAttribute(Qt.AA_DontShowIconsInMenus, True)

    window = StatusBar()
    window.show()
    app.exec()    