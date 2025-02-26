import sys
import threading
from time import sleep

from PySide6.QtCore import Signal, Property, Slot, Qt, QThread, QTimer, QRect
from PySide6.QtGui import QPainter, QBrush, QPen, QColor
from PySide6.QtWidgets import QWidget, QApplication, QVBoxLayout, QLabel, QGraphicsDropShadowEffect


class CustomWidget(QWidget):
    porcentaje_cambiado = Signal(int)

    def __init__(self, estado=0):
        super().__init__()
        self.setWindowTitle("Reto2")
        self.setGeometry(300, 300, 250, 150)
        self.porcentaje_cambiado.connect(self.on_change)
        layout = QVBoxLayout()
        self.setLayout(layout)
        self.__porcentaje = estado
        self.__current_estado = estado
        self.label_estado = QLabel()
        self.label_estado.setStyleSheet("color: #FFC966; font-size: 20px;")
        self.label_estado.setText(f"{self.__porcentaje} %")
        self.label_estado.setAlignment(Qt.AlignCenter)
        layout.addWidget(self.label_estado)


    def paintEvent(self, event):
        pintor = QPainter(self)
        pintor.setRenderHint(QPainter.Antialiasing)  # Para suavizado de bordes

        circle_size = 100

        # Calculamos el centro de la ventana
        x = (self.width() - circle_size) // 2
        y = (self.height() - circle_size) // 2
        angle = int((self.__current_estado / 100) * 360 * 16)  # 16 unidades por grado
        contenedor = QRect(x, y, circle_size, circle_size)

        pincel_exterior = QPen(Qt.white, 15, Qt.SolidLine, Qt.RoundCap)
        pintor.setPen(pincel_exterior)
        pintor.drawArc(contenedor, -180 * 16, -angle)

        pincel_interior = QPen(QColor("#FFC966"), 10, Qt.SolidLine, Qt.RoundCap)
        pintor.setPen(pincel_interior)
        pintor.drawArc(contenedor, -180 * 16, -angle)

    @Property(int)
    def porcentaje(self):
        return self.__porcentaje

    @porcentaje.setter
    def porcentaje(self, value):
        if value > 0 and value <= 100:
            self.__porcentaje = value
            self.porcentaje_cambiado.emit(self.__porcentaje)

    @Slot(int)
    def on_change(self, value):
        print("on_change")
        # self.label_estado.setText(f"{value}%")
        self.animate_progress(value)

    def animate_progress(self, target):
        self.target_estado = target

        self.timer = QTimer()
        self.timer.timeout.connect(self.timer_update)
        self.timer.start(500)

    def timer_update(self):
        if self.__current_estado <= self.target_estado:
            self.label_estado.setText(f"{self.__current_estado}%")
            self.__current_estado += 5
            self.update()
        else:
            self.timer.stop()


if __name__ == "__main__":
    app = QApplication([])
    widget = CustomWidget()
    widget.show()
    widget.porcentaje = 100
    sys.exit(app.exec())
