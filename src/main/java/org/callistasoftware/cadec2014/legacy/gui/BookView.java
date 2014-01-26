package org.callistasoftware.cadec2014.legacy.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.callistasoftware.cadec2014.legacy.Book;

public class BookView extends Container {
    private static final ImageIcon EMPTY_IMAGE = new ImageIcon();

	private static final long serialVersionUID = 1L;

    private JLabel image;
    private JTextArea titleArea;
    private JTextArea descriptionArea;

    public BookView () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleArea = new JTextArea("Book title");
        image = new JLabel(EMPTY_IMAGE);
        image.setPreferredSize(new Dimension(200,300));
        image.setAlignmentX(CENTER_ALIGNMENT);
        descriptionArea = new JTextArea("Book description", 5, 50);
        descriptionArea.setLineWrap(true);
        add(titleArea);
        add(image);
        add(new JScrollPane(descriptionArea));
    }

    public void updateWith(final Book book) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                titleArea.setText(book.getTitle());
                descriptionArea.setText(book.getDescription());
                if (book.getThumbnail() != null) {
                	image.setIcon(new ImageIcon(book.getThumbnail()));
                } else {
                	image.setIcon(EMPTY_IMAGE);
                }
            }
        });
    }
}
