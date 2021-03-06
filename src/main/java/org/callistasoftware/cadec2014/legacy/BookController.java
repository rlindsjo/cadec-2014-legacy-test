package org.callistasoftware.cadec2014.legacy;

import static javax.swing.JOptionPane.*;

import org.callistasoftware.cadec2014.legacy.env.DataFetcher;
import org.callistasoftware.cadec2014.legacy.env.Env;

public class BookController {
    static final Book NO_BOOK = new Book("No book found", null, "No book found.");
	private final String key;
	private final DataFetcher fetcher;

	public BookController() {
    	this(Env.getInstance().getProperties().getProperty("api.key"), 
    			Env.getInstance().getDataFetcher());
	}
	
	BookController(String key, DataFetcher fetcher) {
		this.key = key;
		this.fetcher = fetcher;
	}

	public Book getBook() {
        try {
            String isbn = getIsbn();
            if (isbn == null) {
                return NO_BOOK;
            }
            byte[] raw = fetcher.get(isbn, key);
            Book book = parse(raw);
            book.setThumbnail(fetcher.get(book.getThumbnailUrl()));
            return book;
        } catch (Exception e) {
            String message = "Could not get book information.\n" + e.getMessage();
            showError(message);
            return NO_BOOK;
        }
    }

	Book parse(byte[] raw) {
		return Env.getInstance().getJsonParser().parse(raw);
	}

	void showError(String message) {
		showMessageDialog(null, message, "Error", ERROR_MESSAGE);
	}

	String getIsbn() {
		return showInputDialog("Enter book ISBN");
	}
}
