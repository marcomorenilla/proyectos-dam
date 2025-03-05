import sys

import pytest
from PySide6.QtWidgets import QApplication

from Ejercicio1 import Edad
from Ejercicio2 import Combo


@pytest.fixture(scope="session")
def app():
    app = QApplication(sys.argv)
    yield app
    app.quit()

@pytest.fixture()
def window_edad(app):
    window = Edad()
    window.show()
    return window

@pytest.fixture()
def window_combo(app):
    window = Combo()
    window.show()
    return window

def test_edad(window_edad):
    window_edad.line_edit.setText("5")
    window_edad.calculate()
    assert window_edad.lbl_edad.text() == "Eres menor de edad"
    window_edad.line_edit.setText("18")
    window_edad.calculate()
    assert window_edad.lbl_edad.text() == "Eres mayor de edad"
    window_edad.line_edit.setText("-1")
    window_edad.calculate()
    assert window_edad.lbl_edad.text() == "No existen edades negativas"

def test_combo(window_combo):
    window_combo.combo.setCurrentIndex(0)
    window_combo.combo_changed()
    assert window_combo.lbl.styleSheet() == "font-size:10px;"
    window_combo.combo.setCurrentIndex(1)
    #window_combo.combo_changed()
    assert window_combo.lbl.styleSheet() == "font-size:15px;"
    window_combo.combo.setCurrentIndex(2)
    #window_combo.combo_changed()
    assert window_combo.lbl.styleSheet() == "font-size:20px;"