package com.company.model;

import java.text.SimpleDateFormat;

public class Invoice_Line {
    private Invoice_Header parent_Invoice= null;
    private int inv_no = 0;
    private String Item_Name;
    private int Price;
    private int Count;
    private int Total;
    private int inv_id = 0;

    public Invoice_Line(Invoice_Header pInv , String item_Name, int price, int count) {
        this.inv_no = pInv.getNo();
        Item_Name = item_Name;
        Price = price;
        Count = count;
        parent_Invoice = pInv;
        Update_total_line_Cost();
    }
    //setters and getters
    public void setInv_id(int inv_id) {
        this.inv_id = inv_id;
    }

    public int getInv_id() {
        return inv_id;
    }
    
    //method to update line cost
    public void Update_total_line_Cost()
    {
        this.Total = this.Price*this.Count;
    }

    public int getTotal() {
        return Total;
    }
//Method used to print Lines in console
    @Override
    public String toString() {
        return " " + Item_Name +
                ", " + Price +
                ", " + Count ;

    }
//Method used to save data in files
    public String string_to_be_saved() {


        return this.parent_Invoice.getNo() +","+this.Item_Name+","+this.Price +","+ this.Count;
    }
    //Method used to fill Lines Table
    public  String[] string_array_to_filltable()
    {   
        int n = this.parent_Invoice.getNo();
        String [] test = {String.valueOf(n),this.Item_Name , String.valueOf(this.Price) , String.valueOf(this.Count) ,String.valueOf(this.Total) };
        return test;
    
    }
}
