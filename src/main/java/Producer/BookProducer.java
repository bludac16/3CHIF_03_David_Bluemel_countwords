/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Producer;

import BL.Book;
import Queue.FullException;
import Queue.Queue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author David
 */
public class BookProducer extends Thread {

    private final Queue<Book> books;
    private File f;
    private String text;
    JFileChooser chooser = new JFileChooser();

    public BookProducer(Queue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        int rueckgabeWert = chooser.showOpenDialog(null);
        if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();
        } else {
            chooser.cancelSelection();
        }
        while (true) {

            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }

                text = br.toString();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex);
            }

            synchronized (books) {
                try {
                    books.put(new Book(f.getName(), text));
                    books.notifyAll();
                } catch (FullException ex) {
                    try {
                        books.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
