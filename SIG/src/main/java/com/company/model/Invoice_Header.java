package com.company.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice_Header {
    private int no = 0;
    private Date Inv_Date;

    private String Customer = "Unknown";
    private int Total = 0;
    //private Invoice_Line[] Lines = new Invoice_Line[];
    private ArrayList<Invoice_Line> Lines = new ArrayList<Invoice_Line>();
    private int Lines_Count = 0;
    

    public Invoice_Header(int no, Date inv_Date, String customer) {
        this.no = no;
        Inv_Date = inv_Date;
        Customer = customer;

    }

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

    public void Update_total_Header_Cost() {
        int total = 0;
        for (int i = 0; i < Lines.size(); i++) {
            if(Lines.get(i) != null){
            total += Lines.get(i).getTotal();}
        }
        this.Total = total;
    }

    public Invoice_Header get_Invoice_ny_no(int no) {
        if (no == this.no) {
            return this;
        }
        return null;
    }
    
        /*public Invoice_Line get_Invoice_by_total(int total) {
        if (no == this.no) {
            return this;
        }
        return null;
    }*/


    @Override
    public String toString() {
        String lines_print = "";

        for (int i = 0; i < Lines.size(); i++) {
            if (Lines.get(i) != null) {
                lines_print += Lines.get(i).toString();
                lines_print += "\n";
            }
        }

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(Inv_Date);


        return no + "\n{\n" +
                date + ", "
                + Customer + "\n"
                + lines_print
                + "\n}\n";
    }

    public String string_to_be_saved() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(Inv_Date);

        return this.no + "," + date + "," + this.Customer;
    }

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

    public  String[] string_array_to_filltable()
    {
        Update_total_Header_Cost();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(Inv_Date);


        String [] arr = {String.valueOf(this.getNo()), date , this.Customer , String.valueOf(this.Total) };
        return arr;

    }
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

