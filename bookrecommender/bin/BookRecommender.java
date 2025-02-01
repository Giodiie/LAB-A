package bookrecommender.bin;

import bookrecommender.src.csvMaker;
import bookrecommender.src.Frame;

import javax.swing.*;

public class BookRecommender {



    public static void main(String[] args) {

        /*csvMaker va utilizzato solo all'inizio.
        Genera Libri.dati.csv partendo da BooksDatasetClean.csv.
        **/
        //csvMaker csvMaker = new csvMaker();
        SwingUtilities.invokeLater(() -> new Frame());



    }
}
