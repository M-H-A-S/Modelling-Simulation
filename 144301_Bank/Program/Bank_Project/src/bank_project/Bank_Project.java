/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Muaad
 */
public class Bank_Project {

    
        //Three ArrayLists of type customer  to get/set variables (Customer Arrtibutes)  
       public static ArrayList<Customer> Drive_In_Table = new ArrayList<>();
        public static ArrayList<Customer>  Inside_Table= new ArrayList<>();
         public static ArrayList<Customer> Simulation_Table = new ArrayList<>();
        
    
    
    
    public static void main(String[] args) {
        
       
        Scanner sc=new Scanner(System.in);
        //("+e+ ")
        //System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       //System.out.println("Hello , Please Enter number of experiments ->");
        //int no_exp=sc.nextInt();
        
        //for(int e=0;e<no_exp;e++)
        //{
        
        //Taking the number of customer from User
        
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("please Enter the number of Customer in experiment  ->");
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
        
        int Customer_No=sc.nextInt();
                
        int Arrival_Time = 0;        
        int Row_of_Car=0;// im using Row_of_Car variable as counter to handling switching between Rows
        int Row_Of_Inside=0;//im using Row_of_Inside variable as counter to handling switching between Rows
    
        
        
        // Printing customer attributes as row
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(	"Customer_NO \t Iinter_Arrival      Arrival \t Servicer_Time\t       Queue_Type \t      Start    \t     Waiting\t\tEnd \t   Spended_In_System \tIdle\t");
	System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    
        //---------------------------//
        //looping on all Arraylists -->
        
        
        for(int i=0;i<Customer_No;i++)
        {
                        Customer row = new Customer();
                        Random rand = new Random();//built in function taht taking random number in every iteration
             
                        int RND_Inter_Arrival_Time = rand.nextInt(100);//taking number from 0 to 1000
			int Inter_Arrival_Time = 0;
			if (i == 0)// frist iteration_=-> inter_arravil_time = 0
				Inter_Arrival_Time = 0;
			else if (RND_Inter_Arrival_Time < 9)//Next intger from rand function and compare with comulitave probabilty according to my calculation 
				Inter_Arrival_Time = 0;
			else if (RND_Inter_Arrival_Time < 26)
				Inter_Arrival_Time = 1;
			else if (RND_Inter_Arrival_Time < 53)
				Inter_Arrival_Time = 2;
			else if (RND_Inter_Arrival_Time < 73)
				Inter_Arrival_Time = 3;
			else if (RND_Inter_Arrival_Time < 88)
				Inter_Arrival_Time = 4;
			else
				Inter_Arrival_Time = 5;
                        
                        Arrival_Time += Inter_Arrival_Time;//calculation of Arravilas time 
                        
                        
                        int RND_Service_Time = rand.nextInt(100);//taking number from 0 to 1000
			int Service_Time = 0;
                        if (RND_Service_Time < 21)//compare Random service time with thier cumulative probability 
				Service_Time = 1;
			else if (RND_Service_Time < 61)
				Service_Time = 2;
			else if (RND_Service_Time < 89)
				Service_Time = 3;
			else
				Service_Time = 4;
                        
                        //Calculation of Customer one 
                        if(i==0) // static calculation of first customer mannually 
                        {
                             row.Customer_No=i+1;
                             row.Inter_Arrival_Time=Inter_Arrival_Time;
                             row.Arrival_Time=Arrival_Time;
                             row.Service_Time=Service_Time;
                             row.Service_Start=0;
                             row.Waiting_Time=0;
                             row.Service_Ends=Service_Time;
                             row.Time_Spent_In_System=Service_Time;
                             row.Idle_Time=0;
                             row.Queue_Type="Drive_In"; //according to bank problem the first two customer will get serve in drive_in Queue ; 
                             
                             // Adding The First Customer to Arraylist
                             Drive_In_Table.add(row);//adding row that contain customer attributes !!-> to be easy when calculating the avarage in Car Queue
                             Simulation_Table.add(row);// also adding in final table 
                             Row_of_Car++;
                           System.out.println(Simulation_Table.get(i).toString());
                            
                        }
                        
                        // Calculation of Customer two
                        if(i==1)
                        {
                            row.Queue_Type="Drive_In";//first and second customers will be in Drive_in Queue
                           row.Customer_No=i+1;
                           row.Inter_Arrival_Time=Inter_Arrival_Time;
                           row.Arrival_Time=Arrival_Time;
                           row.Service_Time=Service_Time;
                           //Start Calculation
                           if(Drive_In_Table.get(i-1).Service_Ends<Arrival_Time)//(i-1) -> checing if time end of previous row smallar or greatar than Arravial time of current state
                               row.Service_Start=Arrival_Time;// relation rule in report
                           else
                               row.Service_Start=Drive_In_Table.get(i-1).Service_Ends; //in report
                           //Waiting Calculation
                           if(Drive_In_Table.get(i-1).Service_Ends<Arrival_Time)
                               row.Waiting_Time=0;
                           else
                               row.Waiting_Time=Drive_In_Table.get(i-1).Service_Ends-Arrival_Time;
                           
                           //Service End Calucation of second Customer
                           row.Service_Ends=row.Service_Start+Service_Time;
                           //Time Spended in system
                           row.Time_Spent_In_System=Service_Time+row.Waiting_Time;
                //idle time            
                           if(Drive_In_Table.get(i-1).Service_Ends <row.Arrival_Time)
				row.Idle_Time = row.Arrival_Time - Drive_In_Table.get(i-1).Service_Ends;
				else
                                row.Idle_Time = 0;
                           
                           Drive_In_Table.add(row);
                           Simulation_Table.add(row);
                           Row_of_Car++;
                           System.out.println(Simulation_Table.get(i).toString());
                        }
                    
                        
        
                         //When Customer which Queue have to go
                         if(i>1) //After first and second customer which is index 0 and 1 ->then starting from i>2 ->then every customer have to go Either Drive_in or Inside according to time ending of previous customer
                        {
                        
                           if(Drive_In_Table.get(Row_of_Car-2).Service_Ends <= Arrival_Time)//if time ending of (i-2) before  previous vustomer smallar than current customer arravils then will go Drive_in Queue;
                         {
                                row.Queue_Type = "Drive_In";
                                row.Customer_No = i+1;
				row.Inter_Arrival_Time = Inter_Arrival_Time;
				row.Arrival_Time = Arrival_Time;
				row.Service_Time = Service_Time;
                                //Start calculartion -> relation in report
                                if(Drive_In_Table.get(Row_of_Car-1).Service_Ends < Arrival_Time)
                                    row.Service_Start = Arrival_Time;
                                else 
                                    row.Service_Start = Drive_In_Table.get(Row_of_Car-1).Service_Ends;
                                
                                //Waiting Calculation
                                if(Drive_In_Table.get(Row_of_Car-1).Service_Ends < Arrival_Time)
                                    row.Waiting_Time = 0;
                                else 
                                    row.Waiting_Time =Drive_In_Table.get(Row_of_Car-1).Service_Ends - Arrival_Time;
				
                                // Service_End
                                row.Service_Ends = row.Service_Start + Service_Time;
                                // Time Spended In System
				row.Time_Spent_In_System = Service_Time+ row.Waiting_Time;////////!!!!!!!!!!!! this part in to tresing -> check before submit 
                                
                                //idle time calculation
                                if(Drive_In_Table.get(Row_of_Car-1).Service_Ends <row.Arrival_Time)
				row.Idle_Time = row.Arrival_Time - Drive_In_Table.get(Row_of_Car-1).Service_Ends;
				else
                                row.Idle_Time = 0;
                           
                                // Adding row in tables 
				Drive_In_Table.add(row);
                                Simulation_Table.add(row);
				Row_of_Car++;
                                System.out.println(Simulation_Table.get(i-1).toString());
                               
                         }
                           else if(Inside_Table.isEmpty())// if 
                         {
                             // in this if statment has a first record of customer arrive in bank 
                             row.Queue_Type="Inside_Bank";
                             row.Customer_No=i+1;
                             row.Inter_Arrival_Time=Inter_Arrival_Time;
                             row.Arrival_Time=Arrival_Time;
                             row.Service_Time=Service_Time;
                             row.Service_Start=Arrival_Time;
                             row.Service_Ends=row.Service_Start + Service_Time;
                             
                             
                             row.Waiting_Time=0;
                             row.Time_Spent_In_System=row.Waiting_Time+Service_Time;
                             row.Idle_Time=0;
                             Inside_Table.add(row);
                             Simulation_Table.add(row);
                          
                             Row_Of_Inside++;
                             System.out.println(Simulation_Table.get(i-1).toString());
                             
                         }
                           else
                         {
                             row.Queue_Type="Inside_Bank";
                            
                             row.Customer_No=i+1;
                             row.Inter_Arrival_Time=Inter_Arrival_Time;
                             row.Arrival_Time=Arrival_Time;
                             row.Service_Time=Service_Time;
                             
                             //Start Clc
                             if(Inside_Table.get(Row_Of_Inside-1).Service_Ends<Arrival_Time)
                                 row.Service_Start=Arrival_Time+ Service_Time;
                             else
                                 row.Service_Start=Arrival_Time;
                             //Waiting in bank 
                             if(Inside_Table.get(Row_Of_Inside-1).Service_Ends<Arrival_Time)
                                 row.Waiting_Time=0;
                             else 
                                 row.Waiting_Time=Inside_Table.get(Row_Of_Inside-1).Service_Ends-Arrival_Time;
                             
                             //Service End 
                             row.Service_Ends=row.Service_Start+Service_Time;
                             //Service Time Spend In System 
                             row.Time_Spent_In_System=Service_Time+row.Waiting_Time;
                             
                             //Idle Time Clc
                            if(Inside_Table.get(Row_Of_Inside-1).Service_Ends<row.Arrival_Time)
                                 row.Idle_Time=row.Arrival_Time-Inside_Table.get(Row_Of_Inside-1).Service_Ends;
                            else
                                 row.Idle_Time=0;
                             
                             Inside_Table.add(row);
                             Simulation_Table.add(row);
                             Row_Of_Inside++;
                             System.out.println(Simulation_Table.get(i-1).toString());
                         }  
                             
    
        }
        }
 
       
   
        
         
       System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
       System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
      
           
           double  Total_Service_Time_Q1=0;
           double  Total_Car=0;
           double  Total_Waiting_Q1=0;
           //Calculation total of Drive_in (Service_Time & Waiting & total car_)
           for (int i=0;i<Drive_In_Table.size();i++)
           {
               Total_Service_Time_Q1+=Drive_In_Table.get(i).Service_Time;
               Total_Waiting_Q1+=Drive_In_Table.get(i).Waiting_Time;
               Total_Car++;
           }
           
           double  Total_Service_Time_Q2=0;
           double  Total_Customer_Inside=0;
           double  Total_Waiting_Q2=0;
           //Calculation total of Drive_in (Service_Time & Waiting & total car_)
           for (int i=0;i<Inside_Table.size();i++)
           {
               Total_Service_Time_Q2+=Inside_Table.get(i).Service_Time;
               Total_Waiting_Q2+=Inside_Table.get(i).Waiting_Time;
               Total_Customer_Inside++;
           }
           
           //The average service time of the drive-in teller and the inside-bank teller.(*1*)
           double Avg_ServiceTime_DriveIn=Total_Service_Time_Q1/Total_Car;
           double Avg_ServiceTime_Inside=Total_Service_Time_Q2/Total_Customer_Inside;
           
           //The average waiting time in the drive-in teller queue and the inside-bank teller queue.   (*2*) 
           double Avg_WaitingTime_DriveIn=Total_Waiting_Q1/Total_Car;
           double Avg_WaitingTime_Inside=Total_Waiting_Q2/Total_Customer_Inside;
           
           //Maximum Inside the Bank
           
           
           //The probability that a customer wait in the inside-bank teller queue. 
           double number_of_Customer_has_to_wait=0;
           for(int k=0;k<Inside_Table.size();k++)
           {
               if(Inside_Table.get(k).Waiting_Time>0)
               {
                   number_of_Customer_has_to_wait++;
               }
                       
           }
           // 
           double Probility_waiting_inside_bank=number_of_Customer_has_to_wait/Total_Customer_Inside;
           
           //The portion of idle time of the inside-bank teller.
           double P_Idle_Inside=0;
           double idle_times=0;
           for(int j=0;j<Inside_Table.size();j++)
           {
               if(Inside_Table.get(j).Idle_Time>0)
               {
                   P_Idle_Inside+=Inside_Table.get(j).Idle_Time;
                   idle_times++;
               }
           }
           //partion idle in car 
            double C_Idle_DriveIn=0;
           double C_idle_times=0;
           for(int j=0;j<Drive_In_Table.size();j++)
           {
               if(Drive_In_Table.get(j).Idle_Time>0)
               {
                   C_Idle_DriveIn+=Drive_In_Table.get(j).Idle_Time;
                   C_idle_times++;
               }
           }
           //("+e+") 
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
             System.out.println("-------------------------------------------------------------------- Time estimation of experiment----------------------------------------------------------------------------------------------------- ");
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
             
            System.out.println("The average service time of the drive-in teller:                        \t"+Avg_ServiceTime_DriveIn);
            System.out.println("The average service time of the Inside-bank teller:                     \t"+Avg_ServiceTime_Inside);
     
                System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
           
           System.out.println("The average waiting time in the drive-in teller queue:                   \t"+Avg_WaitingTime_DriveIn);
           System.out.println("The average waiting time in the Inside_bank teller queue:                \t"+Avg_WaitingTime_Inside);
           
                 System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
          
           System.out.println("The probability that a customer wait in the inside-bank teller queue:     \t"+Probility_waiting_inside_bank);
                
                    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
          
           System.out.println("The portion of idle time of the Drive_In teller:                          \t"+C_idle_times  +" Times  "  + " Total Idle Inside " +C_Idle_DriveIn+" Min");
                    
           System.out.println("The portion of idle time of the inside-bank teller:                       \t"+idle_times  +" Times  "  + " Total Idle Inside " +P_Idle_Inside+" Min");
                
            
      // }
        
    
    }
}
