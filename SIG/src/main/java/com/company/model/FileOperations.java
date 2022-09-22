package com.company.model;



import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.sound.sampled.Line;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class FileOperations  {
    private static ArrayList<Invoice_Header> Inv_H = new  ArrayList<Invoice_Header>();
    private static ArrayList<Invoice_Header> Inv_H_backup = new  ArrayList<Invoice_Header>();      
    public static ArrayList<Invoice_Header> getInv_H() {
        return Inv_H;
    }

    private static int Inv_H_count = 0;

    public static int getInv_H_count() {
        return Inv_H_count;
    }

    public static void setInv_H_count(int Inv_H_count) {
        FileOperations.Inv_H_count = Inv_H_count;
    }

   public static ArrayList<Invoice_Header> read_Header_File(String[] sb)
    {
        String [] Invoice_without_commas;
        ArrayList<Invoice_Header> Invoices = new  ArrayList<Invoice_Header>();
        for(int i = 0 ; i<sb.length ; i++)
        {
            Invoice_without_commas = sb[i].split(",");
            if(Invoice_without_commas.length == 3){

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

                Date date = null;
                try {
                    date = formatter.parse(Invoice_without_commas[1]);
                } catch (ParseException e) {
                    //wrong date format
                    JOptionPane.showMessageDialog(null, "wrong date format", "wrong date format", JOptionPane.WARNING_MESSAGE);return null;
                }

                Invoice_Header Inv_H = new Invoice_Header(Integer.parseInt(Invoice_without_commas[0]) ,date , Invoice_without_commas[2]);
                Invoices.add(Inv_H);
                Inv_H_count++;
            }
            else {/*more or less columns*/JOptionPane.showMessageDialog(null, "records Contain more or less columns than expected \n Load aborted", "wrong date Structure", JOptionPane.WARNING_MESSAGE);return null;}
        }

        return Invoices;
    }




    public static void read_Line_File(String[] sb)
    {
        String [] Line_without_commas;
        Invoice_Header target = null;
        for(int i = 0 ; i<sb.length ; i++)
        {
            Line_without_commas = sb[i].split(",");
            if(Line_without_commas.length == 4)
            {
                for(int j = 0 ; j<Inv_H.size() ; j++)
                {
                    if(Inv_H.get(j).get_Invoice_ny_no(Integer.parseInt(Line_without_commas[0])) != null) {target = Inv_H.get(j);}
                }
                if (target != null) {
                    Invoice_Line L = new Invoice_Line(target ,Line_without_commas[1] , Integer.parseInt(Line_without_commas[2]) , Integer.parseInt(Line_without_commas[3]) );
                    target.add_Line(L);
                }
                else{/*line that has no header*/JOptionPane.showMessageDialog(null, "some lines have no invoices in the header file \n Load aborted", "wrong data Insertion", JOptionPane.WARNING_MESSAGE);Inv_H = Inv_H_backup;return;}
            }
            else {/*more or less columns*/JOptionPane.showMessageDialog(null, "records Contain more or less columns than expected \n Load aborted", "wrong date Structure", JOptionPane.WARNING_MESSAGE);Inv_H = Inv_H_backup;return;}
        }
    }


    public static void open_Header_file(Component parent , Boolean default_or_new)
    {
        FileInputStream FIS = null;
        String path = "";
        JFileChooser JFC = null;
            if(default_or_new == true) {

             String dir = System.getProperty("user.dir");
                        path = dir.concat("\\CSV_Files\\InvoiceHeader.csv");
                        path = path.replace("\\", "/");
            }
            else {
                JFC = new JFileChooser();
                if(JFC.showOpenDialog(parent)==JFileChooser.APPROVE_OPTION) {
                    String ff = JFC.getSelectedFile().getPath();
                    ff =  ff.substring(ff.lastIndexOf(".")+1);
                    if(ff.equals("csv") == false) {
                       
                        JOptionPane.showMessageDialog(null, "File extension should be .csv \n load aborted", "Wrong Format for Header file ", JOptionPane.WARNING_MESSAGE);
                        
                        return;
                    }
                    else
                    {
                        path = JFC.getSelectedFile().getPath();
                    }
                }
            }

            try {
                FIS = new FileInputStream(path);


                int size;
                size = FIS.available();
                byte[] b = new byte[size];

                FIS.read(b);

                String sb = new String(b);
                String[] Inv_Headers_Text = sb.split(System.lineSeparator());
                ArrayList<Invoice_Header>  in_H_temp = new ArrayList<Invoice_Header>();
                in_H_temp = read_Header_File(Inv_Headers_Text);
                
                if(in_H_temp == null){return;}
                Inv_H_backup = Inv_H;
                Inv_H = in_H_temp;
                open_Line_file(parent, default_or_new);


            } catch (FileNotFoundException e) {JOptionPane.showMessageDialog(null , "Header Path not Found" ,"Path not Found" , JOptionPane.WARNING_MESSAGE );
            } catch (IOException e) {
            } finally {
                try {
                    FIS.close();
                } catch (IOException e) {
                }

            }






    }

    public static void open_Line_file(Component parent , Boolean default_or_new)
    {
        FileInputStream FIS = null;
        String path = "";
        JFileChooser JFC = null;


        if(default_or_new)
             {
                         String dir = System.getProperty("user.dir");
                        path = dir.concat("\\CSV_Files\\InvoiceLine.csv");
                        path = path.replace("\\", "/");
            }
            else {
                JFC = new JFileChooser();
                if (JFC.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
                {
                    String ff = JFC.getSelectedFile().getPath();
                    ff = ff.substring(ff.lastIndexOf(".") + 1);
                    if (ff.equals("csv") == false)
                    {
                        JOptionPane.showMessageDialog(null, "File extension should be .csv", "Wrong Format for Line file ", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else
                        {
                            path = JFC.getSelectedFile().getPath();
                         }
                }
            }
            try {
                FIS = new FileInputStream(path);


                int size;
                size = FIS.available();
                byte[] b = new byte[size];

                FIS.read(b);

                String sb = new String(b);
                String[] Inv_Lines_Text = sb.split(System.lineSeparator());
                read_Line_File(Inv_Lines_Text);
                print_in_console();

            } catch (FileNotFoundException e) {JOptionPane.showMessageDialog(null , "Line Path not Found" ,"Path not Found" , JOptionPane.WARNING_MESSAGE );
            } catch (IOException e) {
            } finally {
                try {
                    FIS.close();
                } catch (IOException e) {
                }

            }





    }
    public static void open_Header_file(Component parent)
    {open_Header_file(parent , false);}

    public static void open_Line_file(Component parent)
    {open_Line_file(parent , false);}



    public static void print_in_console()
    {
        if(Inv_H != null) {
            for (int i = 0; i < Inv_H.size(); i++) {
                System.out.println(Inv_H.get(i).toString());

            }
        }

    }

    public static void Save_Header_file(Component parent)
    {
        String Inv_H_print = "";
        for (int i = 0 ; i<Inv_H.size() ; i++)
        {
            if(Inv_H.get(i)!=null){Inv_H_print+=Inv_H.get(i).string_to_be_saved();Inv_H_print+=System.getProperty("line.separator");}
        }
        FileOutputStream FOS = null;
        JFileChooser JFC = new JFileChooser();
        JFC.changeToParentDirectory();

        if(JFC.showSaveDialog(parent)==JFileChooser.APPROVE_OPTION)
        {


            String ff = JFC.getSelectedFile().getPath();
            ff =  ff.substring(ff.lastIndexOf(".")+1);
            if(ff.equals("csv")) {
                String path = JFC.getSelectedFile().getPath();

                try {
                    FOS = new FileOutputStream(path);

                    byte[] b = Inv_H_print.getBytes();
                    FOS.write(b);
                    Save_Line_file(parent);

                } catch (FileNotFoundException e) {JOptionPane.showMessageDialog(null, "File not found", "File not found ", JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                } finally {
                    try {
                        FOS.close();
                    } catch (IOException e) {
                    }
                }
            }
                else
                {
                    JOptionPane.showMessageDialog(null, "File extension should be .csv", "Wrong Format for Header file ", JOptionPane.WARNING_MESSAGE);
                }
            }


        }



    public static void Save_Line_file(Component parent)
    {
        JFileChooser JFC = new JFileChooser();
        JFC.changeToParentDirectory();
        String Inv_L_print = "";
        for (int i = 0 ; i<Inv_H.size() ; i++)
        {
            if(Inv_H.get(i)!=null){Inv_L_print+=Inv_H.get(i).lines_string_to_be_saved();}
        }
        if(JFC.showSaveDialog(parent)==JFileChooser.APPROVE_OPTION)
        {
            String ff = JFC.getSelectedFile().getPath();
           ff =  ff.substring(ff.lastIndexOf(".")+1);
            if(ff.equals("csv")) {
                FileOutputStream FOS = null;
                String path = JFC.getSelectedFile().getPath();

                try {
                    FOS = new FileOutputStream(path);

                    byte[] b = Inv_L_print.getBytes();
                    FOS.write(b);

                } catch (FileNotFoundException e) { JOptionPane.showMessageDialog(null, "File not found", "File not found ", JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                } finally {
                    try {
                        FOS.close();
                    } catch (IOException e) {
                    }
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "File extension should be .csv", "Wrong Format for Line file ", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
