package src.asimetric;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Firma {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        //Necesitamos la clave privada para firmar un documento/texto
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

        //Texto que firmamos
        String mensaje = "No nos queda nada....";
        //https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#Signature
        //A firmar
        Signature firma = Signature.getInstance("SHA256withRSA");
        firma.initSign(privateKey);
        firma.update(mensaje.getBytes());
        byte[] firmaDelTexto = firma.sign();

        System.out.println("Mensaje que se va a firmar: ");
        System.out.println(mensaje);
        System.out.println("Firma");
        System.out.println(Base64.getEncoder()
                .encodeToString(firmaDelTexto));

    }
}
