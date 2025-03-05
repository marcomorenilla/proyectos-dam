import java.security.Provider;
import java.security.Security;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------------------");
        System.out.println("PROVEEDORES DEL JCA");
        System.out.println("---------------------");

        Provider[] lista = Security.getProviders();
        for (Provider p : lista) {
            System.out.println("Nombre: "+p.getName());
            System.out.println("Version: "+p.getVersionStr());
            System.out.println("Info: "+p.getInfo());
        }


    }
}