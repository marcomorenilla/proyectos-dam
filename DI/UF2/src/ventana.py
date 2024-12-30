# Trabajamos componentes de UI de la UF2
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QLabel, QWidget 

# La clase ventana hereda de QWidget `ventana(QWidget)`
# QWidget es la clase base de todos los componentes de la interfaz de usuario

class Ventana(QWidget):

    #Constructor de la clase
    # `super().__init__()` llama al constructor de la clase padre
    # self.setWindowTitle("Ventana") establece el título de la ventana
    # self equivale a la clase entera, lo llevan todos los métoos de clase en Python
    # QLabel("Hola Mundo", self) crea una etiqueta con el texto "Hola Mundo"
    # self es el padre de la etiqueta
    # self.etiqueta es un atributo de la clase
    # Los componentes están ocultos por defecto al pasarles el padre se mostrarán al mostrar el padre


    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ventana")
        self.etiqueta = QLabel("¡Hola Mundo!", self)

############ Fin de la clase Ventana ############

# Creamos la aplicación
# __name__ es una variable que contiene el nombre del módulo
# __main__ es el nombre del módulo principal
# Si el módulo es el principal, se ejecuta el código
# Si el módulo es importado, no se ejecuta el código
# app = QApplication([]) crea la aplicación
# ventana = Ventana() crea la ventana
# ventana.show() muestra la ventana
# app.exec() ejecuta la aplicación

if __name__ == "__main__":
    app = QApplication([])

    # Creamos la ventana
    ventana = Ventana()
    ventana.show()

    # Ejecutamos la aplicación
    app.exec()

    