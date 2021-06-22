/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author denis
 */
public class FileManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        
        Options opt = new Options();
        opt.fileManager();
   }
}
