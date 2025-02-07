import sys
from tkinter.commondialog import Dialog

from PySide6.QtCore import QFile, Qt
from PySide6.QtGui import QAction, QKeySequence
from PySide6.QtUiTools import QUiLoader
from PySide6.QtWidgets import QMainWindow, QApplication, QMenu, QInputDialog, QMessageBox


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

        ## UNIR ACCIÓN COPIAR CON DEF COPIAR
        self.ui.actionCopiar.setShortcut(QKeySequence("Ctrl+c"))
        self.ui.actionCopiar.triggered.connect(self.copiar)

        ## UNIR ACCION PEGAR CON DEF PEGAR
        self.ui.actionPegar.setShortcut(QKeySequence("Ctrl+v"))
        self.ui.actionPegar.triggered.connect(self.pegar)

        ## UNIR ACCIÓN LIMPIAR CON DEF LIMPIAR
        self.ui.actionLimpiar.setShortcut(QKeySequence("Ctrl+l"))
        self.ui.actionLimpiar.triggered.connect(self.limpiar)

        ## UNIR ACCIÓN MOSTRAR MENSAJE PERSONALIZADO
        self.ui.actionMensaje.triggered.connect(self.mensaje_personalizado)

        ## Cargamos widget de ayuda
        loader_ayuda = QUiLoader()
        self.ui_ayuda = loader_ayuda.load("../ui/ayuda.ui")

        ## Menú ayuda
        menu_ayuda = QMenu("Ayuda",self)
        action_ayuda = QAction("Ayuda",self)
        menu_ayuda.addAction(action_ayuda)
        action_ayuda.triggered.connect(self.ayuda)

        self.ui.menubar.addMenu(menu_ayuda)


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

    def mensaje_personalizado(self):
        input, ok = QInputDialog().getText(self, 'Mensaje personalizado', 'Introduce un mensaje personalizado')
        if input and ok:
            QMessageBox.information(self, 'Mensaje personalizado', f'Mensaje personalizado: {str(input)} ')

    def ayuda(self):
        self.ui_ayuda.show()
        self.ui_ayuda.btnCerrar.clicked.connect(self.cerrar_ayuda)

    def cerrar_ayuda(self):
        self.ui_ayuda.hide()


if __name__ == "__main__":
    app = QApplication([])
    window = MainWindow()
    window.show()
    sys.exit(app.exec())
