# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtWidgets import QApplication, QVBoxLayout, QWidget, QMainWindow, QPushButton

class VentanaPrincipal (QMainWindow):
    
    def __init__(self):
        
        super().__init__()
        self.setWindowTitle("Vertical Layout")
        
        # Creamos layout
        layout = QVBoxLayout()

        #Añadimos objetos
        layout.addWidget(QPushButton('Uno'))
        layout.addWidget(QPushButton('Dos'))
        layout.addWidget(QPushButton('Tres'))
        layout.addWidget(QPushButton('Cuatro'))

        #Creamos ventana 
        componente = QWidget()

        #Metemos layout en ventana
        componente.setLayout(layout)

        #Metemos ventana dentro de ventana principal
        self.setCentralWidget(componente)

#################### Fin de clase ####################

if __name__ == '__main__':
    app = QApplication()

    ventana = VentanaPrincipal()
    ventana.show()

    app.exec()
     