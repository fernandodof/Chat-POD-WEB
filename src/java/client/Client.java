/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class Client {

    private Socket socket;
    private final InetSocketAddress inetSocketAddress;
    private PrintWriter printWriter;
    private String messageClient;
    
    public Client(String ip, int port){
        this.inetSocketAddress = new InetSocketAddress(ip, port);
    }
    
    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public String getMessageClient() {
        return messageClient;
    }

    public void setMessageClient(String messageClient) {
        this.messageClient = messageClient;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public PrintWriter getPrintWriter(){
        return this.printWriter;
    }
    
    public void sendMessage(String message){
        try {
            this.socket = new Socket(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
            PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream());
            //String msg = scanner.nextLine();
            printWriter.println(message);
            printWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public class ThreadClass implements Runnable{
//
//    private Socket socket;    
//    
//    public ThreadClass(Socket socket){
//        this.socket = socket;
//    }
//    
//    @Override
//    public void run() {
//        while(true){
//            try {
//                this.socket = new Socket(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
//                PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream());
//                System.out.print("Insert message: ");
//                String msg = scanner.nextLine();
//                printWriter.println(msg);
//                printWriter.flush();
//            } catch (IOException ex) {
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//    }
    
//    public static void main(String[] args) {
//        new Client("localhost", 10999);
//    }
//    
    } 
    
    
