from time import sleep

from PySide6.QtCore import Property, Signal, Slot, QTimer
from PySide6.QtGui import QColor
from PySide6.QtWidgets import QPushButton, QApplication, QGraphicsDropShadowEffect


class CustomButton(QPushButton):
    # Defino una señal personaliza que emite el texto del boton cuando cambie
    textoCambiado = Signal(str)

    def __init__(self, text = "Texto inicial"):
        super().__init__(text) # Llamos al constructor de QPushButton
        self.__text = text # Inicializo el atributo _text con el texto proporcionado
        # Quito esta línea para que la función se ejecute en el setter
        #self.set_color_based_on_text()
        self.textoCambiado.connect(self.on_text_changed)

    @Property(str) # Definimos una propiedad "text" que devuelve el valor del texto
    def text(self):
        return self.__text

    @text.setter
    def text(self,value):
        if self.__text != value: # Si el texto es diferente al anterior
            self.__text = value # Actualizo el valor del atributo
            self.setText(value) # Cambio el texto del boton
            self.textoCambiado.emit(value) # Emito la señal de texto cambiado
            self.set_color_based_on_text()  # Añado esta línea para que cada vez que se cambie el texto se cambie el color

    def set_color_based_on_text(self):
        #Definir un diccionario de colores
        color_map = {
            "Rojo": "red",
            "Verde" : "green",
            "Azul" : "blue"
        }
        color = color_map.get(self.__text, "gray") # Si no encuentra el texto usar gray
        # Elimino esta parte para delegar el cambio de color a la función animate_color_changed
        #self.setStyleSheet(f"background-color: {color};")
        self.animate_color_changed(color)

    @Slot(str)  # TextoCambiado ha sido emitido pues se ejecuta
    def on_text_changed(self):
        print(f"El texto del boton cambio")
        self.setStyleSheet("font-size: 16px;")

    def add_shadow_efect(self):
        #Creo un efecto de sombra
        shadow = QGraphicsDropShadowEffect()
        #Establecemos el radio de difuminado de la sombra
        shadow.setBlurRadius(10)
        #Establezco el color de la sombra
        shadow.setColor(QColor(0,0,0,160))
        #Establezco el desplazamiento de la sombra
        shadow.setOffset(3,3)
        #Aplico el efecto de la sombra al boton
        self.setGraphicsEffect(shadow)

    def animate_color_changed(self, target_color):
        # Tomo el color actual y el color objetivo
        self.current_color = QColor(self.palette().button().color())
        self.target_color = QColor(target_color)

        # Crear un temporalizador para hacer el cambio gradual del color
        self.timer = QTimer()
        self.timer.timeout.connect(self.step_color_change)
        self.timer.start(50) # Se repite esto cada 50 milisegundos

    def step_color_change(self):
        step = 10 # El cambio de los colores
        current_rgb = self.current_color.getRgb()[:3]
        target_rgb = self.target_color.getRgb()[:3]

        #Calculo los valores intermedios del RGB
        new_rgb = [
            min(255, max(0, current_rgb[i] + (target_rgb[i] - current_rgb[i]) // step))
            for i in range(3)
        ]

        self.current_color.setRgb(*new_rgb)
        self.setStyleSheet(f"background-color: {self.current_color.name()};")
        if self.current_color == self.target_color:
            self.timer.stop() # Detener el temporalizador

    def set_text_size(self, size):
        self.setStyleSheet(f"font-size:{size} px")



if __name__ == "__main__":
    app = QApplication([]) # Aplicacion de QT
    button = CustomButton("Rojo")
    button.show()
    button.text="Verde"
    #button.animate_color_changed("green") # Iniciamos la animacion de cambio de colro hacia el azul
    app.exec()