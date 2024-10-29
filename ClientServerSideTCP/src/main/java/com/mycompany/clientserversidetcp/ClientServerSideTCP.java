/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clientserversidetcp;

/**
 *
 * @author dmoc2
 */

import java.io.*;
import java.net.*;
import java.net.Socket;

public class ClientServerSideTCP {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) { //start main block
        try
        {//start try block
            host = InetAddress.getLocalHost();
        }// end try block
        catch(UnknownHostException e)
        { //start catch block
            System.out.println("Host ID is not found");
            System.exit(1);
        } //end catch block
        clientServer();
    }// end main block
    
    private static void clientServer()
    {//start clientServer
        Socket link = null; //intializing the link variable
        try
        {//start try block
            
            link = new Socket(host, PORT); //establishing a host and a port number to connect to as a client 
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream())); // Retrives messages from the Server side
            PrintWriter out = new PrintWriter(link.getOutputStream(), true);
            
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in)); //this will be for user entries (currently going to be used for testing to ensure the messages go through until I move over to the event Scheduler)
            String message ;
            String response;
           
            System.out.println("Connected to Server");
            System.out.println("Enter commands (add <event>, remove <event>, list, exit):");
            
           
            
            while(true){
            message = userEntry.readLine();
           
              if("stop".equalsIgnoreCase(message))
            {
                break;
            }
             out.println(message); //sends a stream of the message data to the server
            response = in.readLine(); //brings the server response to display that it has gone through
            System.out.println("\n Server Response: "+response);
            }
          
            
            
           
           
            
        }//end try block
        catch(IOException e)
        {//open catch block
            e.printStackTrace();
        }//close catch block
      
        finally
        {//open finally block
           try
           {//open try block
            System.out.println("\n closing connection");
            link.close();
           }//end try block
           catch(IOException e)
           { //open catch block
                System.out.println("Unable to disconnect/close");
                System.exit(1);
           } //end try block
        }//end finally block
       
    }//end clientServer
}//end client server class
