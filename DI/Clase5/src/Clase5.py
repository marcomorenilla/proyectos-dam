import sys

from PySide6.QtCore import QFile, Qt
from PySide6.QtGui import QAction
from PySide6.QtUiTools import QUiLoader
from PySide6.QtWidgets import QMainWindow, QApplication, QMenu


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        ##INTANCIA DEL OBJETO QUiLoader

        loader = QUiLoader()

        ##INSTANCIA DEL ARCHIVO

        ui_file = QFile("../ui/interfaz.ui")

        if not ui_file.open(QFile.ReadOnly):
            print("No se puede abrir el archivo .ui")

        ##CARGAR EL ARCHIVO

        self.ui = loader.load(ui_file)
        ui_file.close()

        self.setCentralWidget(self.ui)
        self.ui.btnCambiar.clicked.connect(self.cambiarTexto)

        ##COMPORTAMIENTO DEBE DE REALIZAR EL MENU CONTEXTUAL

        self.ui.plainTextEdit.setContextMenuPolicy(Qt.CustomContextMenu)
        self.ui.plainTextEdit.customContextMenuRequested.connect(self.mostrar_menu_contextual)

    def cambiarTexto(self):
        texto = self.ui.plainTextEdit.toPlainText()
        self.ui.lblCambiar.setText(texto)


    def mostrar_menu_contextual(self, pos):
        menu_contextual = QMenu(self)

        copiar_action = menu_contextual.addAction("Copiar")
        pegar_action = menu_contextual.addAction("Pegar")
        limpiar_action = menu_contextual.addAction("Limpiar")

        copiar_action.triggered.connect(self.copiar)
        pegar_action.triggered.connect(self.pegar)
        limpiar_action.triggered.connect(self.limpiar)

        menu_contextual.exec(self.ui.plainTextEdit.mapToGlobal(pos))

    def copiar(self):
        self.ui.plainTextEdit.copy()

    def pegar(self):
        self.ui.plainTextEdit.paste()
    def limpiar(self):
        self.ui.plainTextEdit.clear()

if __name__ == "__main__":
    app = QApplication([])
    window = MainWindow()
    window.show()
    sys.exit(app.exec())