package org.callistasoftware.cadec2014.legacy;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.callistasoftware.cadec2014.legacy.env.Env;
import org.callistasoftware.cadec2014.legacy.gui.BookView;

/**
 * Hello world!
 * 
 */
public class App {
    private BookView bookView;
    public App() {
        Env.init();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGui();
            }
        });
    }

    private void createGui() {
        JFrame frame = new JFrame("Enterprisey BDD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        bookView = new BookView();
        pane.add(bookView, BorderLayout.CENTER);
        
        JButton searchButton = new JButton("Search for book info");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        pane.add(searchButton, BorderLayout.SOUTH);

        frame.pack();
        center(frame);
        frame.setVisible(true);
    }

    private void center(JFrame frame) {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((int)(center.getX() - (frameDimension.getWidth() / 2)), (int)(center.getY() - (frameDimension.getHeight() / 2)));
    }

    private void search() {
        Book book = new BookController().getBook();
        bookView.updateWith(book);
    }

    public static void main(String[] args) {
        new App();
    }
}
