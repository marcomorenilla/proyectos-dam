import sys

import pytest
from PySide6.QtWidgets import QApplication

from ExtraClase8 import ListaTareas


@pytest.fixture(scope='session')
def app():
    app = QApplication(sys.argv)
    yield app
    app.quit()

@pytest.fixture()
def task_window(app):
    window = ListaTareas()
    window.show()
    return window

def test_agregar(task_window):
    task_window.edit_tarea.setText("Tarea1")
    task_window.agregar()
    assert task_window.lista_tarea.count() == 1

def test_eliminar(task_window):
    task_window.lista_tarea.addItem("Tarea2")
    task_window.lista_tarea.setCurrentRow(0)
    task_window.eliminar()
    assert task_window.lista_tarea.count() == 0