package src.asimetric;

import java.security.*;
import java.util.Base64;

public class GeneradorParClaves {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //openssl genrsa -des3 -out private_key.key 2048
        //https://www.ibm.com/docs/es/license-metric-tool?topic=certificate-step-1-creating-private-keys-certificates
        //https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#KeyPairGenerator
        KeyPairGenerator keyGen =
                KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);

        //Generación de par claves
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //Mostrar por pantalla
        //Meterlo en un archivo (publica y privada)
        //Compartirlo con Sockets (pública), correo electrónico

        System.out.println("Clave pública:");
        System.out.println(Base64.getEncoder()
                .encodeToString(publicKey.getEncoded()));

       // System.out.println("Private Key: " + privateKey);
        System.out.println("Clave privada:");
        System.out.println(Base64.getEncoder()
                .encodeToString(privateKey.getEncoded()));

    }
}
