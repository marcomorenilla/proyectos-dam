import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FuncionResumenMD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        //Recogemos el archivo y nos quedamos con su contenido
        String ruta = "puttygen2.exe";
        byte[] contenido = Files.readAllBytes(Path.of(ruta));

        //MessageDigest: Clase para trabajar con Hash y aplicamos algoritmo
        MessageDigest md = MessageDigest.getInstance("SHA256");
        byte[] resultado = md.digest(contenido);

        //Transformarlo a Hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : resultado) {
            hexString.append(String.format("%02X", b));
        }

        //Mostrarlo
        System.out.println(hexString.toString().toLowerCase());

    }
}
