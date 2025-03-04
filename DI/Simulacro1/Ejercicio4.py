class Car():
    def arrancar(self):
        self.en_marcha = True
    def detener(self):
        self.en_marcha = False
    def estado(self):
        return "En marcha" if self.en_marcha else "Detenido"
