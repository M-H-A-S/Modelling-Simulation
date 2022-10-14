/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_project;

/**
 *
 * @author Muaad
 */
public class Customer {
    int Customer_No;
    int Inter_Arrival_Time;
    int Arrival_Time;
    int Service_Time;
    int Service_Start;
    int Waiting_Time;
    int Service_Ends;
    int Time_Spent_In_System;
    int Idle_Time;
    String Queue_Type;

    public Customer() {
    }

    
     @Override
    public String toString() {
        return   "\t"+ Customer_No +  "\t\t" + Inter_Arrival_Time + "\t   \t" + Arrival_Time + "\t\t"+ Service_Time + "\t\t" + Queue_Type+"\t\t"+ Service_Start + "\t\t" + Waiting_Time + "\t\t" + Service_Ends + "\t\t" + Time_Spent_In_System + "\t\t" + Idle_Time ;
    }
    
           
    
}
