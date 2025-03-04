import pytest
from PySide6.QtWidgets import QApplication
import sys
import os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from Clase8 import TaskManager



# Asegúrate de importar tu clase correcta

@pytest.fixture(scope="session")
def app_instance():
    """Crear una instancia de QApplication solo una vez para todos los tests"""
    app = QApplication([])  # Creamos la instancia de QApplication
    yield app  # Proporciona la aplicación para los tests
    app.quit()  # Cierra la aplicación después de los tests

@pytest.fixture
def window(app_instance):
    """Crear una instancia de TaskManager para cada test"""
    window = TaskManager()
    window.show()
    return window

def test_add_empty_task(window):
    window.task_input.setText("")  # Dejar el input vacío
    window.add_task()  # Intentar agregar una tarea vacía
    assert window.task_list.count() == 0  # No debe agregar nada


def test_delete_task(window):
    # Similar para el test de eliminación
    window.add_task()
    window.delete_task()
    assert window.task_list.count() == 0  # Asegúrate de que se eliminó la tarea
