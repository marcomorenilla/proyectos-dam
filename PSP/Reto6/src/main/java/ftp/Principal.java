/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ftp;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author marco
 */
public class Principal {
    
    public static void main(String[] args) throws IOException {
        FTPClient ftpclient =  new FTPClient();
        
        //Conexión a FTP más modo pasivo.
        ftpclient.connect(Util.HOST);
        ftpclient.login(Util.USER, Util.PASS);
        ftpclient.enterLocalPassiveMode();
        
        // Compruebo que la conexión ha sido correcta.
        System.out.println("Código: "+ftpclient.getReplyCode());
        
        // Traigo lista de archivos.
        FTPFile[] files = ftpclient.listFiles();
        
        //Recorro lista y si encuentra el fichero lo intenta eliminar, en caso positivo, imprime confirmación.
        //Si no lo encuentra imprime nombre del archivo que tiene en la lista.
        for(FTPFile file : files){
            if(file.getName().equalsIgnoreCase("dametoldinero.exe")){
                System.out.println("Malware encontrado....");
                if(ftpclient.deleteFile("dameTolDinero.exe")){
                    System.out.println("Malware eliminado sin problemas.");
                }else{
                    System.out.println("No se pudo eliminar el malware llame a Damián.");
                } 
            } else {
                System.out.println("Nombre del archivo: "+ file.getName());
            }
        }   
    }   
}
