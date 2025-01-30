import sys

from PySide6.QtCore import Qt
from PySide6.QtGui import QAction
from PySide6.QtWidgets import QMainWindow, QApplication, QWidget, QVBoxLayout, QListWidget, QAbstractItemView, \
    QPushButton, QMessageBox, QListWidgetItem, QMenuBar, QInputDialog, QMenu


class Tarea(QMainWindow):
    def __init__(self, current_row=None):
        super().__init__()
        self.setWindowTitle("Tareas")
        self.setGeometry(300, 300, 300, 150)

        # Contenedor central
        central_widget = QWidget()
        self.setCentralWidget(central_widget)

        # Layout principal
        main_layout = QVBoxLayout()
        central_widget.setLayout(main_layout)

        # Lista de tareas
        self.task_list = QListWidget()
        self.task_list.setContextMenuPolicy(Qt.CustomContextMenu)
        self.task_list.setSelectionMode(QAbstractItemView.ExtendedSelection)
        main_layout.addWidget(self.task_list)

        # Botón completar tarea
        btn_finished = QPushButton("Completar")
        btn_finished.clicked.connect(lambda: self.task_finished(btn_finished, self.task_list))
        main_layout.addWidget(btn_finished)

        # Menú de opciones
        menu = QMenuBar()
        self.setMenuBar(menu)

        # Acciones del menú
        # Nueva tarea

        new_task = QAction('Agregar tarea', self)
        new_task.triggered.connect(lambda: self.new_task(self.task_list))
        menu.addAction(new_task)

        # Editar tarea
        edit_task = QAction('Editar tarea', self)
        edit_task.triggered.connect(lambda: self.edit_task(self.task_list))

        menu.addAction(edit_task)

        # Eliminar tarea
        delete_task = QAction('Eliminar tarea', self)
        delete_task.triggered.connect(lambda: self.task_finished(delete_task, self.task_list))
        menu.addAction(delete_task)

        # Menú contextual
        self.task_list.setContextMenuPolicy(Qt.CustomContextMenu)
        self.task_list.customContextMenuRequested.connect(self.show_context_menu)


    # Funcionalidad de la app

    def task_finished(self, action, task_list):
        current_task = task_list.currentItem()
        text = current_task.text()
        task_list.takeItem(task_list.row(current_task))
        if action.text() == 'Completar':
            QMessageBox.information(self, "Tarea completada", f'Enhorabuena has completado la tarea {text.lower()}')
        else:
            QMessageBox.information(self, 'Tarea eliminada', f'Enhorabuena has eliminado la tarea {text.lower()}')

    def new_task(self, task_list):
        task = QListWidgetItem()
        input, ok = QInputDialog().getText(None, 'Añadir tarea', 'Introduce la tarea que quieras añadir:')
        if ok and input:
            task.setText(str(input))
            task_list.addItem(task)
            QMessageBox.information(self, 'Mensaje', 'Tarea añadida correctamente')
        else:
            QMessageBox.error(self, "Error", "Algo ha fallado tarea no añadida")

    def edit_task(self, task_list):
        current_task = task_list.currentItem()
        input, ok = QInputDialog.getText(None, 'Editar tarea', 'Introduce el texto de la nueva tarea:')
        if ok and input:
            current_task.setText(str(input))
            QMessageBox.information(self, 'Mensaje', 'Tarea editada correctamente')

    def show_context_menu(self, position):
        # Acción agregar tarea a usuario
        context_menu = QMenu()
        asing_menu = QAction('Asignar tarea', self)
        asing_menu.triggered.connect(lambda: self.asing_task(self.task_list))
        context_menu.addAction(asing_menu)

        context_menu.exec(self.mapToGlobal(position))

    def asing_task(self, task_list):
        current_task = task_list.currentItem()
        input, ok = QInputDialog.getText(None, 'Asignar usuario', 'Introduce el usuario al que asignar la tarea:')
        if ok and input:
            QMessageBox.information(self, 'Mensaje', f'Tarea {current_task.text()} asignada correctamente a {input}')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Tarea()
    window.show()
    sys.exit(app.exec())
