import sys

from PySide6.QtWidgets import QWidget, QVBoxLayout, QApplication, QPushButton, QLineEdit, QListWidget, QMessageBox


class ListaTareas(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Lista Tareas')
        self.setGeometry(300, 300, 300, 300)

        layout = QVBoxLayout()
        self.setLayout(layout)

        self.edit_tarea = QLineEdit(self)
        self.edit_tarea.setPlaceholderText('Escribe tarea...')
        layout.addWidget(self.edit_tarea)

        btn_add = QPushButton('Agregar')
        btn_add.clicked.connect(self.agregar)
        layout.addWidget(btn_add)

        self.lista_tarea = QListWidget(self)
        layout.addWidget(self.lista_tarea)

        btn_del = QPushButton('eliminar')
        btn_del.clicked.connect(self.eliminar)
        layout.addWidget(btn_del)

    def agregar(self):
        if self.edit_tarea.text():
            tarea = self.edit_tarea.text()
            self.lista_tarea.addItem(tarea)
        else:
            QMessageBox.warning(self, "Error","No hay tarea que agregar")

    def eliminar(self):
        row = self.lista_tarea.currentRow()
        if row != -1:
            self.lista_tarea.takeItem(row)
        else:
            QMessageBox.warning(self, 'Error', 'No se hay tarea seleccioada')



if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = ListaTareas()
    ex.show()
    sys.exit(app.exec())
