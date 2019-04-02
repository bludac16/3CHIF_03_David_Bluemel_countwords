/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consumer;

import BL.Book;
import Queue.EmptyException;
import Queue.Queue;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class BookConsumer extends Thread {

    private Queue<Book> books;
    private HashMap<String, Integer> dupWords = new HashMap<String, Integer>();

    public BookConsumer(Queue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        while (true) {
            Book book = null;
            synchronized (books) {
                try {
                    book = books.get();
                    books.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        books.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(BookConsumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    continue;
                }

            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BookConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            dupWords = book.duplicateWords();
        }
    }

    public HashMap<String, Integer> getDupWords() {
        return dupWords;
    }

}
