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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.ChatManager;

/**
 *
 * @author Fernando
 */
public class Client {
    private Socket socket;
    private final InetSocketAddress inetSocketAddress;

    public Client(String ip, int port){
        this.inetSocketAddress = new InetSocketAddress(ip, port);
    }
    
    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public Socket getSocket() {
        return socket;
    }
    
//    public void addMessage(String msg){
//        this.messages.add(msg);
//        try {
//            Thread.sleep((long) 30);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ChatManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public ArrayList<String> getMessages() {
//        return messages;
//    }
    
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
    
    
