import pandas as pd
import os
import sys
import datapane as dp
import altair as alt
from PySide6.QtWidgets import QApplication, QMainWindow, QVBoxLayout, QWidget, QPushButton, QComboBox
from PySide6.QtWebEngineWidgets import QWebEngineView
from PySide6.QtCore import QUrl, QTimer
import threading
import http.server
import socketserver

# Cargar datos desde un CSV
fichero_csv = "../prueba.csv"
df = pd.read_csv(fichero_csv, sep=",")

# Función para generar el informe dinámico
def generar_informe(mes):
    datos_mes = df[df['Mes'] == mes]
    unidades_total = datos_mes['Unidades'].sum()

    # Gráfico de barras de ventas por vendedor
    grafico_barras = alt.Chart(datos_mes).mark_bar().encode(
        x='Nombre:N', y='Importe:Q', color='Nombre:N'
    ).properties(title=f"Ventas por Vendedor en {mes}")

    # Gráfico de sectores (pie chart) de unidades vendidas
    datos_agg = datos_mes.groupby("Nombre")["Unidades"].sum().reset_index()
    grafico_sectores = alt.Chart(datos_agg).mark_arc().encode(
        theta='Unidades:Q', color='Nombre:N'
    ).properties(title=f"Distribución de Unidades Vendidas en {mes}")

    # Elementos del informe
    titulo = dp.HTML(f'<h2 style="text-align:center;">Informe de Ventas - {mes}</h2>')
    tabla = dp.DataTable(datos_mes)
    texto = dp.Text(f'**Unidades totales vendidas en {mes}: {unidades_total}**')

    # Crear el informe
    reporte = dp.Report(
        titulo,
        texto,
        dp.Plot(grafico_barras),
        dp.Plot(grafico_sectores),
        tabla
    )

    # Guardar el informe como HTML en una ruta absoluta
    reporte_path = os.path.abspath("informe_ventas.html")
    reporte.save(reporte_path)

    # Verificar si el archivo se generó correctamente
    if not os.path.exists(reporte_path):
        print(f"⚠ Error: No se encontró el archivo {reporte_path}")
    else:
        print(f"✅ Archivo guardado en: {reporte_path}")

    return reporte_path


# Servidor HTTP local para servir el informe
def iniciar_servidor():
    PORT = 8000
    Handler = http.server.SimpleHTTPRequestHandler
    with socketserver.TCPServer(("", PORT), Handler) as httpd:
        print(f"Servidor iniciado en http://localhost:{PORT}")
        httpd.serve_forever()


# Interfaz con Qt para mostrar el informe en QWebEngineView
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Informe de Ventas")
        self.setGeometry(100, 100, 1200, 800)

        # Selector de mes
        self.selector_mes = QComboBox()
        self.selector_mes.addItems(df['Mes'].unique())
        self.selector_mes.currentTextChanged.connect(self.actualizar_reporte)

        # Botón para regenerar informe
        self.boton_actualizar = QPushButton("Actualizar Informe")
        self.boton_actualizar.clicked.connect(self.actualizar_reporte)

        # Crear el widget de vista web
        self.vista_web = QWebEngineView()
        self.vista_web.settings().setAttribute(self.vista_web.settings().WebAttribute.JavascriptEnabled, True)

        # Layout principal
        layout = QVBoxLayout()
        layout.addWidget(self.selector_mes)
        layout.addWidget(self.boton_actualizar)
        layout.addWidget(self.vista_web)

        contenedor = QWidget()
        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)

        # Generar el primer informe por defecto
        self.actualizar_reporte()

    def actualizar_reporte(self):
        mes_seleccionado = self.selector_mes.currentText()

        # Generar el informe en un hilo separado para no bloquear la interfaz
        def generar_y_mostrar():
            reporte_path = generar_informe(mes_seleccionado)

            # Verificar si el archivo existe antes de abrirlo
            if os.path.exists(reporte_path):
                print(f"📂 Cargando informe desde: {reporte_path}")
                self.vista_web.setUrl(QUrl(f"http://localhost:8000/informe_ventas.html"))
            else:
                print(f"⚠ Error: No se pudo abrir {reporte_path}")

        # Usamos QTimer para no bloquear el hilo principal
        QTimer.singleShot(0, generar_y_mostrar)  # Ejecuta la función de forma asíncrona


# Ejecutar la aplicación Qt
if __name__ == "__main__":
    # Iniciar el servidor HTTP en un hilo separado
    server_thread = threading.Thread(target=iniciar_servidor, daemon=True)
    server_thread.start()

    # Ejecutar la aplicación Qt
    app = QApplication(sys.argv)
    ventana = VentanaPrincipal()
    ventana.show()
    sys.exit(app.exec())