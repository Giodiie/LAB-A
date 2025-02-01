package bookrecommender.src;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;


public class LogInPanel implements ActionListener {

    JFrame logInFrame;



    public JButton logInButton;
    public JTextField userField;
    public JPasswordField passwordField;
    public JLabel errorLabel;
    private boolean isLogged;

    public JButton libraryButton;


    public LogInPanel(){
        logInFrame = new JFrame("Log In");
        logInFrame.setLayout(null);
        logInFrame.setSize(400,200);
        logInFrame.setLocationRelativeTo(null);
        logInFrame.setResizable(false);



        JLabel userLabel = new JLabel("USER");
        userLabel.setBounds(15,10,150,50);
        userLabel.setFont(new Font("Arial",Font.BOLD,20));
        userField = new JTextField();
        userField.setBounds(150,20,200,25);


        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(15,40,150,50);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,20));
        passwordField = new JPasswordField();
        passwordField.setBounds(150,50,200,25);


        libraryButton = new JButton("LIBRARY");
        libraryButton.setBounds(250,100,100,50);
        libraryButton.addActionListener(this);
        libraryButton.setFocusable(false);

        logInButton = new JButton("LOG IN");
        logInButton.setBounds(150,100,100,50);
        logInButton.setFocusable(false);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                Logged(username,password);
                if(isLogged == true){
                   logInFrame.add(libraryButton);
                   logInFrame.revalidate();
                   logInFrame.repaint();
                } else{
                    errorLabel.setFont(new Font("Arial",Font.BOLD,20));
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Log In First");
                }

            }
        });

        errorLabel = new JLabel();
        errorLabel.setBounds(15,100,150,50);
        errorLabel.setFocusable(false);

        logInFrame.add(userLabel);
        logInFrame.add(userField);
        logInFrame.add(passwordLabel);
        logInFrame.add(passwordField);
        logInFrame.add(logInButton);
        logInFrame.add(errorLabel);

        logInFrame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == libraryButton){
                generateLibrary();

        }

    }

    public boolean Logged(String username, String password){
        isLogged = false;

        try(CSVReader reader = new CSVReader(new FileReader(getRegiterFilePath()))) {

            String[] columns;
            while((columns = reader.readNext()) != null) {
                if(columns.length >= 4 && columns[3].equals(username)){
                    if(columns.length >= 4 && columns[4].equals(password)){
                        errorLabel.setFont(new Font("Arial",Font.BOLD,20));
                        errorLabel.setForeground(new Color(0,150,0));
                        errorLabel.setText("Logged In!");
                        isLogged = true;


                    }
                }
            }

        }catch(Exception e){
            errorLabel.setFont(new Font("Arial",Font.BOLD,20));
            errorLabel.setForeground(new Color(150,0,0));
            errorLabel.setText("ERROR");
            e.printStackTrace();

        }


        return isLogged;
    }

    private String getRegiterFilePath(){
        FileFinder fileFinder = new FileFinder();


        String str = String.valueOf(fileFinder.UtentiRegistrati());


        return str;

    }
    public void generateLibrary(){
        personalLibrary library = new personalLibrary();

    }




}
