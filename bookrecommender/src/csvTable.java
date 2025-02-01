package bookrecommender.src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvTable {

    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;
    private String author;
    private String title;
    private String year;
    private String description;

    public csvTable() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem openItem = new JMenuItem("Informazioni");

        openItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();  // Get the selected row when the menu item is clicked
            getInfo(table, selectedRow);
            visualizzaLibro();
        });

        popupMenu.add(openItem);
        table.setComponentPopupMenu(popupMenu);

        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    }

    public void loadCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String[]> rows = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] cells = parseCSVLine(line);
                rows.add(cells);
            }

            if (!rows.isEmpty()) {
                String[] columnNames = rows.get(0);
                tableModel.setColumnIdentifiers(columnNames);

                for (int i = 1; i < rows.size(); i++) {
                    tableModel.addRow(rows.get(i));
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String[] parseCSVLine(String line) {
        StringBuilder cell = new StringBuilder();
        List<String> cells = new ArrayList<>();

        boolean inQuotes = false;

        for (char ch : line.toCharArray()) {
            if (ch == '"') {
                inQuotes = !inQuotes;
            } else if (ch == ',' && !inQuotes) {
                cells.add(cell.toString().trim());
                cell.setLength(0);
            } else {
                cell.append(ch);
            }
        }

        cells.add(cell.toString().trim());
        return cells.toArray(new String[0]);
    }

    public void cercaLibro(String titleFilter, String authorFilter, String yearFilter) {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (titleFilter != null && !titleFilter.trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + titleFilter, 0));
        }
        if (authorFilter != null && !authorFilter.trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + authorFilter, 1));
        }
        if (yearFilter != null && !yearFilter.trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("^" + yearFilter + "$", 2));
        }

        RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(compoundRowFilter);
    }

    public JTable getTable() {
        return table;
    }

    public void visualizzaLibro() {
        bookINFO bookINFO = new bookINFO(title, author, year, description);
    }

    public void getInfo(JTable table, int row) {
        if (row != -1) {
            title = table.getValueAt(row, 0).toString();
            author = table.getValueAt(row, 1).toString();
            year = table.getValueAt(row, 2).toString();
            description = "brief description test holder space multiple line holder space. testing for multiple lines. \nAbra Kadabra Kurwa! \nHello world \nMy ASs Is FaT \nThe magic world of jurassic park was a novel written for kids. \nUnfortunately the novel never saw the shelves, due to the and I quote \"unholiness of the language used to describe such atrocities\" ";


        } else {
            // Handle the case when no row is selected (optional)
            JOptionPane.showMessageDialog(table, "ERROR 10 NO BOOK SELECTED \nPLEASE SELECT A BOOK. \n\tTwat.");

        }
    }
}
