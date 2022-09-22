package com.company.view;

import com.company.model.FileOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JMenuBar Jmb;
    private JMenu File;
    private JMenuItem openmenuitem;
    private JMenuItem savemenuitem;
    private JMenuItem exitmenuitem;


    private JTextArea JTA;
    private JPanel ContentPanel;
    private JPanel LeftSidePanel;
    private JPanel RightSidePanelTop;
    private JPanel RightSidePanelBot;
    private JTable Inv_H_Table;

    public MainFrame() {
        ////////////////////////////////////////////////
        super("Menu bar");
        setSize(1280, 720);
        setLocation(400, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        setLayout(new GridLayout(1, 2));
        /////////////////////////////////////////////
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            FileOperations.open_Header_file(this, false);
        } else if (e.getActionCommand().equals("save")) {
            FileOperations.Save_Header_file(this);
        }
        else  if(e.getActionCommand().equals("exit"))
        {
            System.exit(0);
        }  
    }
}

