import sys

from PySide6.QtWidgets import QWidget, QVBoxLayout, QComboBox, QApplication


class Ejercicio2(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio2")
        self.setGeometry(300, 300, 250, 150)

        layout = QVBoxLayout()
        self.setLayout(layout)

        self.combo = QComboBox()
        self.combo.addItem("Verde")
        self.combo.addItem("Rojo")
        self.combo.addItem("Azul")
        self.combo.currentTextChanged.connect(self.set_color)
        layout.addWidget(self.combo)

    def set_color(self):
        map_color = {"Verde":"green", "Rojo":"red", "Azul":"blue"}
        color = self.combo.currentText()
        self.setStyleSheet(f"background-color: {map_color.get(color)} ")


if __name__ == "__main__":
    app = QApplication([])
    ex = Ejercicio2()
    ex.show()
    sys.exit(app.exec())
