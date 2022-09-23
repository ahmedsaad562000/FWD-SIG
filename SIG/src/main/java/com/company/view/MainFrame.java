package com.company.view;

import com.company.Controller.Control;
import com.company.model.FileOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
   //Local Coomponents of Main Frame
    private JMenuBar Jmb;
    private JMenu File;
    private JMenuItem openmenuitem;
    private JMenuItem savemenuitem;
    private JMenuItem exitmenuitem;

    
  

    public MainFrame() {
        
        ////////////////////////////////////////////////
        //Main frame settings
        super("Menu bar");
        setSize(1280, 720);
        setLocation(400, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridLayout(1, 2));
        /////////////////////////////////////////////
        //Menubar setup
        Jmb = new JMenuBar();
        File = new JMenu("File");
        File.setMnemonic('F');


        openmenuitem = new JMenuItem("Load File", 'L');
        savemenuitem = new JMenuItem("Save File", 'S');
        exitmenuitem = new JMenuItem("Exit", 'E');

        openmenuitem.addActionListener(this);
        openmenuitem.setActionCommand("open");
        savemenuitem.addActionListener(this);
        savemenuitem.setActionCommand("save");
        exitmenuitem.addActionListener(this);
        exitmenuitem.setActionCommand("exit");


        File.add(openmenuitem);
        File.add(savemenuitem);
        File.addSeparator();
        File.add(exitmenuitem);


        Jmb.add(File);

        setJMenuBar(Jmb);
        
            

    }

    //Open , Save and Exit menuitems
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            FileOperations.open_Header_file(this, false);
            UIPanel ss = (UIPanel) this.getContentPane();
            Control.Update_Header_Table(ss.getInv_H_Table());
        } else if (e.getActionCommand().equals("save")) {
            FileOperations.Save_Header_file(this);
        }
        else  if(e.getActionCommand().equals("exit"))
        {
            System.exit(0);
        }  
    }
}

