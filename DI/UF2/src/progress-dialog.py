# Trabajamos componentes de UI de la UF2
# Archivo con descripciones ventana.py
# Utilizamos la librería PySide6
# Autor: Marco Antonio Morenilla Alonso

from PySide6.QtCore import QThread, Signal
from PySide6.QtWidgets import QMainWindow,  QPushButton, QProgressDialog, QApplication, QVBoxLayout


class WorkerThread(QThread):
    progress = Signal(int)
    finished = Signal()

    def run(self):
        for i in range(11):
            self.sleep(1)
            self.progress.emit(i)
        self.finished.emit()

class MainWindow(QMainWindow):

    def __init__(self):
        super().__init__()
        self.setWindowTitle('Copia')

        self.button_copy = QPushButton('Click para iniciar')

        self.button_copy.clicked.connect(self.start_thread)
        
        self.setCentralWidget(self.button_copy)
        

    def start_thread(self):
        self.button_copy.setEnabled(False)

        self.progress = QProgressDialog('Copiando archivos', 'Cancelar', 0, 10, self)
        self.progress.canceled.connect(self.cancel_thread)

        self.worker = WorkerThread()
        self.worker.progress.connect(self.update_progress)
        self.worker.finished.connect(self.thread_finished)

        self.worker.start()
        self.progress.show()

    def update_progress(self, progreso):
        self.progress.setValue(progreso)

    def thread_finished(self):
        self.progress.close()
        self.button_copy.setEnabled(True)

    def cancel_thread(self):
        self.worker.terminate()
        self.progress.close()
        self.button_copy.setEnabled(True)


if __name__ == '__main__':
    app = QApplication([])
    window = MainWindow()
    window.show()
    app.exec()



   

