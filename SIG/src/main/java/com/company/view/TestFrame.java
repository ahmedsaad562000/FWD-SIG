//A frame that was only used for testing in intellij but has no effect on the actual project

package com.company.view;

import com.company.model.FileOperations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestFrame extends JFrame implements ActionListener {


    private JButton  Test_Button;

    public TestFrame()
    {
        ////////////////////////////////////////////////
        super("Console Test");
        setSize(600 , 400);
        setLocation(600 , 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        /////////////////////////////////////////////




        Test_Button = new JButton("Open");
        Test_Button.setMnemonic('T');


        Test_Button.addActionListener(this);
        Test_Button.setActionCommand("open");
        add(Test_Button);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("open"))
        {
            FileOperations.open_Header_file(this , true);
        }

    }

}

