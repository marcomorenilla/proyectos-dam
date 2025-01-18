import sys

from PySide6.QtWidgets import QApplication, QMainWindow

from src import traffic_light
from traffic_light import Ui_MainWindow

class Principal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.btnCambiar.clicked.connect(self.cambiar)
        self.contador = 0

    def cambiar(self):
        if self.contador ==2:
            self.contador=0
            self.ui.lblYellow.setStyleSheet("background-color:transparent")
            self.ui.lblGreen.setStyleSheet("background-color: green")

        elif self.contador ==1:
            self.contador += 1
            self.ui.lblRed.setStyleSheet("background-color: transparent")
            self.ui.lblYellow.setStyleSheet("background-color: yellow")

        else:
            self.contador += 1
            self.ui.lblGreen.setStyleSheet("background-color: transparent")
            self.ui.lblRed.setStyleSheet("background-color: red")





if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Principal()
    window.show()
    sys.exit(app.exec())
