package bookrecommender.src;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class personalLibrary implements ActionListener {
    private JFrame Library;
    private JTextField nameLibrary;
    private JButton newLibrary;
    private JPanel tablePanel;
    private JTable masterTable;
    private DefaultTableModel masterTableModel;
    private List<LibraryTable> libraryTables = new ArrayList<>();
    private JTable selectedLibraryTable;
    public String bookName;
    public String author;
    public String year;
    public String description;




    public personalLibrary() {
        Library = new JFrame("Libreria Personale");
        Library.setSize(1000, 600);
        Library.setLocationRelativeTo(null);
        Library.setResizable(false);
        Library.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(null);
        filterPanel.setBackground(Color.LIGHT_GRAY);
        filterPanel.setPreferredSize(new Dimension(300, 600));

        JLabel createLibrary = new JLabel("Crea Libreria");
        createLibrary.setFont(new Font("Arial", Font.BOLD, 20));
        createLibrary.setBounds(10, 10, 200, 30);

        nameLibrary = new JTextField();
        nameLibrary.setBounds(10, 50, 200, 30);

        newLibrary = new JButton("+");
        newLibrary.setFont(new Font("Arial", Font.BOLD, 20));
        newLibrary.setBounds(220, 50, 50, 30);
        newLibrary.setFocusable(false);
        newLibrary.addActionListener(this);

        JButton saveLibraries = new JButton("Salva Librerie");
        saveLibraries.setFont(new Font("Arial", Font.BOLD, 15));
        saveLibraries.setBounds(10, 100, 200, 30);
        saveLibraries.setFocusable(false);
        saveLibraries.addActionListener(e -> saveLibrariesToCSV());

        JButton removeLibrary = new JButton("Rimuovi Libreria");
        removeLibrary.setFont(new Font("Arial", Font.BOLD, 15));
        removeLibrary.setBounds(10, 150, 200, 30);
        removeLibrary.setFocusable(false);
        removeLibrary.addActionListener(e -> removeSelectedLibrary());

        filterPanel.add(createLibrary);
        filterPanel.add(nameLibrary);
        filterPanel.add(newLibrary);
        filterPanel.add(saveLibraries);
        filterPanel.add(removeLibrary);

        Library.add(filterPanel, BorderLayout.EAST);

        // creazione di MasterTable, serve per vedere TUTTI i libri.
        masterTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }
        };
        masterTable = new JTable(masterTableModel);
        JScrollPane masterScrollPane = new JScrollPane(masterTable);
        masterScrollPane.setPreferredSize(new Dimension(700, 300));
        Library.add(masterScrollPane, BorderLayout.NORTH);

        // tablePanel serve per gestire dinamicamente le librerie generate.
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS)); //boxlayout serve per la generazione dinamica del layout
        JScrollPane scrollPane = new JScrollPane(tablePanel);
        Library.add(scrollPane, BorderLayout.CENTER);

        //funzione che carica i libri dal file CSV master.
        //1
        loadBooksFromCSV(pathToCSVMaster());
        //funzione che carica le librerie dal file CSV.
        loadLibrariesFromCSV();

        // menu popUp con tasto destro per aggiungere i libri alla libreria personale.
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem addToLibraryItem = new JMenuItem("Aggiungi alla libreria");
        addToLibraryItem.addActionListener(e -> addToLibraryFromContextMenu());
        popupMenu.add(addToLibraryItem);
        masterTable.setComponentPopupMenu(popupMenu);

        Library.setVisible(true);
    }

    private void loadBooksFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext();
            if (headers != null) {
                for (String header : headers) {
                    masterTableModel.addColumn(header);
                }

                String[] values;
                while ((values = reader.readNext()) != null) {
                    masterTableModel.addRow(values);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void loadLibrariesFromCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(pathToLibrary()))) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                String libraryName = values[0];
                addNewLibraryTable(libraryName);

                //aggiunge i libri alla libreria
                for (int i = 1; i < values.length; i++) {
                    String[] bookData = values[i].split(";");
                    if (bookData.length == masterTableModel.getColumnCount()) {
                        DefaultTableModel model = (DefaultTableModel) libraryTables.get(libraryTables.size() - 1).table.getModel();
                        model.addRow(bookData);
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void saveLibrariesToCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(pathToLibrary()))) {
            for (LibraryTable libraryTable : libraryTables) {
                List<String> row = new ArrayList<>();
                row.add(libraryTable.name);
                DefaultTableModel model = (DefaultTableModel) libraryTable.table.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    StringBuilder bookData = new StringBuilder();
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bookData.append(model.getValueAt(i, j));
                        if (j < model.getColumnCount() - 1) {
                            bookData.append(";");
                        }
                    }
                    row.add(bookData.toString());
                }
                writer.writeNext(row.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newLibrary) {
            String libraryName = nameLibrary.getText().trim();
            if (!libraryName.isEmpty()) {
                addNewLibraryTable(libraryName);
                nameLibrary.setText("");
            } else {
                JOptionPane.showMessageDialog(Library, "Dare un nome alla libreria.");
            }
        }
    }

    private void addNewLibraryTable(String libraryName) {
        // Crea una nuova tabella con le stesse colonne della tabella "master"
        // Tabella settata in modalità non modificabile.

        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < masterTableModel.getColumnCount(); i++) {
            tableModel.addColumn(masterTableModel.getColumnName(i));
        }

        // creazione di una nuova JTable con il tableModel
        JTable table = new JTable(tableModel);
        libraryTables.add(new LibraryTable(libraryName, table));
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedLibraryTable = table; // seleziona la tabella con un click sopra di essa.
            }
        });

        // crea un nuovo JPanel per JTable e Label
        JPanel libraryPanel = new JPanel();
        libraryPanel.setLayout(new BorderLayout());
        JLabel libraryLabel = new JLabel("Libreria: " + libraryName);
        libraryPanel.add(libraryLabel, BorderLayout.NORTH);
        libraryPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Menu popup che compare al click di RMB, serve per rimuovere i libri dalla libreria personale, o per inserire valutazioni.
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem removeBookItem = new JMenuItem("Rimuovi Libro");
        removeBookItem.addActionListener(e -> removeSelectedBook(table));
        JMenuItem inserisciValuazione = new JMenuItem("Valuta Libro");
        inserisciValuazione.addActionListener(e -> InserisciValutazione());

        popupMenu.add(removeBookItem);
        popupMenu.add(inserisciValuazione);
        table.setComponentPopupMenu(popupMenu);

        tablePanel.add(libraryPanel);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void addToLibraryFromContextMenu() {
        int selectedRow = masterTable.getSelectedRow();
        if (selectedRow >= 0 && selectedLibraryTable != null) {
            Object[] rowData = new Object[masterTableModel.getColumnCount()];
            for (int i = 0; i < masterTableModel.getColumnCount(); i++) {
                rowData[i] = masterTableModel.getValueAt(selectedRow, i);
            }
            DefaultTableModel selectedLibraryModel = (DefaultTableModel) selectedLibraryTable.getModel();
            selectedLibraryModel.addRow(rowData);
        } else {
            JOptionPane.showMessageDialog(Library, "Selezionare una libreria e un libro.");
        }
    }

    private void removeSelectedLibrary() {
        if (selectedLibraryTable != null) {
            int index = -1;
            for (int i = 0; i < libraryTables.size(); i++) {
                if (libraryTables.get(i).table == selectedLibraryTable) {
                    index = i;
                    break;
                }
            }

            if (index >= 0) {
                tablePanel.remove(index);
                libraryTables.remove(index);
                selectedLibraryTable = null;
                tablePanel.revalidate();
                tablePanel.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(Library, "Selezionare una libreria da rimuovere.");
        }
    }

    private void removeSelectedBook(JTable libraryTable) {
        int selectedRow = libraryTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) libraryTable.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(Library, "Selezionare un libro da rimuovere.");
        }
    }


    private static class LibraryTable {
        String name;
        JTable table;

        LibraryTable(String name, JTable table) {
            this.name = name;
            this.table = table;
        }
    }
    private String pathToLibrary(){
        FileFinder fileFinder = new FileFinder();
        String str = String.valueOf(fileFinder.LibreriePathDati());
        return str;
    }
    private String pathToCSVMaster(){
        FileFinder fileFinder = new FileFinder();
        String str = String.valueOf(fileFinder.LibrifilePath());
        return str;

    }

    private String nameFinder(){




        return bookName;
    }
    private String authorFinder(){



        return author;
    }
    private String yearFinder(){
        return year;

    }
    private String descFinder(){
        return description;

    }

    private void visualizzaLibro(){
        bookINFO bkInfo = new bookINFO(nameFinder(),authorFinder(),yearFinder(),descFinder());

    }
    private void InserisciValutazione(){
        //Valutazioni valutazioni = new Valutazioni();


    }




}
