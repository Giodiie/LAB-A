package bookrecommender.src;

import javax.swing.*;
import java.awt.*;

public class Frame {

    JFrame frame;


    public Frame() {


        frame = new JFrame("Book Recommender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        ToolBar toolBar = new ToolBar();

        csvTable csvTable = new csvTable();
        csvTable.loadCSV(getCSVfilePath());

        JTable table = csvTable.getTable();

        JScrollPane scrollPane = new JScrollPane(table);

        searchPanel searchPanel = new searchPanel(csvTable); // invia la classe csvTable a searchPanel
        searchPanel.setPreferredSize(new Dimension(300, 300));

        frame.add(toolBar, BorderLayout.NORTH);
        frame.add(searchPanel, BorderLayout.EAST);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    String getCSVfilePath(){
        FileFinder fileFinder = new FileFinder();
        String str = String.valueOf(fileFinder.LibrifilePath());

        return str;
    }
}
