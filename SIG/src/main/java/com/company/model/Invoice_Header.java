//Invoices Header Class
package com.company.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice_Header {
    //Local Variables
    private int no = 0;
    private Date Inv_Date;

    private String Customer = "Unknown";
    private int Total = 0;
    
    private ArrayList<Invoice_Line> Lines = new ArrayList<Invoice_Line>();
    private int Lines_Count = 0;
    
    //Constructor
    public Invoice_Header(int no, Date inv_Date, String customer) {
        this.no = no;
        Inv_Date = inv_Date;
        Customer = customer;

    }
    //Setters and Getters
    public void setInv_Date(Date Inv_Date) {
        this.Inv_Date = Inv_Date;
    }

    public void setCustomer(String Customer) {
        this.Customer = Customer;
    }

    public int getNo() {
        return no;
    }
    public int get_Lines_No() {
        return Lines.size();
    }
    
    /////////////////////////////////////////////////
    //method used remove all lines of invoice when updating from Lines table
    public void remove_all_lines()
    {
        for (int i = Lines.size()-1; i >= 0; i--) {
        Lines.remove(i);
        }
        
    }
    public void add_Line(Invoice_Line Line) {
        Line.setInv_id(Lines.size());
        Lines.add(Line);
        Lines_Count++;
        this.Total += Line.getTotal();
    }

    public void remove_Line(int no) {
        this.Total -= Lines.get(no).getTotal();
        Lines.remove(no);
        Lines_Count--;
    }
    //Method used to update total cost 
    public void Update_total_Header_Cost() {
        int total = 0;
        for (int i = 0; i < Lines.size(); i++) {
            if(Lines.get(i) != null){
            total += Lines.get(i).getTotal();}
        }
        this.Total = total;
    }
    //Method used to get Invoice Header by Invoice Number
    public Invoice_Header get_Invoice_ny_no(int no) {
        if (no == this.no) {
            return this;
        }
        return null;
    }
    


//Method used to print in console
    @Override
    public String toString() {
        String lines_print = "";

        for (int i = 0; i < Lines.size(); i++) {
            if (Lines.get(i) != null) {
                lines_print += Lines.get(i).toString();
                lines_print += "\n";
            }
        }
        //Convert date to string with specific Format
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(Inv_Date);


        return no + "\n{\n" +
                date + ", "
                + Customer + "\n"
                + lines_print
                + "\n}\n";
    }
    
    //Method used to save Invoice data in csv files 
    public String string_to_be_saved() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(Inv_Date);

        return this.no + "," + date + "," + this.Customer;
    }
//Method used to save Invoice lines data in csv files
    public String lines_string_to_be_saved() {
        String printlines = "";
        for (int i = 0; i < Lines.size(); i++) {
            if (Lines.get(i) != null) {
                printlines += Lines.get(i).string_to_be_saved();
                printlines+=System.getProperty("line.separator");
            }

        }
        return printlines;


    }
//Method used to get data of invoice to fill Invoice table.
    public  String[] string_array_to_filltable()
    {
        Update_total_Header_Cost();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(Inv_Date);


        String [] arr = {String.valueOf(this.getNo()), date , this.Customer , String.valueOf(this.Total) };
        return arr;

    }
    //Method used to get data of invoice lines to fill lines/Items table.
        public  String[][] lines_string_array_to_filltable()
    {
         String [][] arr = new String[Lines.size()][5];
         int count = 0;
        for (int i = 0; i < Lines.size(); i++) 
        {
            
            if(Lines.get(i) != null)
            {
               arr[count] = Lines.get(i).string_array_to_filltable();
               count++;
            }
        }
        
        return arr;

    }
}

