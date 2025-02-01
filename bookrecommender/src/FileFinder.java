package bookrecommender.src;
/*
*
*  This is a test file for implementing over both macOS, Linux and Windows.
*
*
* */

import java.io.File;

public class FileFinder {

    public FileFinder(){
    }

    public File LibrifilePath(){
        String sep = File.separator;
        File LibriDati = new File("." + sep  +"src" + sep + "bookrecommender" + sep+ "data" + sep + "Libri.dati.csv");
        return LibriDati;
    }
    public File UtentiRegistrati(){
        String sep = File.separator;
        File Utenti = new File("."+ sep +"src"+ sep + "bookrecommender"+ sep + "data"+ sep + "UtentiRegistrati.dati.csv");
        return Utenti;
    }
    public File ConsigliLibri(){
        String sep = File.separator;
        File ConsigliPath = new File("."+ sep +"src"+ sep + "bookrecommender"+ sep + "ConsigliLibri.dati.csv");
        return ConsigliPath;
    }
    public File LibreriePathDati(){
        String sep = File.separator;
        File Librerie = new File("."+ sep +"src"+ sep + "bookrecommender"+ sep + "data" + sep + "Librerie.dati.csv");
        return Librerie;
    }
    public File MasterCSVPath(){
        String sep = File.separator;
        File MasterCSV = new File("." + sep +"src"+ sep +"bookrecommender"+ sep + "data" + sep + "Dataset.csv");
        return MasterCSV;
    }
}
