/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.Day;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Muaad
 */
public class Inventory {
    public static ArrayList<Day> FinalTable  = new ArrayList<>();
    
    public static void main(String[] args) {
    
    Scanner sc=new Scanner(System.in);
    System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("please Enter the number of Days  in experiment  ->");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
    int Day_No=sc.nextInt();
    
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(	"N \t Day_no     Showroom_Start  Inventory_Start    Total_Start       Demand       Showroom_End\tInventory_End \t   Total_End \t          Shortage\t     Order_Quantity\tLaed_Time ");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
int Demand=0;
     int Lead_Time =2;
    for (int i=0;i<Day_No;i++)
    {
                        Day row = new Day();
                        Random rand = new Random();//built in function taht taking random number in every iteration
                        
                        int R_Demand=0;
                        int RND_Demand = rand.nextInt(100);//taking number from 0 to 1000
			
                        
                        int Min_To_order=2;// my minimum of quantity to make order
                        
                        
                        if(RND_Demand<5)
                            Demand=0;
                        else if(RND_Demand<33)
                            Demand=1;
                        else if(RND_Demand<70)
                                Demand=2;
                        else if (RND_Demand<90)
                                Demand=3;
                        else if(RND_Demand <1)
                            Demand=4;
                        
                        
                        int RND_Lead_Time = rand.nextInt(100);//taking number from 0 to 1000
			
                            
                        if(RND_Lead_Time<55)
                            Lead_Time=1;
                        else if (RND_Lead_Time<90)
                            Lead_Time=2;
                        else if(RND_Lead_Time<1)
                            Lead_Time=3;
                        
                        int Day_Counter=0;
                        int Cycle=0;
                        
                        if(i==0)
                        {
                             row.N=Cycle;
                             row.Day_no=i+1;
                             row.Showroom_Start=4;
                             row.Inventory_Start=2;
                             row.Total_Start=row.Inventory_Start+row.Showroom_Start;
                             row.Demand=Demand;
                             
                             //Calculation of Showroom_End  & Inventory_End
                             // if demand > from my inventory quantity
                             if(row.Demand==0)
                             {
                                 row.Inventory_End=row.Inventory_Start;
                                 row.Showroom_End=row.Showroom_Start;
                                 row.Total_End=row.Total_Start;
                             }
                             else if(row.Demand > row.Inventory_Start)//when inventory runs out the cars in showroom are sold 
                             {
                                R_Demand  = row.Demand - row.Inventory_Start;
                                row.Showroom_End=row.Showroom_Start-R_Demand;
                                row.Inventory_End=0;
                             }
                             //if demand == my inventory quantaty 
                             else if(row.Demand==row.Inventory_Start)
                             {
                                 row.Inventory_End=0;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             // if demand < inventory 
                             else if(row.Demand<row.Inventory_Start)
                             {
                                 row.Inventory_End=row.Inventory_Start-row.Demand;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             
                              //Shortage 
                             if (row.Demand>row.Total_Start)
                             {
                                 row.Shortage=row.Demand-row.Total_Start;
                             }
                             //total end 
                             row.Total_End=row.Inventory_End+row.Showroom_End;
                             
                             //order quantity 
                            
                             row.Order_Quantity=0;
                             
                             //leading time Random
                             row.Lead_Time=0;
                             
                             
                             
                             // Adding The First Day  to simulation table
                             FinalTable.add(row);
                             System.out.println(FinalTable.get(i).toString());
                             Day_Counter++;
        
                        }
        
                        
                        
                        if(i==1)
                        {
                             row.N=Cycle;
                             row.Day_no=i+1;
                             
                             row.Showroom_Start=FinalTable.get(i-1).Showroom_End;
                             row.Inventory_Start=FinalTable.get(i-1).Inventory_End;
                             row.Total_Start=row.Inventory_Start+row.Showroom_Start;
                             row.Demand=Demand;
                             
                             //Calculation of Showroom_End & Inventory_End 
                             if(row.Demand==0)
                             {
                                 row.Inventory_End=row.Inventory_Start;
                                 row.Showroom_End=row.Showroom_Start;
                                 row.Total_End=row.Total_Start;
                             }
                             else if(row.Demand > row.Inventory_Start)
                             {
                                R_Demand  = row.Demand - row.Inventory_Start;
                                row.Showroom_End=row.Showroom_Start-R_Demand;
                                row.Inventory_End=0;
                             }
                             //if demand == my inventory quantaty 
                             else if(row.Demand==row.Inventory_Start)
                             {
                                 row.Inventory_End=0;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             // if demand < inventory 
                             else if(row.Demand<row.Inventory_Start)
                             {
                                 row.Inventory_End=row.Inventory_Start-row.Demand;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             
                              
                             //total end 
                             row.Total_End=row.Inventory_End+row.Showroom_End;
                              //Shortage 
                             if (row.Demand>row.Total_Start)
                             {
                                 row.Shortage=row.Demand-row.Total_Start;
                             }
                             
                             //order quantity 
                             if(row.Inventory_End<=Min_To_order)
                             {
                                    row.Order_Quantity=12-row.Total_End;
                                    row.Lead_Time=2;
                             
                             }
                             else
                             {
                                 row.Order_Quantity=0;
                                 row.Lead_Time=0;
                             }
                             
                             // Adding The First Day  to simulation table
                             FinalTable.add(row);
                             System.out.println(FinalTable.get(i).toString());
                             Day_Counter++;
                            // row.Lead_Time--;
        
                             
                            
                        }
                        
                        if(i>1)
                        {
                            row.N=0;
                            row.Day_no=i+1;
                            
                            //Start if order still 
                            if(FinalTable.get(i-1).Lead_Time>0 && FinalTable.get(i-1).Order_Quantity>0)
                            {
                                row.Showroom_Start=FinalTable.get(i-1).Showroom_End;
                                row.Inventory_Start=FinalTable.get(i-1).Inventory_End;
                                row.Total_Start=row.Showroom_Start+row.Inventory_Start;
                                row.Demand=Demand;
                                
                                //---------------------------
                                if(row.Demand==0)
                             {
                                 row.Inventory_End=row.Inventory_Start;
                                 row.Showroom_End=row.Showroom_Start;
                                 row.Total_End=row.Total_Start;
                             }
                             else if(row.Demand > row.Inventory_Start)
                             {
                                R_Demand  = row.Demand - row.Inventory_Start;
                                row.Showroom_End=row.Showroom_Start-R_Demand;
                                row.Inventory_End=0;
                             }
                             //if demand == my inventory quantaty 
                             else if(row.Demand==row.Inventory_Start)
                             {
                                 row.Inventory_End=0;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             // if demand < inventory 
                             else if(row.Demand<row.Inventory_Start)
                             {
                                 row.Inventory_End=row.Inventory_Start-row.Demand;
                                 row.Showroom_End=row.Showroom_Start;
                             } 
                             //total end 
                             row.Total_End=row.Inventory_End+row.Showroom_End;
                             
                             //----------------------------------------
                                //Shortage
                             if(row.Demand>row.Total_Start)
                             {
                                 row.Shortage=row.Demand-row.Total_Start;
                             }
                             
                             //order 
                             row.Order_Quantity=FinalTable.get(i-1).Order_Quantity;
                             row.Lead_Time=FinalTable.get(i-1).Lead_Time-1;
                              FinalTable.add(row);
                             System.out.println(FinalTable.get(i).toString());
                             
                            }
                            // if order reach
                            else if(FinalTable.get(i-1).Lead_Time==0)
                            {
                                row.N=0;
                                row.Day_no=i+1;
                                //----------- start
                                if(FinalTable.get(i-1).Shortage>0)
                                {
                                    row.Showroom_Start=4;
                                    row.Inventory_Start=FinalTable.get(i-1).Order_Quantity-FinalTable.get(i-1).Shortage;
                                }
                                else
                                {
                                    row.Showroom_Start=4;
                                    row.Inventory_Start=8;
                                }
                                //total start
                                row.Total_Start=row.Showroom_Start+row.Inventory_Start;
                                
                                //demand
                                row.Demand=Demand;
                                
                                //calculation of End day
                                //Calculation of Showroom_End  & Inventory_End
                             // if demand > from my inventory quantity
                             if(row.Demand==0)
                             {
                                 row.Inventory_End=row.Inventory_Start;
                                 row.Showroom_End=row.Showroom_Start;
                                 row.Total_End=row.Total_Start;
                             }
                             else if(row.Demand > row.Inventory_Start)//when inventory runs out the cars in showroom are sold 
                             {
                                R_Demand  = row.Demand - row.Inventory_Start;
                                row.Showroom_End=row.Showroom_Start-R_Demand;
                                row.Inventory_End=0;
                             }
                             //if demand == my inventory quantaty 
                             else if(row.Demand==row.Inventory_Start)
                             {
                                 row.Inventory_End=0;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             // if demand < inventory 
                             else if(row.Demand<row.Inventory_Start)
                             {
                                 row.Inventory_End=row.Inventory_Start-row.Demand;
                                 row.Showroom_End=row.Showroom_Start;
                             }
                             
                              //Shortage 
                             if (row.Demand>row.Total_Start)
                             {
                                 row.Shortage=row.Demand-row.Total_Start;
                             }
                             //total end 
                             row.Total_End=row.Inventory_End+row.Showroom_End;
                             
                                
                             //ordeeeeer
                             if(row.Inventory_End<=Min_To_order)
                             {
                                 row.Order_Quantity=12-row.Inventory_End;
                                 row.Lead_Time=Lead_Time;
                             }
                             else 
                             {
                                 row.Order_Quantity=0;
                                 row.Lead_Time=0;
                             }
                                 //adding
                              FinalTable.add(row);
                             System.out.println(FinalTable.get(i).toString());
                                
                            }
                            
                            
                            
                            
                            
                        }
                        
                        
                        
                        }
        
    }
        
        
    }
    

