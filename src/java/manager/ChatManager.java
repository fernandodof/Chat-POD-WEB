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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatManager {

    private Client client;
    private ArrayList <Client> clientes = new ArrayList();
    private ArrayList<String> serverMessages = new ArrayList();
    //private List messages = Collections.synchronizedList(new ArrayList()); 
    private static HashMap<String,ArrayList<String>> clientMessages = new HashMap();
    //private static final Server server = new Server(10999);
    //CopyOnWriteArrayList<String>
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

    public ArrayList<String> getClientMessages(String login) {
        return clientMessages.get(login);
    }

    public String createClient(String ip, int port, String loginMessage) {
        InputStream inputStream = null;
        String message = null;
        client = new Client(ip, port,loginMessage.replace("-l@", "").split("&")[0]);
        try {

            client.sendMessage(loginMessage);
            //Getting answer from server
//            inputStream = client.getSocket().getInputStream();
//            byte[] b = new byte[1024];
//            inputStream.read(b);
//            message = new String(b);
            InputStreamReader inputStreamReader = new InputStreamReader(this.client.getSocket().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            message = bufferedReader.readLine().trim();
            if(message.equalsIgnoreCase("ok")){
                clientMessages.put(loginMessage.replace("-l@", "").split("&")[0], new ArrayList<String>());
                //clientes.add(client);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public void sendMessage(String message) {
        client.sendMessage(message);
        clientMessages.get(message.replace("-m@", "").split("&")[0]).add("Você disse: " + message.replace("-m@", "").split("&")[1]);
        try {
            Thread.sleep((long) 30);
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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