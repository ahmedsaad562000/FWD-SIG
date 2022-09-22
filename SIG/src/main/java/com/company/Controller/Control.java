package com.company.Controller;

import com.company.model.FileOperations;
import com.company.model.Invoice_Header;
import com.company.model.Invoice_Line;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class Control {

    public static String[][] get_Header_data()
    {

        ArrayList<Invoice_Header> Inv_Headers = FileOperations.getInv_H();
        String [][] Data = new String[Inv_Headers.size()][4];
        for (int i = 0 ; i<Inv_Headers.size(); i++)
            if(Inv_Headers != null)
            {
                Data[i] = Inv_Headers.get(i).string_array_to_filltable();
            }
        return Data;

    }   
        public static String[][] get_Lines_data(int no)
    {

        ArrayList<Invoice_Header> Inv_Headers = FileOperations.getInv_H();
        Invoice_Header Target = null;
       
         for (int i = 0 ; i<Inv_Headers.size() ; i++)
        {
            if(Inv_Headers.get(i) != null)
            {
                if(Inv_Headers.get(i).get_Invoice_ny_no(no)!=null)
                {
                Target = Inv_Headers.get(i);
                }
            }
        }
        if(Target != null){
        String [][] Data = new String[Target.get_Lines_No()][5];

                Data = Target.lines_string_array_to_filltable();
            return Data;
        }
        else
        {return null;}
    }
        
    public static String[] get_Selected_Invoice_data(int no)
    {

        ArrayList<Invoice_Header> Inv_Headers = FileOperations.getInv_H();
        Invoice_Header Target = null;
       
         for (int i = 0 ; i<Inv_Headers.size() ; i++)
        {
            if(Inv_Headers.get(i) != null)
            {
                if(Inv_Headers.get(i).get_Invoice_ny_no(no)!=null)
                {
                Target = Inv_Headers.get(i);
                }
            }
        }
        if(Target != null){
        String [] Data = Target.string_array_to_filltable();
            return Data;
        }
        else
        {return null;}
    }
    
    public static void update_Date_and_Customer(int selected_invoice , Date date , String Customer)
    {
        ArrayList<Invoice_Header> Inv_Headers = FileOperations.getInv_H();
        Invoice_Header Target = null;
       
         for (int i = 0 ; i<Inv_Headers.size() ; i++)
        {
            if(Inv_Headers.get(i) != null)
            {
                if(Inv_Headers.get(i).get_Invoice_ny_no(selected_invoice)!=null)
                {
                Target = Inv_Headers.get(i);
                Target.setInv_Date(date);
                Target.setCustomer(Customer);
                }
            }
        }
            
        }
     public static void add_line_into_selected_inv(int selected_invoice , String item_name , int price , int count)
    {
        ArrayList<Invoice_Header> Inv_Headers = FileOperations.getInv_H();
        
       
         for (int i = 0 ; i<Inv_Headers.size() ; i++)
        {
            if(Inv_Headers.get(i) != null)
            {
                if(Inv_Headers.get(i).get_Invoice_ny_no(selected_invoice)!=null)
                {
                    Invoice_Line Line = new Invoice_Line(Inv_Headers.get(i), item_name, price,  count);
               
                Inv_Headers.get(i).add_Line(Line);
               
                }
            }
        }
            
        }
     
      public static void delete_line_from_selected_inv(int selected_invoice , int line_no)
    {
        ArrayList<Invoice_Header> Inv_Headers = FileOperations.getInv_H();
        
       
         for (int i = 0 ; i<Inv_Headers.size() ; i++)
        {
            if(Inv_Headers.get(i) != null)
            {
                if(Inv_Headers.get(i).get_Invoice_ny_no(selected_invoice)!=null)
                {
                    
                
                Inv_Headers.get(i).remove_Line(line_no);
               
                }
            }
        }
            
        }
      
      public static void Update_Tables(JTable Inv_H_Table ,JTable Inv_L_Table)
      {
         for(int i = 0  ; i<Inv_H_Table.getRowCount();i++) 
         {
             
         }
       
            
       
    }
}
    /*public static String[][] get_Lines_data()
    {

        Invoice_Header[] Inv_Headers = FileOperations.getInv_H();
        ArrayList<String><String> cars = new ArrayList<String>();
        String [][] Data = new String[Inv_Headers.length][5];
        for (int i = 0 ; i<Inv_Headers.length ; i++)
            if(Inv_Headers != null)
            {
                Data[i] = Inv_Headers[i].string_array_to_filltable();
            }
        return Data;

    }*/
