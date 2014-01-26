package org.callistasoftware.cadec2014.legacy.env;

import org.callistasoftware.cadec2014.legacy.Book;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class BookParser {
    private JsonFactory factory;

    BookParser() {
        factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper();
        factory.setCodec(mapper);
    }

    /**
     * Parse raw bytes of JSON to Book.
     * @param raw
     * @return Book
     * @throws RuntimeException if raw data is invalid JSON or invalid book response
     */
    public Book parse(byte[] raw) {
        try {
            JsonNode json = factory.createJsonParser(raw).readValueAsTree();
            return build(json);
        } catch (Exception e) {
            throw new RuntimeException(e); 
        }
    }

    private Book build(JsonNode json) {
        System.err.println(json);
        int hits = json.get("totalItems").getIntValue();
        if (hits != 1) {
            throw new RuntimeException("Wrong number of hits. Got " + hits);
        }
        JsonNode bookInfo = json.get("items").get(0).get("volumeInfo");
        String title = bookInfo.get("title").getTextValue();
        String description = bookInfo.get("description").getTextValue();
        String thumbnailUri = bookInfo.get("imageLinks").get("thumbnail").getTextValue();
        return new Book(title, thumbnailUri, description);
    }
}
