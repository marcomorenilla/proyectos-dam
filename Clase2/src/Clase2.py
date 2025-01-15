import sys
from PySide6.QtWidgets import QMainWindow, QApplication
from src import  Ui_MainWindow
from src.Ui_MainWindow import Ui_MainWindow


class Clase2_Grupo2(QMainWindow):
    """
    Tengo la clase principal que hereda de QMAINWINDOW, permite tener el menu de herramientas de arriba.
    """
    def __init__(self):
        super().__init__()

        # Llama al constructor de la clase base QMainWindows
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.btnIncrementar.clicked.connect(lambda : self.sumar_restar(1))
        self.ui.btnDecrementar.clicked.connect(lambda : self.sumar_restar(-1))
        self.ui.btnResetear.clicked.connect(lambda: self.sumar_restar(-int(self.ui.lblContador.text())))
        self.ui.btnSumar.clicked.connect(lambda : self.sumar_restar(int(self.ui.plainTextEdit.toPlainText())))
        self.ui.btnRestar.clicked.connect(lambda: self.sumar_restar(-int(self.ui.plainTextEdit.toPlainText())))

    def sumar_restar(self,numero):
        try:
            contador = int(self.ui.lblContador.text())
            self.ui.lblContador.setText(str(contador + numero))
            if contador + numero > 0:
                self.ui.lblContador.setStyleSheet("QLabel {color: green;}")
            elif contador + numero < 0:
                self.ui.lblContador.setStyleSheet("QLabel {color: red;}")
            else:
                self.ui.lblContador.setStyleSheet("QLabel {color: black;}")

        except ValueError:
            print("No se ha introducido un numero")

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = Clase2_Grupo2()
    window.show()
    sys.exit(app.exec())