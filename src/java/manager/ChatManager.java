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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatManager {

    private static Client client;
    private static ArrayList<String> clientMessages = new ArrayList();
    private static ArrayList<String> serverMessages = new ArrayList();
    private static ArrayList<String> serverMessages1 = new ArrayList();
    //private static final Server server = new Server(10999);

    private static ChatManager instance = null;

    public static ChatManager getInstance() {
        if (instance == null) {
            instance = new ChatManager();
            Server server = Server.getInstance();
        }
        return instance;
    }

    private ChatManager() {
       
    }

    public ArrayList<String> getClientMessages() {
        return clientMessages;
    }

    public String createClient(String ip, int port, String loginMessage) {
        InputStream inputStream = null;
        String message = null;
        client = new Client(ip, port);
        try {

            client.sendMessage(loginMessage);
            //Getting answer from server
//            inputStream = client.getSocket().getInputStream();
//            byte[] b = new byte[1024];
//            inputStream.read(b);
//            message = new String(b);
            InputStreamReader inputStreamReader = new InputStreamReader(this.client.getSocket().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine();
            
        } catch (IOException ex) {
            Logger.getLogger(ChatManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message.trim();
    }

    public void sendMessage(String message) {
        client.sendMessage(message);
        clientMessages.add("Você disse: " + message.replace("-m@", "").split("&")[1]);
    }

    public void addServerMessage(String message){
//        System.out.println("M: "+ message);
//        System.out.println("Insertion: "+serverMessages.add(message));
//        System.out.println("S: "+ serverMessages.size());
        serverMessages.add(message);
    }
    
    public ArrayList<String> getServerMessages(){
        return serverMessages;
    }
}
