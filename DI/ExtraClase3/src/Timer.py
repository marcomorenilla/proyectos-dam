#Crea una aplicación que implemente un cronómetro. Debe incluir botones para iniciar, pausar y
#reiniciar el cronómetro, y mostrar el tiempo transcurrido en formato mm:ss. (QTime)
import sys
from time import sleep

from PySide6.QtCore import Qt, QTime, QTimer
from PySide6.QtWidgets import QWidget, QVBoxLayout, QLabel, QPushButton, QHBoxLayout, QApplication


class Crono(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Crono")
        layout = QVBoxLayout()
        self.setLayout(layout)


        # Creación de elementos
        widget_time = QWidget()
        widget_btn = QWidget()
        self.label_time =QLabel(self,text="00:00")
        btn_inicio = QPushButton(self, text="Inicio")
        btn_pausa = QPushButton(self, text="Pausa")
        btn_reset = QPushButton(self, text="Reset")
        layout_time = QHBoxLayout()
        layout_btn =QVBoxLayout()
        self.time = QTime(0,1,0,0)
        self.timer = QTimer()

        # estilo elementos
        self.label_time.setAlignment(Qt.AlignCenter)

        # Añadir layouts a widgets y widgets a principal
        widget_time.setLayout(layout_time)
        widget_btn.setLayout(layout_btn)
        layout.addWidget(widget_time)
        layout.addWidget(widget_btn)

        # Añadir elementos a layout tiempo
        layout_time.addWidget(self.label_time)


        #Añadir elementos a layout btn
        layout_btn.addWidget(btn_inicio)
        layout_btn.addWidget(btn_pausa)
        layout_btn.addWidget(btn_reset)

        #Conectar botones con función
        btn_inicio.clicked.connect(self.cronometro)
        btn_pausa.clicked.connect(self.cronometro)
        btn_reset.clicked.connect(self.cronometro)

        self.timer.timeout.connect(self.update_time)
        self.timer.setInterval(1000)

    def cronometro(self):
        btn = self.sender()
        if btn.text() == "Inicio":
            self.update_time()

        elif btn.text() == "Pausa":
            self.timer.stop()

        else:
            self.time = QTime(0,0)
            self.label_time.setText(self.time.toString("mm:ss"))


    def update_time(self):
        self.timer.start()
        self.time = self.time.addSecs(1)
        self.label_time.setText(self.time.toString("mm:ss"))

if __name__ == "__main__":
    app = QApplication(sys.argv)
    crono = Crono()
    crono.show()
    app.exec()
