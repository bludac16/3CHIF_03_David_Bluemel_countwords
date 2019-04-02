/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BL.Book;
import Consumer.BookConsumer;
import Producer.BookProducer;
import Queue.Queue;

/**
 *
 * @author David
 */
public class Main {
    public static void main(String[] args) {
        Queue<Book> queue = new Queue<>(5);

        BookProducer prod1 = new BookProducer(queue);
        BookConsumer cons1 = new BookConsumer(queue);
        BookConsumer cons2 = new BookConsumer(queue);

        new Thread(prod1, "Producer 1").start();
        new Thread(cons1, "Consumer 1").start();
        new Thread(cons2, "Consumer 2").start();
    }
}
