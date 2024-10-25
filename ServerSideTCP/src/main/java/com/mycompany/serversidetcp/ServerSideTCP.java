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
import java.util.ArrayList;

public class ServerSideTCP {
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    private static int clientConnections = 0;

    public static void main(String[] args) {//start main block 
        
        System.out.println("Opening Port Connection..."); //displaying server message to check if the class is running 
        
        try{
            
            servSock = new ServerSocket(PORT); //connecting the server to the port number provided above
            System.out.println("The Server is now running on Port "+PORT);
        }
        catch(IOException e)
        {
            System.out.println("Unable to connect to the port");
        }
        
     
      do
      {
        eventServer();
        
      }
      while(true);
        
        
    }//end main block
    
    private static void eventServer()
    {//start eventSever block
        Socket link = null;
        
        try
        {
            link = servSock.accept();
            clientConnections++;
            String name = " "+clientConnections;
        
            
            System.out.println("Connection Accepted from: "+name);
            
            
            
            Runnable Resource = new ClientThreadClass(name, link);
            Thread clientThread = new Thread(Resource, "Client-"+clientConnections);
            clientThread.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }//end eventServer block
    
    
}//end class block

