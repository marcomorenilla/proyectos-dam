from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QLabel, QMainWindow, QMenuBar,
    QPlainTextEdit, QPushButton, QSizePolicy, QStatusBar,
    QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(468, 272)
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.lblContador = QLabel(self.centralwidget)
        self.lblContador.setObjectName(u"lblContador")
        self.lblContador.setGeometry(QRect(240, 10, 49, 16))
        self.btnIncrementar = QPushButton(self.centralwidget)
        self.btnIncrementar.setObjectName(u"btnIncrementar")
        self.btnIncrementar.setGeometry(QRect(50, 60, 75, 24))
        self.btnDecrementar = QPushButton(self.centralwidget)
        self.btnDecrementar.setObjectName(u"btnDecrementar")
        self.btnDecrementar.setGeometry(QRect(210, 60, 75, 24))
        self.btnResetear = QPushButton(self.centralwidget)
        self.btnResetear.setObjectName(u"btnResetear")
        self.btnResetear.setGeometry(QRect(370, 60, 75, 24))
        self.plainTextEdit = QPlainTextEdit(self.centralwidget)
        self.plainTextEdit.setObjectName(u"plainTextEdit")
        self.plainTextEdit.setGeometry(QRect(150, 110, 201, 31))
        self.btnSumar = QPushButton(self.centralwidget)
        self.btnSumar.setObjectName(u"btnSumar")
        self.btnSumar.setGeometry(QRect(90, 170, 75, 24))
        self.btnRestar = QPushButton(self.centralwidget)
        self.btnRestar.setObjectName(u"btnRestar")
        self.btnRestar.setGeometry(QRect(350, 170, 75, 24))
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 468, 33))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
        self.lblContador.setText(QCoreApplication.translate("MainWindow", u"0", None))
        self.btnIncrementar.setText(QCoreApplication.translate("MainWindow", u"Incrementar", None))
        self.btnDecrementar.setText(QCoreApplication.translate("MainWindow", u"Decrementar", None))
        self.btnResetear.setText(QCoreApplication.translate("MainWindow", u"Resetear", None))
        self.btnSumar.setText(QCoreApplication.translate("MainWindow", u"Sumar", None))
        self.btnRestar.setText(QCoreApplication.translate("MainWindow", u"Restar", None))
    # retranslateUi