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
import java.util.StringTokenizer;

public class ClientThreadClass implements Runnable { //start client class
    
    private String clientName;
    private Socket link = null;
    private PrintWriter out;
    private BufferedReader in;
    private List<String>events;
    
    
    

    public ClientThreadClass(String clientName, Socket link) {
        this.clientName = clientName;
        this.link = link;
        this.events = new ArrayList<>();
        
        try
        {
            in = new BufferedReader(new InputStreamReader(link.getInputStream())); //reads the values sent from the stream
            out = new PrintWriter(link.getOutputStream(), true); //sends out values 
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
     
        
    
    
    @Override
    public void run()
    {//start runnable method
      
       try{ 
            String command;
            do{
                command = in.readLine();
                System.out.println("Command recieved from client: "+clientName+ ": "+command);
                
                if("add".equalsIgnoreCase(command))
                {
                    addingOperation();
                    out.println("Event Added Successfully");
                } else if("remove".equalsIgnoreCase(command))
                {
                    removingOperation();
                    out.println("Event Removed Successfully");
                } else if("stop".equalsIgnoreCase(command))
                {
                    out.println("Goodbye!");
                    break;
                    
                } else {
                    out.println("Command Does not exist");
                }
                
            } while(true);
          
       } catch(IOException e) {
           e.printStackTrace();
       }     
        
        
        
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
        
    } //end runnable method
    
    
   
    
    
    private void addingOperation()
    {
        try{
            boolean isValid=false;
            String eventDetails;
            while(!isValid){
                out.println("Please enter the event details in the format: EventName dd/mm/yyyy time: ");
        
                 eventDetails = in.readLine(); //reads the input from the user
                String dateFormat = "^[\\\\w\\\\s]+ \\\\d{2}/\\\\d{2}/\\\\d{4} \\\\d{2}:\\\\d{2}[APap][Mm]$" ;
                if(eventDetails != null && eventDetails.matches(dateFormat)) //if the details are not null and the format is correct it will accept it or if neither of the conditions are true it will not accept it
                {//open if
                    events.add(eventDetails);
                    
                    isValid =true;
                }//close if 
                else 
                {//open else
                    out.println("invalid format, try again in the format: EventName dd/mm/yyyy hh:mmAM/PM ");
                }//end else 
            }//end while loop
            
            out.println("Thank you for adding event:");
                   
        } catch(IOException e)
        {
            e.printStackTrace();
        }///end catch
    }
    
    
    private void removingOperation()
    {
        try{
            boolean validInput = false;
            out.println("Please enter the index related to the event!\n");
            for(int i=0; i<events.size(); i++){
            StringTokenizer st = new StringTokenizer(events.get(i));
        
                while(st.hasMoreTokens())
                {
                    out.println(st.nextToken());
                }
        }
        
            String remove = in.readLine();
            
            while(!validInput)
            {
                int index = Integer.parseInt(remove);
                
                out.println("You have successfully removed event at the index of: "+index);
                validInput = true; //exit the loop
                
            }
            
            
            
            
        } catch(IOException e)
        {
            
        }
    }
}//end client class 
