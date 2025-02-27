import pandas as pd
import altair as alt
import datapane as dp
import os

from PySide6.QtWebEngineWidgets import QWebEngineView
from PySide6.QtWidgets import QMainWindow, QComboBox, QApplication, QVBoxLayout, QWidget

fichero_csv = "../prueba.csv"
df = pd.read_csv(fichero_csv)


def generar_informe(mes):
    datos_mes = df[df['Mes'] == mes]

    ## Grafico barras
    grafico_barras = alt.Chart(datos_mes).mark_bar().encode(
        x='Nombre:N', y='Importe:Q', color='Nombre:N'
    ).properties(title = f"Ventas por Vendedor en {mes}")

    ## Grafico de sectores
    datos_agg = datos_mes.groupby("Nombre")["Unidades"].sum().reset_index()
    grafico_sectores = alt.Chart(datos_agg).mark_arc().encode(
        theta= 'Unidades:Q',  color = 'Nombre:N'
    ).properties(title = f"Distribucion de unidades vendidas en {mes}")

    titulo = dp.HTML('<h1>Informe</h1>')

    reporte = dp.Report(
        titulo,
        dp.Plot(grafico_barras),
        dp.Plot(grafico_sectores)
    )

    ruta_reporte = os.path.abspath("informe_ventas1.html")
    reporte.save(ruta_reporte)

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Informe de ventas")

        self.selector_mes = QComboBox()
        self.selector_mes.addItems(df['Mes'].unique())
        self.selector_mes.currentTextChanged.connect(self.actualizar_reporte)

        self.vista_web = QWebEngineView()

        layout = QVBoxLayout()
        layout.addWidget(self.selector_mes)
        layout.addWidget(self.vista_web)

        contenedor = QWidget()
        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)



    def actualizar_reporte(self):
        mes_seleccionado = self.selector_mes.currentText()
        generar_informe(mes_seleccionado)
        ruta_reporte = os.path.abspath("informe_ventas2.html")
        if os.path.exists(ruta_reporte):
            with open(ruta_reporte, 'r', encoding='utf-8') as file:
                html_content = file.read()
                self.vista_web.setHtml(html_content)

if __name__ == "__main__":
    app = QApplication([])
    ventana = VentanaPrincipal()
    ventana.show()
    app.exec()