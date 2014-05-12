package chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import loginService.LoginService;

/**
 *
 * @author Fernando
 */
public class Server1{
    private ServerSocket serverSocket;
    private Socket socket;
    private ArrayList<String> messages = new ArrayList();
    
    public Server1(int port) {
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
        }
    }

//    public ArrayList<String> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(ArrayList<String> messages) {
//        this.messages = messages;
//    }
    
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
                    //PrintStream printStream = new PrintStream(this.socket.getOutputStream());
                    
                    msg = bufferedReader.readLine();
//                    System.out.println(msg);
//                    socket = serverSocket.accept();

//                    byte[] b = new byte[1024];
//                    InputStream input = socket.getInputStream();
//                    input.read(b);
//                    msg = new String(b).trim();
                    System.out.println(msg);
                    if (msg.startsWith("-l@")) {
                        if (LoginService.validateLogin(msg)) {
                            serverResponse = "OK";
                        } else {
                            serverResponse = "Invalid Credentials";
                        }
                    System.out.println(serverResponse);
                    socket.getOutputStream().write(serverResponse.getBytes());
                    } else if (msg.startsWith("-m@")) {
                        msg = msg.replace("-m@", "");
                        messageParts = msg.split("&");
                        login = messageParts[0];
                        menssage = messageParts[1];
                        serverResponse = login + " disse: " + menssage;

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
    new Server1(10999);
}   

}