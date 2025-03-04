import os
import sys

import pytest
from PySide6.QtWidgets import QApplication


from Ejercicio4 import Car

#sys.path.append(os.path.abspath("src"))

@pytest.fixture (scope="session")
def app():
    app = QApplication([])
    yield app
    app.quit()

@pytest.fixture()
def coche(app):
    coche = Car()
    return coche

def test_arrancar(coche):
    coche.arrancar()
    assert coche.estado() == "En marcha"

def test_detenendo(coche):
    coche.detener()
    assert coche.estado() == "Detenido"

def test_detener(coche):
    coche.en_marcha = True
    assert coche.estado() == "En marcha"
    coche.en_marcha = False
    assert coche.estado() == "Detenido"
