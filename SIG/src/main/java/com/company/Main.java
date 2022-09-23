
//Main class that starts the program
package com.company;


import com.company.model.FileOperations;
import com.company.view.MainFrame;
import com.company.view.UIPanel;


public class Main {

    public static void main(String[] args) {
        MainFrame mf = new MainFrame(); //Main Frame of the project
        FileOperations.open_Header_file(mf , true); //Load Invoices from the default CSV files 
        mf.setVisible(true);
        mf.setContentPane(new UIPanel());
        mf.pack();
    }
}
