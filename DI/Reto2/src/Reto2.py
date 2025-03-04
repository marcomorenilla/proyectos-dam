import sys

from PySide6.QtCore import Signal, Property, Slot, Qt, QTimer, QRect
from PySide6.QtGui import QPainter, QPen, QColor
from PySide6.QtWidgets import QWidget, QApplication, QVBoxLayout, QLabel


class CustomWidget(QWidget):
    porcentaje_cambiado = Signal(int)

    def __init__(self, estado=0):
        super().__init__()

        # Inicialización del widget
        self.setWindowTitle("Reto2")
        self.setGeometry(300, 300, 250, 150)

        # Conexión de señal con slot
        self.porcentaje_cambiado.connect(self.on_change)

        # Layout del widget
        layout = QVBoxLayout()
        self.setLayout(layout)

        # Estado inicial del porcentaje
        self.__current_estado = estado
        self.label_estado = QLabel()
        self.label_estado.setStyleSheet("color: #FFC966; font-size: 20px;")
        self.label_estado.setAlignment(Qt.AlignCenter)
        layout.addWidget(self.label_estado)

    # Método que controla el círculo de estado
    def paintEvent(self, event):
        pintor = QPainter(self)
        pintor.setRenderHint(QPainter.Antialiasing)  # Para suavizado de bordes

        # Pintamos fondo
        pintor.fillRect(self.rect(), QColor("#2B2B2B"))

        # Definimos tamaño
        circle_size = 100

        # Calculamos el centro de la ventana
        x = (self.width() - circle_size) // 2
        y = (self.height() - circle_size) // 2

        # Transformamos el estado en grados
        angle = int((self.__current_estado / 100) * 360 * 16)
        contenedor = QRect(x, y, circle_size, circle_size)

        # Pintamos los bordes que son un círculo pintado por debajo de otro
        pincel_exterior = QPen(QColor("#E2DEDE"), 15, Qt.SolidLine, Qt.RoundCap)
        pintor.setPen(pincel_exterior)
        pintor.drawArc(contenedor, -180 * 16, -angle)

        # Pintamos el círculo interior
        pincel_interior = QPen(QColor("#FFC966"), 10, Qt.SolidLine, Qt.RoundCap)
        pintor.setPen(pincel_interior)
        pintor.drawArc(contenedor, -180 * 16, -angle)

    # Propiedad porcentaje
    @Property(int)
    def porcentaje(self):
        return self.__porcentaje

    # Setter que emite señal para que funcione
    @porcentaje.setter
    def porcentaje(self, value):
        if value > 0 and value <= 100:
            self.__porcentaje = value
            self.porcentaje_cambiado.emit(self.__porcentaje)

    # Cuando llega la señal se produce la animación
    @Slot(int)
    def on_change(self, value):
        print("on_change")
        self.animate_progress(value)

    # Creamos la animación en un timer
    def animate_progress(self, target):
        self.target_estado = target
        self.timer = QTimer()
        self.timer.timeout.connect(self.timer_update)
        self.timer.start(50)

    # La animación se dispara cada intervalo que el timer se define en el start
    def timer_update(self):
        if self.__current_estado <= self.target_estado:
            self.label_estado.setText(f"{self.__current_estado}%")
            self.__current_estado += 1
            self.update()
        else:
            self.timer.stop()


if __name__ == "__main__":
    app = QApplication([])
    widget = CustomWidget()
    widget.show()
    widget.porcentaje = 100
    sys.exit(app.exec())
