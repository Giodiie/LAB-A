package bookrecommender.src;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;




public class Valutazioni extends JButton{

    private String pathTo = "ValutazioniLibri.dati.csv";


    public Valutazioni(String bookname,String Author){
        JFrame frame = new JFrame("Valutazioni");
        frame.setLayout(null);
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));





        JLabel titleLabel = new JLabel("BOOK_NAME_PLACEHOLDER");
        JLabel authorLabel = new JLabel("AUTHOR_NAME_PLACE_HOLDER");

        titleLabel.setFont(new Font("arial",Font.BOLD,20));
        titleLabel.setFocusable(false);
        titleLabel.setBounds(15,20,300,50);



        frame.add(titleLabel);


        frame.setVisible(true);
    }

}
