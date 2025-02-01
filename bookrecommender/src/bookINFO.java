package bookrecommender.src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import com.opencsv.*;
import java.io.*;


public class bookINFO {

    private JFrame frame;


    public bookINFO(String bookName, String author, String year, String desc){


        frame = new JFrame("Informazioni Libro");
        frame.setSize(950,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);



        JTextArea title = new JTextArea(bookName);
        title.setEditable(false);
        title.setFont(new Font("Arial",Font.BOLD,15));
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        JScrollPane titleScroll = new JScrollPane(title);
        titleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        titleScroll.setBounds(0,25,250,150);



        JTextArea authorArea = new JTextArea(author);
        authorArea.setEditable(false);
        authorArea.setLineWrap(true);
        authorArea.setWrapStyleWord(true);
        authorArea.setFont(new Font("Arial",Font.BOLD,15));
        JScrollPane authorScroll = new JScrollPane(authorArea);
        authorScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        authorScroll.setBounds(250,25,200,150);


        JLabel yearLabel = new JLabel(year);
        yearLabel.setFont(new Font("Arial",Font.BOLD,15));
        yearLabel.setBounds(500,25,100,50);

        JTextArea description = new JTextArea();
        description.setFont(new Font("Arial",Font.BOLD,15));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setText(desc);
        description.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(description);
        descriptionScrollPane.setBounds(600,25,300,150);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        frame.add(titleScroll);
        frame.add(authorScroll);
        frame.add(yearLabel);
        frame.add(descriptionScrollPane);


        frame.setVisible(true);
    }





}
