/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author Muaad
 */
public class Day {
    public int N;
    public int Day_no;
    public int Showroom_Start;
    public int Inventory_Start;
    public int Total_Start;
    public int Demand;
    public int Showroom_End;
    public int Inventory_End;
    public int Total_End;
    public int Shortage;
    public int Order_Quantity;
    public int Lead_Time;
    public int Day_Leading_Order_Until_Reach;

    public Day(int N, int Day_no, int Showroom_Start, int Inventory_Start, int Total_Start, int Demand, int Showroom_End, int Inventory_End, int Total_End,int Shortage ,int Order_Quantity, int Lead_Time, int Day_Leading_Order_Until_Reach) {
        this.N = N;
        this.Day_no = Day_no;
        this.Showroom_Start = Showroom_Start;
        this.Inventory_Start = Inventory_Start;
        this.Total_Start = Total_Start;
        this.Demand = Demand;
        this.Showroom_End = Showroom_End;
        this.Inventory_End = Inventory_End;
        this.Total_End = Total_End;
        this.Shortage=Shortage;
        this.Order_Quantity = Order_Quantity;
        this.Lead_Time = Lead_Time;
        this.Day_Leading_Order_Until_Reach = Day_Leading_Order_Until_Reach;
    }

    public Day() {
    }
    
    

    @Override
    public String toString() {
        return   "" + N + "\t  " + Day_no + "\t\t" + Showroom_Start + "\t\t" + Inventory_Start + "\t\t  " + Total_Start + "\t\t  " + Demand + "\t\t" + Showroom_End + "\t\t   " + Inventory_End + "\t\t      " + Total_End + "                     " + Shortage +"                  " + Order_Quantity + "                     " + Lead_Time  ;
    }
    
    
    
}
