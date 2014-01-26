package org.callistasoftware.cadec2014.legacy;

import static javax.swing.JOptionPane.*;

import org.callistasoftware.cadec2014.legacy.env.DataFetcher;
import org.callistasoftware.cadec2014.legacy.env.Env;

public class BookController {
    static final Book NO_BOOK = new Book("No book found", null, "No book found.");

	public Book getBook() {
        try {
            String isbn = getIsbn();
            if (isbn == null) {
                return NO_BOOK;
            }
        	String key = Env.getInstance().getProperties().getProperty("api.key");
        	DataFetcher fetcher = Env.getInstance().getDataFetcher();
            byte[] raw = fetcher.get(isbn, key);
            Book book = Env.getInstance().getJsonParser().parse(raw);
            book.setThumbnail(fetcher.get(book.getThumbnailUrl()));
            return book;
        } catch (Exception e) {
            String message = "Could not get book information.\n" + e.getMessage();
            showMessageDialog(null, message, "Error", ERROR_MESSAGE);
            return NO_BOOK;
        }
    }

	String getIsbn() {
		return showInputDialog("Enter book ISBN");
	}
}
