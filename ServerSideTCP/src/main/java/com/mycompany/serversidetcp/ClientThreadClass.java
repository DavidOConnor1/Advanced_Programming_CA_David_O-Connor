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
import java.util.List;


public class ClientThreadClass implements Runnable { //start client class
    
    
    private Socket link = null;
    private PrintWriter out;
    private BufferedReader in;
    private List<String>events = new ArrayList<>();
    
    
    

    public ClientThreadClass( Socket link) 
    { 
        this.link = link;   
    }//
    
     
        
    
    
    @Override
    public void run()
    {//start runnable method
      
       try
       {//open try 
            in = new BufferedReader(new InputStreamReader(link.getInputStream())); //reads the values sent from the stream
            out = new PrintWriter(link.getOutputStream(), true); //sends out values 
            String command;
            
            while((command = in.readLine()) != null)
            {
                try
                {//open try
                System.out.println("command recieved: "+command);
                handleOperations(command); //runs operation in try block because of new exception
                }//close try 
                catch(IncorrectActionException e)
                {//open catch
                    out.println("Error: "+e.getMessage());
                }//close catch
            }//end while
          
       }//end try 
       catch(IOException e) 
       {//open catch
           e.printStackTrace();
       }//end catch     
        
        finally
        { //start finally block
            
            try
            {//start try block
                System.out.println("\n Closing the Connection...");
                link.close(); //closes the connection when the client is finished 
                
            }//end try block
            catch(IOException ex)
            {//start catch block
                System.out.println("Unable to Disconnect from Server"); //lets the Server know the that client cannot disconnect
                System.exit(1); // will exit the entire system if the client cannot discounnect from the server 
            }//end try block
        }//end finally block
        
    } //end runnable method
    
    private static final String eventFormat = "^[^\\d]+ \\d{2}/\\d{2}/\\d{4} (\\d{1,2}:\\d{2}(am|pm)?)$";

    
    private void handleOperations(String command) throws IncorrectActionException {
         String[] parts = command.split(" ", 2); //will read the space after the command and then add the event
         String executables = parts[0].toLowerCase(); //it is the first part of the command

            switch (executables) 
            {//open switch
                case "add"://open add operation
                    if (parts.length < 2) { //needs add and a event name
                        out.println("Usage: add <event>");
                    } else {
                        String event = parts[1]; //will take the event based on what comes after add
                        if(!event.matches(eventFormat))
                        {
                            out.println("invalid format. Please use: Event dd/mm/yyyy HH:mm");
                            break;
                        }
                        events.add(event); //adds the event to the list
                        out.println("Event added: " + event); //displays to the client the event has been added
                    }
                    break; //ends the action of add 
                case "remove": //start remove case
                    if (parts.length < 2) { //reads after the remove command has been entered
                        out.println("Usage: remove <event>"); //tells the user if they only enter the command that they need to add event too
                    } else { //open else
                        String event = parts[1]; //will store the entered event
                        if (events.remove(event)) //checks if the event is in the list
                        {//open if
                            out.println("Event removed: " + event); //removes event if the event was in the list
                        } else {
                            out.println("Event not found: " + event);//won't remove event if its not in the list
                        }
                    }//end else
                    break; //breaks out of action
 
                default:
                    throw new IncorrectActionException("Unknown command. Only add<event>, remove<event>"); //throws exception when user enters the wrong command
                    
            }//end switch
    }//end method
   
    
   
            
            
            
            
     
}//end client class 
