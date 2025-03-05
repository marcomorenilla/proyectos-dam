package src.asimetric;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class VerificarFirma {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        //Necesitamos la clava pública de la persona que verificamos
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dame la clave publica ");
        String clave = teclado.nextLine();
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

        //Necesitamos el texto
        System.out.println("Dame el texto que se ha firmado: ");
        String mensaje = teclado.nextLine();

        //Necesitamos la firma del texto
        System.out.println("Dame la firma que ha generado sobre ese texto: ");
        String firmaDelTexto = teclado.nextLine();
        Signature firma = Signature.getInstance("SHA256withRSA");
        byte[] firmaDelTextoBytes = Base64.getDecoder().decode(firmaDelTexto);
        firma.initVerify(publicKey);
        firma.update(mensaje.getBytes());

        boolean firmaCorrecta = firma.verify(firmaDelTextoBytes);
        if (firmaCorrecta){
            System.out.println("Firma verificada correctamente");
        }else{
            System.out.println("La firma sobre ese texto no es fiable");
        }



    }
}
