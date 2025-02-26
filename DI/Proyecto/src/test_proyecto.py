import sqlite3

import pytest
from PySide6.QtWidgets import QApplication
import sys
import os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from src.Proyecto import MainWindow

def delete_all():
    try:
        conn = sqlite3.connect('libros.db')
        cursor = conn.cursor()
        cursor.execute('delete from libros where id_libro!=?', (0,))
        conn.commit()
        cursor.close()
        conn.close()
    except  sqlite3.Error as e:
        print(e)


# Crear una instancia de la aplicación
@pytest.fixture(scope="session")
def application():
    app = QApplication([])
    yield app
    app.quit()


# Crear instancia de MainWindow con base de datos en memoria
@pytest.fixture()
def window(application):
    window = MainWindow()
    window.show()
    return window


# Al borrar tabla sin libros ni selección siempre es 0
def test_delete_no_book(window):
    window.delete_libro()
    assert window.table.rowCount() == 0


# Pruebo a añadir un libro, compruebo que se añade, lo elimino y lo compruebo
def test_add_delete_book(window):
    # Creo datos para insertar en la bbdd
    datos = {
        'title': 'prueba',
        'author': 'prueba',
        'description': 'prueba',
        'img': 'prueba',
        'state': 'prueba'
    }
    window.add_libro(datos)
    assert window.table.rowCount() == 1
    window.table.selectRow(0)
    window.delete_libro()
    assert window.table.rowCount() == 0


# Prueba actualizar
def test_update_book(window):
    # Creo datos para insertar en la bbdd
    datos = {
        'title': 'prueba',
        'author': 'prueba',
        'description': 'prueba',
        'img': 'prueba',
        'state': 'prueba'
    }
    window.add_libro(datos)
    # compruebo inserción
    assert window.table.rowCount() == 1
    # Cambio datos
    datos2 = {
        'title': 'prueba2',
        'author': 'prueba',
        'description': 'prueba',
        'img': 'prueba',
        'state': 'prueba'
    }
    window.table.selectRow(0)
    window.edit_libro(datos2)
    nombre_libro = window.table.item(0,0).text()
    # compruebo que se han cambiado
    assert nombre_libro == 'prueba2'
    delete_all()