/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serversidetcp;

/**
 *
 * @author dmoc2
 */

import java.io.*;
import java.net.*;

public class ServerSideTCP {
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    private static int clientConnections = 0;

    public static void main(String[] args) {//start main block 
        Socket link = null;
        System.out.println("Opening Port Connection..."); //displaying server message to check if the class is running 
        
        try{
            while(true)
            {
                servSock = new ServerSocket(PORT); //connecting the server to the port number provided above
                System.out.println("The Server is now running on Port "+PORT);
                link = servSock.accept();
                clientConnections++;
         
                Runnable Resource = new ClientThreadClass(link);
                Thread clientThread = new Thread(Resource, "Client-"+clientConnections);
                clientThread.start();
            }
        }
        catch(IOException e)
        {
            System.out.println("Unable to connect to the port");
        }
        
     
    
        
        
    }//end main block
    
   
    
    
}//end class block

