package threads;

public class Sumatorio extends Thread{

    // Atributos de la clase
    private int num;
    private double resultado;

    // Constructor con un parámetro que es el número del sumatorio
    public Sumatorio(int num){
        this.num = num;
    }

    // Implementamos la lógica de  los hilos donde establecemos el resultado invocando a sumaImpar
    @Override
    public void run() {
        resultado = sumaImpar();
        System.out.println("Hilo "+Thread.currentThread().getName()+" con este resultado: "+resultado );
    }

    // Lógica con la que realizamos la operación
    public double sumaImpar(){
        if (num%2==0){
            num = num -1;
        }
        double total = 0;
        for (int i = num; i > 0; i=i-2){
            total+=i;
        }

        try {
            System.out.println("Hilo " + Thread.currentThread().getName()+" durmiendo 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return total;
    }

    // Utilizamos el getter para acceder al resultado obtenido con la operación
    public double getResultado() {
        return resultado;
    }
}
