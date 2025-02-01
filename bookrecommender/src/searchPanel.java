package bookrecommender.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class searchPanel extends JPanel implements ActionListener {

    JTextField titleSearch;
    JTextField authorSearch;
    JTextField yearSearch;
    JButton searchButton;
    bookrecommender.src.csvTable csvTable;

    public searchPanel(bookrecommender.src.csvTable csvTable) {
        this.csvTable = csvTable;

        setLayout(null);


        JLabel titleLabel = new JLabel("Search by title");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(15, 15, 150, 25);
        titleLabel.setFocusable(false);

        titleSearch = new JTextField();
        titleSearch.setBounds(15, 45, 200, 25);

        JLabel authorLabel = new JLabel("Author");
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        authorLabel.setBounds(15, 70, 200, 25);
        authorLabel.setFocusable(false);

        authorSearch = new JTextField();
        authorSearch.setBounds(15, 90, 200, 25);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setForeground(Color.BLACK);
        yearLabel.setFont(new Font("Arial", Font.BOLD, 20));
        yearLabel.setBounds(15, 120, 200, 25);
        yearLabel.setFocusable(false);

        yearSearch = new JTextField();
        yearSearch.setBounds(15, 150, 200, 25);

        searchButton = new JButton("Search Book");
        searchButton.addActionListener(this);
        searchButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchButton.setBounds(175, 175, 125, 35);

        add(titleLabel);
        add(searchButton);
        add(titleSearch);
        add(authorLabel);
        add(authorSearch);
        add(yearLabel);
        add(yearSearch);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String titleFilter = titleSearch.getText();
            String authorFilter = authorSearch.getText();
            String yearFilter = yearSearch.getText();

            csvTable.cercaLibro(titleFilter, authorFilter, yearFilter);
        }
    }
    public void getInfo(){

    }
}
