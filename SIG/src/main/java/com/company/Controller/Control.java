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
      public static void Update_Header_Table(JTable Inv_H_Table)
      {
            String[][] H_Data = Control.get_Header_data();
            String[] H_Cols = {"No.","Date","Customer","Total"};
        
        
        Inv_H_Table.setModel(new javax.swing.table.DefaultTableModel(
            H_Data,
                H_Cols)  {
                    
                @Override
    public boolean isCellEditable(int row, int column) {
       
       return false;
            }
        
        }
        );
        
          
      }
      public static void Update_Invoice_lines(JTable Inv_L_Table , int Invoice_index)
      {
          
          String [][] linesdata = new String[Inv_L_Table.getRowCount()][Inv_L_Table.getColumnCount()];
          Invoice_Header Inv_h = FileOperations.getInv_H().get(Invoice_index);
         for(int i = 0  ; i<Inv_L_Table.getRowCount();i++) 
         {
             for (int j = 0; j < Inv_L_Table.getColumnCount(); j++) 
             {
                 linesdata[i][j] = Inv_L_Table.getModel().getValueAt(i, j).toString();
                 
             }
 
         }
         Inv_h.remove_all_lines();
          for (int i = 0; i < Inv_L_Table.getRowCount(); i++) {
              Inv_h.add_Line(new Invoice_Line(Inv_h , linesdata[i][1] ,Integer.parseInt(linesdata[i][2]) , Integer.parseInt(linesdata[i][3])));
          }
         
         
         
         
       
            
       
    }
}
   
