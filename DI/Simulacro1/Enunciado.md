**PARTE PRÁCTICA**

1) Crea una aplicación en PySide6 que tenga una ventana con los siguientes componentes:
• Un QLineEdit para que el usuario ingrese su nombre.
• Un botón para mostrar un mensaje que salude al usuario con su nombre.
• El mensaje debe aparecer en un QLabel debajo del botón, donde se debe actualizar cada vez
que el usuario presione el botón.

2) Crea una ventana en PySide6 con un QComboBox que permita al usuario elegir un color. Según
el color elegido, cambia el color de fondo de la ventana a ese color y crea un ejecutable de la misma
aplicación.

3) Crea un archivo README.md para una aplicación que permita a los usuarios seleccionar y
eliminar archivos de un directorio. El archivo debe incluir:
• Un título con el nombre de la aplicación.
• Una sección de Descripción que explique cómo funciona la aplicación.
• Una sección de Instalación que explique cómo instalar las dependencias necesarias y
ejecutar la aplicación.

4) Escribe pruebas unitarias utilizando pytest para una clase Coche que tiene los siguientes
métodos:
class Coche:
def arrancar(self):
self.en_marcha = True
def detener(self):
self.en_marcha = False
def estado(self):
return 'En marcha' if self.en_marcha else 'Detenido'
Asegúrate de probar los métodos arrancar(), detener(), y estado() para garantizar su correcto
funcionamiento.

5) Crea un archivo CSV con los siguientes datos de ventas y genera un informe con los siguientes
gráficos: 
A) Un gráfico de barras que muestre las ventas de cada producto. 
B) Un gráfico de líneas que muestre la evolución de las ventas por mes para cada producto.
C) Un título que describa el informe.

ARCHIVO CSV:
Producto,Mes,Unidades Vendidas,Precio Unitario (€)
Producto A,Enero,100,25
Producto B,Enero,50,150
Producto C,Febrero,75,30
Producto D,Febrero,30,200
Producto E,Marzo,200,20
Producto F,Marzo,120,15