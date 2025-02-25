import os
import sqlite3
import sys


from PySide6.QtCore import Qt, Signal, Slot, Property
from PySide6.QtGui import QAction, QIcon, QPixmap
from PySide6.QtWidgets import QMainWindow, QApplication, QVBoxLayout, QWidget, QMenuBar, QToolBar, QFormLayout, \
    QLineEdit, QTextEdit, QFileDialog, QPushButton, QComboBox, QLabel, QMessageBox, QHBoxLayout, QTableWidget, \
    QAbstractItemView, QSizePolicy, QTableWidgetItem, QHeaderView, QGridLayout

# creamos db
def create_db():
    con =sqlite3.connect('libros.db')
    cursor = con.cursor()
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS libros (
        id_libro INTEGER PRIMARY KEY AUTOINCREMENT,
        titulo TEXT NOT NULL,
        autor TEXT NOT NULL,
        descripcion TEXT,
        img_path TEXT,
        estado TEXT
    )
    """)
    con.commit()
    con.close()

class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Gestor de libros")
        self.setGeometry(300, 300, 800, 400)

        # Creamos central widget
        central_layout = QVBoxLayout()
        central_widget = QWidget()
        central_widget.setLayout(central_layout)

        # Incorporamos central widget a MainWindow
        self.setCentralWidget(central_widget)

        # Añadimos segunda ventana
        self.form_window = None

        # Datos pasados del form
        self.datos_libro = None

        # Pantalla detalles
        self.details_window = None

        # Añadimos toolbar
        tool_bar = QToolBar()
        tool_bar.setMovable(False)
        self.addToolBar(Qt.LeftToolBarArea ,tool_bar)


        # Acciones de la app
        tool_bar.addSeparator()
        icon_add_path = os.path.join(os.path.dirname(__file__), "assets\\add.png")
        add_action =QAction(QIcon(icon_add_path),'&Añadir libro',self)
        add_action.triggered.connect(self.show_form)
        tool_bar.addAction(add_action)
        tool_bar.addSeparator()

        icon_edit_path = os.path.join(os.path.dirname(__file__), "assets\\edit.png")
        edit_action =QAction(QIcon(icon_edit_path),'&Editar libro',self)
        tool_bar.addAction(edit_action)
        edit_action.triggered.connect(self.show_form)
        tool_bar.addSeparator()

        icon_delete_path = os.path.join(os.path.dirname(__file__), "assets\\delete.png")
        delete_action = QAction(QIcon(icon_delete_path),'&Eliminar libro',self)
        delete_action.triggered.connect(self.delete_libro)
        tool_bar.addAction(delete_action)
        tool_bar.addSeparator()

        icon_show_path = os.path.join(os.path.dirname(__file__), "assets\\ver.png")
        show_details_action = QAction(QIcon(icon_show_path),'&Ver detalles',self)
        show_details_action.triggered.connect(self.show_details)
        tool_bar.addAction(show_details_action)

        #Añadimos menu bar

        menu_bar = QMenuBar()
        self.setMenuBar(menu_bar)


        add_action_no_icon = QAction('&Añadir libro', self)
        add_action_no_icon.triggered.connect(self.show_form)
        menu_bar.addAction(add_action_no_icon)

        edit_action_no_icon = QAction('&Editar libro', self)
        edit_action_no_icon.triggered.connect(self.show_form)
        menu_bar.addAction(edit_action_no_icon)

        delete_action_no_icon = QAction('&Eliminar libro', self)
        delete_action_no_icon.triggered.connect(self.delete_libro)
        menu_bar.addAction(delete_action_no_icon)

        show_details_action_no_icon = QAction('&Ver detalles', self)
        show_details_action_no_icon.triggered.connect(self.show_details)
        menu_bar.addAction(show_details_action_no_icon)

        # Tabla para mostrar libros
        self.table = QTableWidget()
        self.table.setColumnCount(3)
        self.table.setHorizontalHeaderLabels(["Título", "Autor","Estado"])


        # Política para ajustar la tabla al ancho disponible
        self.table.setSizePolicy(QSizePolicy.Expanding, QSizePolicy.Expanding)
        self.table.setVerticalScrollBarPolicy(Qt.ScrollBarAlwaysOn)
        self.table.horizontalHeader().setSectionResizeMode(0, QHeaderView.Stretch)
        self.table.horizontalHeader().setSectionResizeMode(1, QHeaderView.Stretch)
        self.table.horizontalHeader().setSectionResizeMode(2, QHeaderView.Stretch)

        # Permite selecciones únicas y selecciona toda la fila
        self.table.setSelectionBehavior(QAbstractItemView.SelectRows)
        self.table.setSelectionMode(QAbstractItemView.SingleSelection)

        # Tabla de solo lectura
        self.table.setEditTriggers(QAbstractItemView.NoEditTriggers)
        central_layout.addWidget(self.table)
        self.show_libros()

    # Función que crea el formulario dependiendo de la acción.
    def show_form(self):
        action = self.sender()
        action_text = action.text().split(" ")
        print('creando formulario desde: ', action_text[0])

        self.form_window = Form()
        if action_text[0]=="&Añadir":
            self.form_window.datos_form.connect(self.add_libro)
            self.form_window.show()
        else:
            if not self.table.selectionModel().hasSelection():
                QMessageBox.information(self, "Selecciona un libro","Necesitas seleccionar un libro para poder editarlo " )
            else:
                self.form_window.datos_form.connect(self.edit_libro)
                self.form_window.show()


    def add_libro(self, datos):
        self.datos_libro = datos
        if self.datos_libro['img'] == '':
            self.datos_libro['img'] = 'D:/GitHub/proyectos-dam/DI/Proyecto/src/assets/book.jpg'
        print("Añadiendo: ", datos)
        try:
            con = sqlite3.connect('libros.db')
            cursor = con.cursor()
            cursor.execute("""
            INSERT INTO libros (titulo, autor, descripcion, img_path, estado)
            VALUES (?, ?, ?, ?, ?)
            """, (self.datos_libro['title'],self.datos_libro['author'], self.datos_libro['description'], self.datos_libro['img'], self.datos_libro['state']))
            con.commit()
            con.close()
            QMessageBox.information(self,"Enhorabuena","Libro añadido a la colección")
        except sqlite3.Error as e:
            print(e)
            QMessageBox.warning(self,"Error al añadir", "Algo no fue como debería por favor escriba un mensaje al administrador")
        self.show_libros()

    def edit_libro(self, datos):
        selected_row = self.table.currentRow()
        print("fila seleccionada: ", selected_row)
        self.datos_libro = datos
        if self.datos_libro['img'] == '':
            self.datos_libro['img'] = 'D:/GitHub/proyectos-dam/DI/Proyecto/src/assets/book.jpg'
        print('Editando libro: ',datos)

        libro = self.table.item(selected_row,0).text()
        respuesta = QMessageBox.question(self,"Editando libro",
                                         f"Vas a editar {libro}, una vez hecho los cambios no pueden deshacerse, asegurate de tener una copia a mano",
                                         QMessageBox.Yes | QMessageBox.No)
        if respuesta == QMessageBox.Yes:
            try:
                con = sqlite3.connect('libros.db')
                cursor = con.cursor()
                cursor.execute(
                    """
                    UPDATE libros SET titulo =?,
                    autor =?,
                    descripcion =?,
                    img_path =?,
                    estado =?
                    WHERE titulo = ?
                    """,(self.datos_libro['title'],self.datos_libro['author'], self.datos_libro['description'], self.datos_libro['img'], self.datos_libro['state'], libro))
                con.commit()
                con.close()
                QMessageBox.information(self, "Enhorabuena", "Libro editado con exito")
                self.show_libros()
            except sqlite3.Error as e:
                print(e)
                QMessageBox.warning(self,"Error al editar","Algo fue mal editando el libro")

    def show_libros(self):
        print('Actualizando libro')
        con = sqlite3.connect("libros.db")
        cursor = con.cursor()
        cursor.execute("SELECT titulo, autor, estado  FROM libros")
        libros = cursor.fetchall()
        con.close()
        self.table.setRowCount(len(libros))

        for row, (titulo, autor, estado) in enumerate(libros):

            item_titulo = QTableWidgetItem(titulo)
            item_autor = QTableWidgetItem(autor)
            label_estado = CustomLabel(estado)
            label_estado.setAlignment(Qt.AlignCenter)

            item_titulo.setTextAlignment(Qt.AlignCenter)
            item_autor.setTextAlignment(Qt.AlignCenter)

            self.table.setItem(row, 0, item_titulo)
            self.table.setItem(row, 1, item_autor)
            self.table.setCellWidget(row, 2, label_estado)

    def delete_libro(self):
        print('Eliminando libro')
        if not self.table.selectionModel().hasSelection():
            QMessageBox.information(self, "Selecciona un libro", "Necesitas seleccionar un libro para poder eliminarlo ")
        else:
            selected_row = self.table.currentRow()
            libro = self.table.item(selected_row, 0).text()
            respuesta = QMessageBox.question(self, "Eliminando libro",
                                             f"Vas a eliminar {libro}, una vez hecho los cambios no pueden deshacerse,"
                                             f" asegurate de tener una copia a mano",
                                             QMessageBox.Yes | QMessageBox.No)
            if respuesta == QMessageBox.Yes:
                try:
                    con = sqlite3.connect('libros.db')
                    cursor = con.cursor()
                    cursor.execute("DELETE FROM libros WHERE titulo = ?", (libro,))
                    con.commit()
                    con.close()
                    QMessageBox.information(self, "Libro eliminado con éxito", "El libro ha sido eliminado")
                    self.show_libros()
                except sqlite3.Error as e:
                    print(e)
                    QMessageBox.warning(self,"Error al eliminar", "Error al eliminar el libro")

    def show_details(self):
        print('Mostrando details')
        if not self.table.selectionModel().hasSelection():
            QMessageBox.information(self, "Selecciona un libro", "Necesitas seleccionar un libro para poder mostrar detalles del mismo ")
        else:
            selected_row = self.table.currentRow()
            libro = self.table.item(selected_row, 0).text()
            con = sqlite3.connect("libros.db")
            cursor = con.cursor()
            cursor.execute("SELECT titulo, autor, descripcion ,img_path, estado FROM libros WHERE titulo = ?", (libro,))
            libros = cursor.fetchone()
            print()
            cursor.close()
            con.close()


            datos = {
                'title': libros[0],
                'author': libros[1],
                'description': libros[2],
                'img': libros[3],
                'state': libros[4]
            }

            print(datos)

            self.details_window = Details(datos)
            self.details_window.show()
            self.details_window.signal_actualizado.connect(self.show_libros)




class Form(QWidget):

    texto_vacio = Signal(str)
    datos_form = Signal(dict)

    def __init__(self):
        super().__init__()

        self.setWindowTitle("Formulario")
        layout =QFormLayout()

        self.setLayout(layout)

        self.datos = {}

        self.texto_vacio.connect(self.check_vacio)

        self._title_input = QLineEdit()
        self._title_input.setPlaceholderText("Nombre de libro...")

        self._author_input = QLineEdit()
        self._author_input.setPlaceholderText("Nombre de autor...")

        self._descripcion_input = QTextEdit()
        self._descripcion_input.setPlaceholderText("Introduce una descripción...")

        self._img_path_input = QPushButton('Selecciona imagen')
        self._img_path_input.clicked.connect(self.get_img_path)
        self._img_path =""

        self._estado_input = QComboBox()
        self._estado_input.addItems(["Sin empezar","Leyendo","Finalizado"])

        enviar = QPushButton('Enviar')
        enviar.clicked.connect(self.enviar)

        cancelar = QPushButton('Cancelar')
        cancelar.clicked.connect(self.cancelar)

        btn_layout = QHBoxLayout()
        btn_layout.addWidget(enviar)
        btn_layout.addWidget(cancelar)

        layout.addRow(QLabel('Titulo: '),self._title_input)
        layout.addRow(QLabel('Autor: '),self._author_input)
        layout.addRow(QLabel('Description: '),self._descripcion_input)
        layout.addRow(QLabel('Imagen: '),self._img_path_input)
        layout.addRow(QLabel('Estado: '),self._estado_input)
        layout.addRow(btn_layout)

    def enviar(self):

        title = self._title_input.text()
        author = self._author_input.text()
        descripcion = self._descripcion_input.toPlainText()
        img = self._img_path
        estado = self._estado_input.currentText()

        if title and author:
            self.datos['title'] = title
            self.datos['author'] = author
            self.datos['description'] = descripcion
            self.datos['img'] = img
            self.datos['state'] = estado
            self.datos_form.emit(self.datos)
            self.close()
        else:
            self.texto_vacio.emit('Titulo vacio')
            QMessageBox.information(self,'Titulo vacio',"título y autor no pueden estar vacios")

        print('Enviando libro')

    def cancelar(self):
        print('Cancelando libro')
        info = QMessageBox.information(self,"Cerrar","¿Esás seguro que quieres salir?",QMessageBox.Yes | QMessageBox.No)
        if info == QMessageBox.Yes:
            self.close()

    def get_img_path(self):
        print('Seleccionando imagen')
        dialog = QFileDialog.getOpenFileName(self,caption='Abrir archivo...',
        dir='.',
        filter='JPG (*.jpg);',
        selectedFilter='JPG (*.jpg)')

        if dialog[0]:
            self._img_path = dialog[0]

    @Slot(str)
    def check_vacio(self):
        self._title_input.setStyleSheet("border: 1px solid  red; border-radius: 5px; padding: 5px;")
        self._author_input.setStyleSheet("border: 1px solid  red; border-radius: 5px; padding: 5px;")


class Details(QWidget):

    signal_actualizado =Signal(str)

    def __init__(self, datos_libro):

        super().__init__()
        self.setWindowTitle("Detalles")
        self.setGeometry(730, 300, 400, 300)
        self.layout = QGridLayout()
        self.setLayout(self.layout)

        self.form_window = None

        self.datos_recibidos = datos_libro

        imagen = self.datos_recibidos['img']

        if imagen:
            label_imagen = QLabel(self)
            pixmap = QPixmap(imagen)
            label_imagen.setPixmap(pixmap.scaled(150, 150))
        else:
            label_imagen = QLabel("No hay imagen asociada a este libro")

        label_titulo = QLabel(f"<b>Título:</b><br> {self.datos_recibidos.get('title')}")
        label_autor = QLabel(f"<b>Autor:</b><br> {self.datos_recibidos.get('author')}")
        label_descripcion = QLabel(f"<b>Descripción:</b><br> {self.datos_recibidos.get('description') or 'No hay descripción'}")
        label_estado = QLabel("<b>Estado:</b>")
        label_custom = CustomLabel(f"{self.datos_recibidos.get('state')}")



        self.layout.addWidget(label_imagen,0,0,5,1)
        self.layout.addWidget(label_titulo,0,1)
        self.layout.addWidget(label_autor,1,1)
        self.layout.addWidget(label_descripcion,2,1)
        self.layout.addWidget(label_estado,3,1)
        self.layout.addWidget(label_custom,4,1)

        btn_actualizar = QPushButton('Actualizar')
        btn_actualizar.clicked.connect(self.show_form)
        self.layout.addWidget(btn_actualizar,5,0,1,2)

    def show_form(self):
        print('Enviando form')
        self.form_window = Form()
        self.form_window.show()
        self.form_window.datos_form.connect(self.update_datos)

    def update_datos(self, datos_form):
        self.datos_libro = datos_form
        print('Enviando datos ', datos_form)
        if self.datos_libro['img'] == '':
            self.datos_libro['img'] = 'D:/GitHub/proyectos-dam/DI/Proyecto/src/assets/book.jpg'

        respuesta = QMessageBox.question(self, "Editando libro",
                                         f"Vas a editar {self.datos_recibidos.get('title')}, una vez hecho los cambios no pueden deshacerse, asegurate de tener una copia a mano",
                                         QMessageBox.Yes | QMessageBox.No)
        if respuesta == QMessageBox.Yes:
            try:
                con = sqlite3.connect('libros.db')
                cursor = con.cursor()
                cursor.execute(
                    """
                    UPDATE libros SET titulo =?,
                    autor =?,
                    descripcion =?,
                    img_path =?,
                    estado =?
                    WHERE titulo = ?
                    """,
                    (self.datos_libro['title'],self.datos_libro['author'], self.datos_libro['description'], self.datos_libro['img'], self.datos_libro['state'], self.datos_recibidos['title']))
                con.commit()
                con.close()
                QMessageBox.information(self, "Enhorabuena", "Libro editado con exito")
                self.signal_actualizado.emit("Libro editado con exito")
                self.close()

            except sqlite3.Error as e:
                print(e)
                QMessageBox.warning(self, "Error al editar", "Algo fue mal editando el libro")

class CustomLabel(QLabel):
    estado_lectura = Signal(str)
    def __init__(self, texto):
        super().__init__()
        self.__text = texto
        self.__color_text = self.set_color()
        self.setText(texto)
        self.setStyleSheet(f"color: {self.__color_text};")
        self.estado_lectura.connect(self.cambiar_color)

    @Property(str)
    def texto(self):
        return self.__text

    @texto.setter
    def texto(self, texto):
        self.__text = texto
        self.__color_text = self.set_color()
        self.estado_lectura.emit(texto)

    @Slot(str)
    def cambiar_color(self):
        print('Cambiando color ', self.__text, self.__color_text, sep =' ')
        self.setText(self.__text)
        self.setStyleSheet(f"color: {self.__color_text}; text-align: center;")

    def set_color(self):
        if self.__text == "Sin empezar":
            color = "black"
        elif self.__text == "Leyendo":
            color = "blue"
        else:
            color = "green"
        return color




def apply_stylesheet(application):
    application.setStyleSheet(
        """
        * {
            font-size: 15px;
        }
        
        QMenuBar { font-size: 18px; }
        
        """
    )

if __name__ == '__main__':
    create_db()
    app = QApplication([])
    icon_app_path = os.path.join(os.path.dirname(__file__), "assets\\app-icon.png")
    apply_stylesheet(app)
    app.setWindowIcon(QIcon(icon_app_path))
    window = MainWindow()
    window.show()
    sys.exit(app.exec())