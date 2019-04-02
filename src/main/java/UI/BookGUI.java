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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class BookGUI extends javax.swing.JFrame {

    /**
     * Creates new form BookGUI
     */
    public BookGUI() {
        initComponents();

    }

    Queue<Book> queue = new Queue<>(1000000);

    BookProducer prod1 = new BookProducer(queue);
    BookConsumer cons1 = new BookConsumer(queue);
    private HashMap<String, Integer> dupWords = new HashMap<String, Integer>();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btStart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taOutput = new javax.swing.JTextArea();
        btUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btStart.setText("Start");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });
        getContentPane().add(btStart, java.awt.BorderLayout.PAGE_START);

        taOutput.setColumns(20);
        taOutput.setRows(5);
        jScrollPane1.setViewportView(taOutput);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btUpdate.setText("Update");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btUpdate, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed

        new Thread(prod1, "Producer 1").start();
        new Thread(cons1, "Consumer 1").start();
        try {
            prod1.join();
            cons1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BookGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btStartActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        dupWords = cons1.getDupWords();
        for (String i : dupWords.keySet()) {
            String out = String.format("%s: %d\n", i, dupWords.get(i));
            taOutput.append(i);
        }
    }//GEN-LAST:event_btUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStart;
    private javax.swing.JButton btUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taOutput;
    // End of variables declaration//GEN-END:variables
}