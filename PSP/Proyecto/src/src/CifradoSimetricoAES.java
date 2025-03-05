import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CifradoSimetricoAES {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        //Texto a cifrar
        String texto = "En un lugar de la mancha";

        //Generar la clave de forma interna y aleatoria: AES y junto con su tamaño()
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //keygen.init(128);
        //Llave que se comparte con el destinatario
        //SecretKey clave = keygen.generateKey(); //la genera la propia JCA

        //Genera la clave de una forma directa: 16, 24 y 32 el tamaño de la clave
        byte[] claveString ="1234567891012345".getBytes();
        SecretKey clave = new SecretKeySpec(claveString, "AES/CBC/PKCS5Padding");
        // https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html
        //Ciframos con la clave privada... problema al compartirlo
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,clave);
        byte[] textoBytesCifrado = cipher.doFinal(texto.getBytes());
        //Para mostrar el texto, se suele utilizar siempre Base64
        String textoCifrado = Base64.getEncoder().encodeToString(textoBytesCifrado);
        System.out.println(textoCifrado);

        //Desciframos el texto con la clave privada
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] textoBytesDescifrado =
                cipher.doFinal(
                        Base64.getDecoder().decode(textoCifrado)
                );
        String textoDescifrado = new String(textoBytesDescifrado);
        System.out.println(textoDescifrado);
    }
}
