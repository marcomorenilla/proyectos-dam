package src.asimetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DescifradoAsimetrico {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //Necesitamos la clave privada
        Scanner entrada = new Scanner(System.in);
        System.out.println("Dame la clave privada ");
        String clavePrivada = entrada.nextLine();

        PrivateKey privateKey = null;
        //Convertir algo a PrivateKey, tiene que estar en formato byte[]
        byte[] privateKeyByte = Base64.getDecoder().decode(clavePrivada);
        //Construimos la Clave Privada
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        privateKey = factory.generatePrivate(keySpec);
        //Verificación
        System.out.println(Base64.getEncoder()
                .encodeToString(privateKey.getEncoded()));

        //Necesitamos el mensaje cifrado con la clave pública
        System.out.println("Dame el mensaje cifrado ");
        String mensajeCifrado = entrada.nextLine();
        byte[] mensajeCifradoBytes = Base64.getDecoder().decode(mensajeCifrado);
        //Descifrar el mensaje
        Cipher descifrado = Cipher.getInstance("RSA");
        descifrado.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] mensajeDescrifrado = descifrado.doFinal(mensajeCifradoBytes);

        //Mostramos el mensaje cifrado
        System.out.println("Mensaje descifrado : ");
        System.out.println(new String(mensajeDescrifrado));

    }
}
