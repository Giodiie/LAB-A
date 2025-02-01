package bookrecommender.src;

import com.opencsv.exceptions.CsvValidationException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class ToolBar extends JPanel {

    JFrame utilityFrame;

    JButton UserButton;
    JComboBox<String> combo;
    JButton searchBook;
    JButton libraryButton;

    JPanel registerPanel;
    public JPanel LogInPanel;

    public ToolBar() {

        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(new Color(48, 111, 97));

        UserButton = new JButton("User");
        UserButton.setFocusable(false);
        UserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                UserPanel user = new UserPanel();
            }
        });


        add(UserButton);




    }



}
