package chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import loginService.LoginService;
import manager.ChatManager;


/**
 *
 * @author Fernando
 */
public class Server{
    private ServerSocket serverSocket;
    private Socket socket;
    private static ArrayList<String> messages;
    private static Server instance=null;
    
    private static int port=10999;
    
    public static Server getInstance(){
        if(instance==null){
            instance = new Server(port);
        }
        return instance;
    }
    
    private Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            try {
                this.socket = serverSocket.accept();
                System.out.println("Conected");
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            Thread thread = new Thread(new ThreadClass(this.socket));
            thread.start();
        }    }
    
    public static ArrayList<String> getMessages(){
          if (messages == null){
            return messages = new ArrayList<>();
          }else {
            return messages;
          }
    }
    
    public class ThreadClass implements Runnable{
    
    private Socket socket;    
        
    public ThreadClass(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run() {
            String login = null;
            String msg;
            String[] messageParts;
            String menssage = null;
            String serverResponse;
            
            while(true){
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(this.socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    
                    msg = bufferedReader.readLine();

                    if (msg.startsWith("-l@")) {
                        if (LoginService.validateLogin(msg)) {
                            serverResponse = "OK";
                        } else {
                            serverResponse = "Invalid Credentials";
                        }
                    System.out.println(serverResponse);
//                    socket.getOutputStream().write(serverResponse.getBytes());
                        PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream());
                        printWriter.println(serverResponse);
                        printWriter.flush();
                    } else if (msg.startsWith("-m@")) {
                        msg = msg.replace("-m@", "");
                        messageParts = msg.split("&");
                        login = messageParts[0];
                        menssage = messageParts[1];
                        serverResponse = login + " disse: " + menssage;
                        //2 times
                        getMessages().add(serverResponse);
                        System.out.println(serverResponse);
                    } else {
                        serverResponse = "INVALID COMMUNICATION PROTOCOL";
                        System.out.println(serverResponse);
                    }
                                        
                } catch (IOException ex) {
                    Logger.getLogger(Server1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
}
    
public static void main(String[] args) {
    //new Server(10999);
}   

}