# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso


from PySide6.QtWidgets import (
    QApplication, QMessageBox, QMainWindow, QPushButton)
from PySide6.QtCore import QTranslator, QLibraryInfo

class MessageBox (QMainWindow):
    
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Message Box')
        #self.setMinimumHeight(200)
        #self.setMinimumWidth(200)

        # Creamos botón que muestre mensaje crítico

        button = QPushButton('Haz click aquí')
        button.clicked.connect(self.show_dialog)
        self.setCentralWidget(button)

    def show_dialog(self):
        buttons = QMessageBox.Discard | QMessageBox.NoToAll | QMessageBox.Ignore
        
        # QMessageBox crítico args: título, mensaje y botones.
        # Guardamos el resultado

        result = QMessageBox.critical(
            self, 'Mensaje crítico', 'Algo terrible ha sucedido, ¿qué quieres hacer?',buttons )

        if result == QMessageBox.Discard:
            print('Descartado')
        elif result == QMessageBox.NoToAll:
            print('No a todo')
        else:
            print('Ignorado')

### Fin de clase ###

def traducir(app):
    translator = QTranslator(app)
    translations =QLibraryInfo.path(QLibraryInfo.TranslationsPath)
    translator.load('qt_es',translations)
    app.installTranslator(translator)

if __name__ == '__main__':
    app = QApplication([])
    traducir(app)
    window = MessageBox()
    window.show()
    app.exec()
