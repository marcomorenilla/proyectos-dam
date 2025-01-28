import sys

from PySide6.QtGui import QAction, QKeySequence, QIcon
from PySide6.QtWidgets import QMainWindow, QApplication, QVBoxLayout, QWidget, QPushButton, QMessageBox, QMenuBar, \
    QMenu, QToolBar
from PySide6.QtCore import Qt

class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio con PySide6")
        self.setGeometry(100,100,600, 400)

        # CREAR EL WIDGET CENTRAL O LAYOUT PRINCIPAL
        central_widget = QWidget()
        self.setCentralWidget(central_widget)
        layout = QVBoxLayout()
        central_widget.setLayout(layout)

        # BOTON PRINCIPAL
        self.button = QPushButton("Presioname")
        self.button.clicked.connect(self.show_message)
        layout.addWidget(self.button)

        # MENU PRINCIPAL
        menu_bar = QMenuBar()
        self.setMenuBar(menu_bar)

        # CREO EL MENU QUE SE VE
        file_menu = QMenu("Archivo", self)
        menu_bar.addMenu(file_menu)

        # CREO LA OPCIONES DE ESE MENU
        new_action = QAction("Nuevo", self)
        new_action.setShortcut(QKeySequence("Ctrl+N"))
        new_action.triggered.connect(lambda : self.show_info("Nuevo seleccionado"))
        file_menu.addAction(new_action)
        open_action = QAction("Abrir", self)
        open_action.setShortcut(QKeySequence("Ctrl+O"))
        open_action.triggered.connect(lambda: self.show_info("Abrir seleccionado"))
        file_menu.addAction(open_action)
        save_action = QAction("Guardar", self)
        save_action.setShortcut(QKeySequence("Ctrl+S"))
        save_action.triggered.connect(lambda: self.show_info("Guardar seleccionado"))
        file_menu.addAction(save_action)

        # MENU CONTEXTUAL
        self.setContextMenuPolicy(Qt.CustomContextMenu)
        self.customContextMenuRequested.connect(self.show_context_menu)

        # Barra de herramientas
        toolbar = QToolBar("Barra de herramientas", self)
        self.addToolBar(toolbar)
        toolbar_action = QAction(QIcon.fromTheme("help-browser"), "Mostrar mensaje", self)
        toolbar_action.setShortcut(QKeySequence("Ctrl+M"))
        toolbar_action.triggered.connect(lambda : self.show_info("Mensaje desde la barra de herramientas"))
        toolbar.addAction(toolbar_action)
    def show_message(self):
        QMessageBox.information(self, "Mensaje", "¡Boton presionado!")

    def show_info(self, message):
        QMessageBox.information(self, "Mensaje", message)

    def show_context_menu(self, position):
        context_menu = QMenu()
        context_action = QAction("Accion contextual", self)
        context_action.setShortcut(QKeySequence("Ctrl+X"))
        context_action.triggered.connect(lambda : self.show_info("Accion contextual dada"))
        context_menu.addAction(context_action)

        context_menu.exec(self.mapToGlobal(position))

if __name__ == "__main__":
    app = QApplication([])
    window = MainWindow()
    window.show()
    sys.exit(app.exec())