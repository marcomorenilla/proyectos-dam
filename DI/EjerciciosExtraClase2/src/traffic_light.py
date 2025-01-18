# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'traffic-light.ui'
##
## Created by: Qt User Interface Compiler version 6.8.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QFrame, QLabel, QMainWindow,
    QMenuBar, QPushButton, QSizePolicy, QStatusBar,
    QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.btnCambiar = QPushButton(self.centralwidget)
        self.btnCambiar.setObjectName(u"btnCambiar")
        self.btnCambiar.setGeometry(QRect(290, 150, 101, 101))
        self.lblRed = QLabel(self.centralwidget)
        self.lblRed.setObjectName(u"lblRed")
        self.lblRed.setGeometry(QRect(450, 50, 101, 91))
        self.lblRed.setFrameShape(QFrame.Shape.Box)
        self.lblYellow = QLabel(self.centralwidget)
        self.lblYellow.setObjectName(u"lblYellow")
        self.lblYellow.setGeometry(QRect(450, 150, 101, 91))
        self.lblYellow.setFrameShape(QFrame.Shape.Box)
        self.lblGreen = QLabel(self.centralwidget)
        self.lblGreen.setObjectName(u"lblGreen")
        self.lblGreen.setGeometry(QRect(450, 250, 101, 91))
        self.lblGreen.setFrameShape(QFrame.Shape.Box)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 800, 33))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
        self.btnCambiar.setText(QCoreApplication.translate("MainWindow", u"Pulsar", None))
        self.lblRed.setText("")
        self.lblYellow.setText("")
        self.lblGreen.setText("")
    # retranslateUi

