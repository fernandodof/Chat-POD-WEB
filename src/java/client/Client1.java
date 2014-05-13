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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class Client1 extends Thread{

    private Socket socket;
    private final InetSocketAddress inetSocketAddress;
    //
    private final Scanner scanner = new Scanner(System.in);
    
    public Client1(String ip, int port){
        this.inetSocketAddress = new InetSocketAddress(ip, port);
        start();
    }
    
    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }
     
    @Override
    public void run(){
        try {
           while(true){ 
                this.socket = new Socket(this.inetSocketAddress.getHostName(),this.inetSocketAddress.getPort());
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                String msg;
                System.out.print("Insert message: ");
                msg = scanner.nextLine();
                printWriter.println(msg);
                printWriter.flush();
                System.out.println("Client Sent message");
           }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Client1("localhost", 10999);
    }
}