package principal;

import threads.Sumatorio;

public class MainSecuencial {

    public static void main(String[] args) {

        Long time = System.currentTimeMillis()/1000 ;
        System.out.println("Hora de empezar con el time: " + (System.currentTimeMillis()/1000 - time)  + " segundos");
        Sumatorio s1 = new Sumatorio(23);
        Sumatorio s2 = new Sumatorio(13);
        Sumatorio s3 = new Sumatorio(15);


        double suma =  s1.sumaImpar() + s2.sumaImpar() + s3.sumaImpar();
        double formula = suma/8;

        System.out.println("El resultado de la fórmula es: " + formula + " y he tardado : " + (System.currentTimeMillis()/1000  - time) + " segundos");
    }
}
