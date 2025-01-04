# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton,  QLabel, QVBoxLayout, QWidget


class OtherDialog (QLabel):
    def __init__(self):
        super().__init__()
        self.setMinimumWidth(150)
        self.setMinimumHeight(150)
        self.setWindowTitle('La otra ventana')
        self.setText('  Hola')

### Fin de clase ###


class MainWindow (QMainWindow):

    def __init__(self):
        super().__init__()

        self.otherWindow = None
        self.setWindowTitle('App dos ventanas')

        self.button = QPushButton('Mostrar ventana')
        self.button.clicked.connect(self.show_window)

        self.setCentralWidget(self.button)

    def show_window(self):

        if self.otherWindow == None:
            self.otherWindow = OtherDialog()
            self.otherWindow.show()
        else:
            if self.otherWindow.isHidden():
                self.otherWindow.show()
            else:
                self.otherWindow.hide()

### Fin de clase ###

if __name__ == '__main__':
    app = QApplication()
    window = MainWindow()
    window.show()
    app.exec()
        

