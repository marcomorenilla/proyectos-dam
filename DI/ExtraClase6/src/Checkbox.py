import sys

from PySide6.QtCore import Signal, Slot, Property
from PySide6.QtWidgets import QMainWindow, QApplication
from Tools.scripts.smelly import is_local_symbol_type

from src import Ui_MainWindow
from src.Ui_MainWindow import Ui_MainWindow


class CheckBox(QMainWindow):


    def __init__(self):
        super().__init__()
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.checkBox.toggled.connect(lambda: self.on_check(self.ui.checkBox.isChecked()))


    @Slot(bool)
    def on_check(self, is_checked):
        print("Imprimo desde el slot", is_checked)
        if is_checked:
            self.ui.label.setText("Activado")
        else:
            self.ui.label.setText("Desactivado")



if __name__ == "__main__":
    app = QApplication([])
    window = CheckBox()
    window.show()
    sys.exit(app.exec())


