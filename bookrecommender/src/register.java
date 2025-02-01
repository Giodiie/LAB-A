package bookrecommender.src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;


public class register implements ActionListener {

    public JFrame regFrame;

    public JButton registerButton;
    public JTextField nameField, emailField, FiscalNumberField, userIDField;
    public JPasswordField passwordField;
    private String nomecognome;
    public ToolBar toolBar;
    JLabel statusLabel;

    public register() {
        regFrame = new JFrame("Register");
        regFrame.setSize(390,300);


        regFrame.setLayout(null);
        regFrame.setResizable(false);


        JLabel nameLabel = new JLabel("NOME E COGNOME");
        nameLabel.setBounds(10, 10, 150, 50);
        nameLabel.setFocusable(false);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));

        nameField = new JTextField();
        nameField.setBounds(160, 15, 200, 30);

        JLabel emailLable = new JLabel("EMAIL");
        emailLable.setBounds(10, 50, 150, 50);
        emailLable.setFocusable(false);
        emailLable.setFont(new Font("Arial", Font.BOLD, 15));

        emailField = new JTextField();
        emailField.setBounds(160, 50, 200, 30);

        JLabel FiscalNumberLable = new JLabel("FISCAL NUMBER");
        FiscalNumberLable.setBounds(10, 80, 150, 50);
        FiscalNumberLable.setFocusable(false);
        FiscalNumberLable.setFont(new Font("Arial", Font.BOLD, 15));
        FiscalNumberField = new JTextField();
        FiscalNumberField.setBounds(160, 80, 200, 30);

        JLabel userIDLabel = new JLabel("USER ID");
        userIDLabel.setBounds(10, 120, 150, 50);
        userIDLabel.setFocusable(false);
        userIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
        userIDField = new JTextField();
        userIDField.setBounds(160, 120, 200, 30);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(10, 160, 150, 50);
        passwordLabel.setFocusable(false);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 160, 200, 30);

        registerButton = new JButton("REGISTRAZIONE");
        registerButton.setBounds(10, 200, 350, 50);
        registerButton.setFont(new Font("Arial", Font.BOLD, 25));
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        statusLabel.setBounds(200, 220, 150, 50);
        statusLabel.setFocusable(false);

        regFrame.add(nameLabel);
        regFrame.add(nameField);
        regFrame.add(emailLable);
        regFrame.add(emailField);
        regFrame.add(FiscalNumberLable);
        regFrame.add(FiscalNumberField);
        regFrame.add(userIDLabel);
        regFrame.add(userIDField);
        regFrame.add(passwordLabel);
        regFrame.add(passwordField);
        regFrame.add(registerButton);
        regFrame.add(statusLabel);
        regFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            try {
                registrazione();
            } catch (IOException | CsvValidationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void registrazione() throws IOException, CsvValidationException {

        nomecognome = nameField.getText();
        String email = emailField.getText();
        String fiscalnumber = FiscalNumberField.getText();
        String userid = userIDField.getText();
        String password = new String(passwordField.getPassword());

        CSVWriter writer = new CSVWriter(new FileWriter(pathToRegisterFile(), true));
        writer.writeNext(new String[]{nomecognome, email, fiscalnumber, userid, password});
        writer.flush();
        writer.close();

        nameField.setText("");
        emailField.setText("");
        FiscalNumberField.setText("");
        userIDField.setText("");
        passwordField.setText("");

        JOptionPane.showMessageDialog(registerButton,"Registrato correttamente!");

    }
    //cerca dentro alla prima colonna.
    public boolean isRegistered() throws IOException, CsvValidationException {

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(pathToRegisterFile()));
            String[] line;
            while ((line = reader.readNext()) != null) {

                if (line.length > 0 && line[0].equals(nomecognome)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    public File pathToRegisterFile(){
        FileFinder fileFinder = new FileFinder();
        return fileFinder.UtentiRegistrati();

    }
}
