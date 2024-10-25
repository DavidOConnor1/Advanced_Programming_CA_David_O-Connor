/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serversidetcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author dmoc2
 */
public class EventScheduler {
    
    public void test(String line)
    {
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        
        switch(line){
            case "a":
                System.out.println("random");
                break;
            case "b":
                System.out.println("lol");
                break;
                
        }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    
    
}
