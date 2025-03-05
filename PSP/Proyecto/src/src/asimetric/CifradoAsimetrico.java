package src.asimetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class CifradoAsimetrico {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //Necesitamos la clave pública al que le vamos a cifrar el mensaje
        Scanner entrada = new Scanner(System.in);
        System.out.println("Dame la clave publica ");
        String clave = entrada.nextLine();
        PublicKey publicKey = null;

        //Convertir algo a PublicKey, tiene que estar en formato byte[]
        byte[] publicKeyBytes = Base64.getDecoder().decode(clave);
        //Construimos la Clave Pública
        KeyFactory factory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        publicKey = factory.generatePublic(keySpec);
        //Verificación
        System.out.println(Base64.getEncoder()
                .encodeToString(publicKey.getEncoded()));

        //Vamos a cifrar un cadena de texto para la persona que nos
        // ha proporcionado la clave pública

        String mensaje = "El examen de Gundi lo está viendo todo el mundo";

        //Cifrar el mensaje con la clave pública
        Cipher cifrado = Cipher.getInstance("RSA");
        cifrado.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cifradoBytes = cifrado.doFinal(mensaje.getBytes());
        //Mostramos el mensaje cifrado
        System.out.println("Mensaje cifrado: ");
        System.out.println(Base64.getEncoder()
                .encodeToString(cifradoBytes));

    }
}
