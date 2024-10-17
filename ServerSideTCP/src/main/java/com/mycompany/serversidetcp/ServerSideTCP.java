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
    private static int PORT = 1111;
    private static int clientConnections =0;

    public static void main(String[] args) {//start main block 
        
        System.out.println("Opening Port Connection..."); //displaying server message to check if the class is running 
        
        try{
            
            servSock = new ServerSocket(PORT); //connecting the server to the port number provided above
            
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
    {
        Socket link = null;
        
        try
        {
            link = servSock.accept();
            clientConnections++;
            String clientName = "Client: "+clientConnections;
            Runnable Resource = new ClientThreadClass(clientName, link);
            Thread clientThread = new Thread(Resource);
            clientThread.start();
        }
        catch(IOException e)
        {
            
        }
    }
    
    
}//end class block

