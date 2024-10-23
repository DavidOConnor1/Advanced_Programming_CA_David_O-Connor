/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversidetcp;

/**
 *
 * @author dmoc2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThreadClass implements Runnable { //start client class
    
    String clientName;
    Socket link = null;
    PrintWriter out;
    BufferedReader in;
    
    private ArrayList<String> events = new ArrayList<>();

    public ClientThreadClass(String clientName, Socket link) {
        this.clientName = clientName;
        this.link = link;
    }
    
     
        
    
    
    @Override
    public void run()
    {//start runnable method
       try{ 
      
            in = new BufferedReader(new InputStreamReader(link.getInputStream())); //reads the values sent from the stream
            out = new PrintWriter(link.getOutputStream(), true); //sends out values 
            basicOperation(); //loads the normal operation that is required for the multithreading clients
       } catch(IOException e) {
           e.printStackTrace();
       }     
        
        
        /*
        finally{ //start finally block
            
            try
            {//start try block
                System.out.println("\n Closing the Connection...");
                link.close(); //closes the connection when the client is finished 
                
            }//end try block
            catch(IOException ex)
            {//start catch block
                System.out.println("Unable to Disconnect from Server"); //lets the Server know the that client cannot disconnect
              //  System.exit(1); // will exit the entire system if the client cannot discounnect from the server 
            }//end try block
        }//end finally block
        */
    } //end runnable method
    
    
    private void basicOperation()
    {
                try{
                    String command;
            do{
          
            
            //BufferedReader userResponse = new BufferedReader(new InputStreamReader(System.in));
            
            command = in.readLine(); //read the command from the user which will be either add, list or remove
           
            
            System.out.println("Command recieved from client" +clientName+" "+command); //this message is to let us check what command has come through the server 
            
            if(command.equalsIgnoreCase("add"))
            {
                addingOperation();
            } else if (command.equalsIgnoreCase("remove"))
            {
                removingOperation();
            } 
            if(command.equalsIgnoreCase("stop"))
            {
                System.exit(0);
            }
            
            
            //out.println("Echo Message: "+ command);
            } while(true);
           
            
           
            
           
            
        } catch(IOException ex)
        {
            ex.printStackTrace(); //will print out the errors 
        } 
    }//end of basic operation
    
    
    private void addingOperation()
    {
          
    }
    
    
    private void removingOperation()
    {
        
    }
}//end client class 
