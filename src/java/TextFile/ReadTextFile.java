/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TextFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ReadTextFile {
   private static final File FilePath = new File("F://NetBeansProjects//ProjetoChatPodWeb/login.txt");
   private static final HashMap<String, String> UsersPasswords = new HashMap<>();
   
   private static void ReadingFile(){
       try {
           FileReader FileReader = new FileReader(FilePath);
           BufferedReader BufferedReader = new BufferedReader(FileReader);
           
           //Returns contents of the line or null if it's the end of the file
           String line = BufferedReader.readLine();
           //Stores into the map
           while (line != null) {
               //spliting login and password
               String[] usuarioSenha = line.split("\\|");
               //Storing into map
               UsersPasswords.put(usuarioSenha[0], usuarioSenha[1]);
               //Getting next line
               line = BufferedReader.readLine();
           }
           FileReader.close();

       } catch (FileNotFoundException ex) {
           Logger.getLogger(ReadTextFile.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(ReadTextFile.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public static Map<String, String> getMapWithLoginsAndPasswords(){
       ReadingFile();
       return UsersPasswords;
   }

}