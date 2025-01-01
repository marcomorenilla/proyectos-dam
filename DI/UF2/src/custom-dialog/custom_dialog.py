# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso
# Clase de diálogo

from PySide6.QtWidgets import  QVBoxLayout, QDialog, QDialogButtonBox, QLabel

class CustomDialog(QDialog):
    
    def __init__(self, parent = None):
        super().__init__(parent)
        self.setWindowTitle('CustomDialog')

        # Definimos caja de botones
        # Conectamos con señales

        buttons = QDialogButtonBox.Ok | QDialogButtonBox.Cancel
        self.button_box = QDialogButtonBox(buttons)

        self.button_box.accepted.connect(self.accept)
        self.button_box.rejected.connect(self.reject)

        # Añadimos layout

        self.layout_box = QVBoxLayout()
        self.layout_box.addWidget(QLabel('¿Deseas realizar esta acción?'))
        self.layout_box.addWidget(self.button_box)
        self.setLayout(self.layout_box)

### Fin de clase ###

