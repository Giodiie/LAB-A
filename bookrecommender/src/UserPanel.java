package bookrecommender.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class UserPanel{

    public JButton LogButton, RegButton;
    public UserPanel(){
        JFrame frame = new JFrame("User");
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        LogButton = new JButton("Log In");
        LogButton.setBounds(100,150, 200, 50);
        LogButton.setFont(new Font("Arial", Font.BOLD, 35));
        LogButton.setFocusable(false);
        LogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInPanel login = new LogInPanel();
                frame.dispose();

            }
        });

        RegButton = new JButton("Register");
        RegButton.setBounds(100,50, 200, 50);
        RegButton.setFont(new Font("Arial", Font.BOLD, 35));
        RegButton.setFocusable(false);
        RegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register reg = new register();
                frame.dispose();
            }
        });



        frame.add(LogButton);
        frame.add(RegButton);
        frame.setVisible(true);
    }




}
