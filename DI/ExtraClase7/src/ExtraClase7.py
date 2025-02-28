import http.server
import socketserver
import sys
import threading

import pandas as pd
import altair as alt
import datapane as dp
from PySide6.QtCore import QThread, Signal, QTimer, Qt
from PySide6.QtWebEngineWidgets import QWebEngineView
from PySide6.QtWidgets import  QComboBox, QVBoxLayout, QWidget, QPushButton, QApplication


# Leemos el informe
df = pd.read_csv('inventario.csv', sep=" ")

def generar_infome(categoria):

    # Filtramos los datos por categoria
    df_categoria = df[df['Categoría'] == categoria].sort_values(by="Fecha-de-Entrada")

    # Imprimimos información para asegurarnos buena carga
    print(f"Imprimiendo infome de categoría {categoria}",df_categoria, sep="\n")
    print(f"Imprimiendo infome dos",df_categoria[["Producto","Unidades-Disponibles"]], sep="\n")

    # Generamos gráfico de barras
    bar_chart = alt.Chart(df_categoria).mark_bar().encode(
        x="Categoría:N", y="Unidades-Disponibles:Q",color="Categoría:N"
    ).properties(title=f"Unidades disponible por {categoria}")

    # Gráfico de líneas
    line_chart = alt.Chart(df_categoria).mark_line().encode(
        x=alt.X("Fecha-de-Entrada:T", timeUnit="yearmonthdate"),y="Unidades-Disponibles:Q",color="Categoría:N"
    ).properties(title=f"Evolución de {categoria} en el tiempo")

    # Incluímos HTML y tabla al informe
    titulo = dp.HTML(f'<h1 style="text-align:center;">Ejercicios Extra Clase 7</h1>')
    cabecera = dp.HTML(f'<h2 style="color: blue;text-align:center;"> Unidades disponibles de: {categoria} </h2>')
    cabecera2 = dp.HTML(f'<h2 style="color: blue;text-align:center;"> "Evolución en el tiempo de:" {categoria} </h2>')
    cabecera3 = dp.HTML(f'<h2 style="color: blue;text-align:center;"> Tabla de: {categoria} </h2>')
    tabla = dp.Table(df_categoria)

    report = dp.Report(titulo,
                       cabecera,
                       bar_chart,
                       cabecera2,
                       line_chart,
                       cabecera3,
                       tabla
                       )

    # Ruta para guardar el report
    report.save("report.html")

def start_server():
    PORT = 8000
    Handler = http.server.SimpleHTTPRequestHandler
    with socketserver.TCPServer(("", PORT), Handler) as httpd:
        print(f"Servidor iniciado en http://localhost:{PORT}")
        httpd.serve_forever()

class Worker(QThread):

    def __init__(self):
        super().__init__()
        self.categoria = None

    def run(self):
        if self.categoria != None:
            generar_infome(self.categoria)
        else:
            print("waiting for signal.. zzz")

    def set_categoria(self, categoria):
        self.categoria = categoria


class MainWindow(QWidget):

    signal_categoria = Signal(str)

    def __init__(self):
        super().__init__()
        self.setWindowTitle("ExtraClase7")
        self.setGeometry(100, 100, 1200, 800 )

        layout = QVBoxLayout()
        self.setLayout(layout)

        df_cat = df['Categoría'].unique()
        self.combobox = QComboBox()
        self.combobox.setStyleSheet("text-align:center;")
        self.combobox.addItems(df_cat)

        btn_informe = QPushButton("Hilo timer")
        btn_informe.clicked.connect(self.start_timer)
        btn_informe2 = QPushButton("Hilo QThread")
        btn_informe2.clicked.connect(self.start_QThread)

        layout.addWidget(self.combobox)
        layout.addWidget(btn_informe)
        layout.addWidget(btn_informe2)

        self.web = QWebEngineView()
        self.web.setUrl("http://localhost:8000/report.html")
        layout.addWidget(self.web)

    def start_timer(self):
        self.timer = QTimer()
        self.timer.timeout.connect(self.emit_report)
        self.timer.start(500)

    def emit_report(self):
        generar_infome(self.combobox.currentText())
        self.web.setUrl("http://localhost:8000/report.html")
        self.timer.stop()

    def start_QThread(self):
        self.worker = Worker()
        self.signal_categoria.connect(self.worker.set_categoria)
        self.signal_categoria.emit(self.combobox.currentText())
        self.worker.start()
        self.web.setUrl("http://localhost:8000/report.html")

if __name__ == '__main__':
    # arrancar servidor
    thread = threading.Thread(target=start_server, daemon=True)
    thread.start()

    app = QApplication(sys.argv)
    window = MainWindow()
    window.show()
    sys.exit(app.exec())

