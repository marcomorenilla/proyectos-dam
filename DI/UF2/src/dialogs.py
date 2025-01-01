# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QMainWindow, QApplication, QDialog, QPushButton

class Dialog(QMainWindow):
    def __init__(self):
        super().__init__()

        # Los dialogs son ventanas modales, no se puede interaccionar con la app hasta completarla o cerrarla
        # Ejemplos:
        #   - Ok        - Close
        #   - Open      - Discard
        #   - Save      - Apply
        #   - Cancel    - Reset

        self.setWindowTitle('Dialogs')

        # Botón para lanzar el diálogo

        button = QPushButton('Pulsa aquí')
        button.clicked.connect(self.start_dialog)

        # Metemos botón dentro de MainWindow

        self.setCentralWidget(button)
        self.setMinimumWidth(500)
        self.setMinimumHeight(200)

    def start_dialog(self):
        
        # Creamos diálogo
        # Le damos un nombre a la ventana
        # Lanzamos bucle de eventos del diálogo
        dialog = QDialog(self)
        dialog.setWindowTitle('Ventana diálogo')
        dialog.exec()


### Fin de clase ###

if __name__ == '__main__':
    app = QApplication([])
    window = Dialog()
    window.show()
    app.exec()