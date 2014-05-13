/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

/**
 *
 * @author Fernando
 */
import chatServer.Server;
import client.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatManager {

    private Client client;
    private Vector<String> serverMessages = new Vector();
    private static HashMap<String,Vector<String>> clientMessages = new HashMap();
    private static ChatManager instance = null;

    public static ChatManager getInstance() {
        if (instance == null) {
            instance = new ChatManager();
        }
        return instance;
    }

    private ChatManager() {
        Thread t = new Thread(new ServerThread());
        t.start();
    }
    
    private class ServerThread implements Runnable{

        @Override
        public void run() {
            Server server = Server.getInstance();
        }
    }
   
    public Vector<String> getClientMessages(String login) {
        return clientMessages.get(login);
    }

    public String createClient(String ip, int port, String loginMessage) {
        String message = null;
        client = new Client(ip, port);
        try {

            client.sendMessage(loginMessage);
            InputStreamReader inputStreamReader = new InputStreamReader(this.client.getSocket().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine().trim();
            if(message.equalsIgnoreCase("ok")){
                clientMessages.put(loginMessage.replace("-l@", "").split("&")[0], new Vector<String>());
           }
        } catch (IOException ex) {
            Logger.getLogger(ChatManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public void sendMessage(String message) {
        client.sendMessage(message);
        clientMessages.get(message.replace("-m@", "").split("&")[0]).add("Você disse: " + message.replace("-m@", "").split("&")[1]);
    }

    public void addServerMessages(Vector msgs){
        serverMessages = msgs;
    }
    
    public Vector getServerMessages(){
        return serverMessages;
    }
}