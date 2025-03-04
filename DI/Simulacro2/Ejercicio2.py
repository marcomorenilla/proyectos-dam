import sys

from PySide6.QtWidgets import QWidget, QVBoxLayout, QComboBox, QLabel, QApplication


class Combo(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Combo')
        self.setGeometry(300, 300, 250, 150)

        layout = QVBoxLayout()
        self.setLayout(layout)

        self.combo = QComboBox()
        self.combo.addItems(["Pequeño","Mediano","Grande"])
        self.combo.currentTextChanged.connect(self.combo_changed)
        layout.addWidget(self.combo)

        self.lbl = QLabel('Combo')
        layout.addWidget(self.lbl)

        self.size_opts = {"Pequeño": "10", "Mediano": "15", "Grande": "20"}

    def combo_changed(self):
        text = self.combo.currentText()
        print(self.size_opts[text])
        self.lbl.setStyleSheet(f"font-size:{self.size_opts[text]}px;")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Combo()
    window.show()
    sys.exit(app.exec())
