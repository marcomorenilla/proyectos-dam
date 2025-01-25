# Crea una aplicación que permita agregar tareas a una lista. El usuario puede escribir una tarea en un
# cuadro de texto y agregarla a un área de texto más grande que muestra todas las tareas acumuladas.
from PySide6.QtCore import Qt
from PySide6.QtGui import QIcon
from PySide6.QtWidgets import QWidget, QApplication, QVBoxLayout, QLineEdit, QScrollArea, QLabel, QSizePolicy, \
    QHBoxLayout, QPushButton


class Tareas(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Tareas")
        self.setGeometry(500, 500, 400, 200)

        vbox = QVBoxLayout()
        self.setLayout(vbox)

        task_widget = QWidget()
        task_layout = QHBoxLayout()
        task_widget.setLayout(task_layout)

        self.new_task_edit = QLineEdit(self)
        self.new_task_edit.setPlaceholderText("Escribe una tarea...")
        btn_new_task = QPushButton("Agregar")

        btn_new_task.clicked.connect(self.agregar_tareas)

        task_layout.addWidget(self.new_task_edit)
        task_layout.addWidget(btn_new_task)

        # Scroll
        scroll_area = QScrollArea()
        scroll_area.setWidgetResizable(True)

        #Contenedor del scroll_area
        scroll_widget = QWidget()

        #Layout del contenedor
        self.scroll_layout = QVBoxLayout()

        #Metemos el layout en el contenedor
        #Metemos el contenedor en el scroll_area
        scroll_widget.setLayout(self.scroll_layout)
        scroll_area.setWidget(scroll_widget)

        #Elementos del contenedor principal
        vbox.addWidget(task_widget)
        vbox.addWidget(scroll_area)

    def agregar_tareas(self):
        tarea = QLabel(self, text=self.new_task_edit.text())
        self.new_task_edit.clear()
        tarea.setFixedHeight(50)
        tarea.setAlignment(Qt.AlignCenter)
        tarea.setStyleSheet("border: 2px solid black; border-radius: 5px;")
        self.scroll_layout.addWidget(tarea)






if __name__ == '__main__':
    app = QApplication([])
    window = Tareas()
    window.show()
    app.exec()