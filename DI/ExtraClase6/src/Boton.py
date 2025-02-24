import sys

from PySide6.QtCore import Signal, Slot, Property
from PySide6.QtWidgets import QPushButton, QApplication, QInputDialog


class Boton(QPushButton):
    # Crear señal
    textoCambiado = Signal(str)

    def __init__(self, texto="Botón personalizado"):
        super().__init__()
        self.__text = texto
        self.setText(self.__text)
        self.setStyleSheet("font-size: 15px;")
        self.clicked.connect(self.input_cambio)
        self.textoCambiado.connect(self.on_textoCambiado)

    # Defino propiedad del botón
    @Property(str)
    def texto(self):
        return self.__text

    # Método que se encarga de fijar el texto y emitir señal
    @texto.setter
    def texto(self, texto):
        self.__text = texto
        self.textoCambiado.emit(texto)
        self.setText(self.__text)

    # Recibe la señal cuando cambia
    @Slot(str)
    def on_textoCambiado(self, texto):
        print(texto)
        self.resize(len(texto)*10, 100)

    def input_cambio(self):
        texto_cambiado, ok = QInputDialog.getText(self, "Texto de cambio", "Introduce el texto nuevo del botón:")
        if texto_cambiado and ok:
            self.texto=texto_cambiado




if __name__ == '__main__':
    app = QApplication()
    ventana = Boton()
    ventana.show()
    sys.exit(app.exec())