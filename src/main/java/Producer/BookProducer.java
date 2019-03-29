/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Producer;

import BL.Book;
import Queue.FullException;
import Queue.Queue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author David
 */
public class BookProducer extends Thread{
    
    private final Queue<Book> books;
    private File f;
    private String text;
    JFileChooser chooser = new JFileChooser();

    public BookProducer(Queue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        while(true){
            int rueckgabeWert = chooser.showOpenDialog(null);
            if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
            {
                f = chooser.getSelectedFile();
            }
            try {
                ObjectInputStream oos = new ObjectInputStream(new FileInputStream(f));
                
                text = (String) oos.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookProducer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            synchronized(books){
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
