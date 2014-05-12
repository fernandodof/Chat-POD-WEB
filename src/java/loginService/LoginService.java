/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loginService;

import TextFile.ReadTextFile;

/**
 *
 * @author Fernando
 */
public class LoginService {
    private static ReadTextFile readTextFile;
    
    public static boolean validateLogin(String loginPassword){
        boolean success = false;
        
        if(loginPassword.contains("&")){
            //Replacing the header
            loginPassword = loginPassword.replace("-l@", "");
            //Spliting the string into login and password
            String[] messageParts = loginPassword.split("&");
            String login = messageParts[0];
            String password = messageParts[1];
            //Checking if login and password are a match
            if(readTextFile.getMapWithLoginsAndPasswords().containsKey(login)&& readTextFile.getMapWithLoginsAndPasswords().get(login).equals(password)){
                success = true;
            }
        }else{
            success = false;
        }
    return success;
    }
}

