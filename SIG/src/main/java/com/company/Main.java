package com.company;


import com.company.model.FileOperations;
import com.company.view.MainFrame;
import com.company.view.UIPanel;


public class Main {

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        FileOperations.open_Header_file(mf , true);
        mf.setVisible(true);
        mf.setContentPane(new UIPanel());
        mf.pack();
        //mf.fill_H_table();


       // FileOperations.print_in_console();
    }
}
