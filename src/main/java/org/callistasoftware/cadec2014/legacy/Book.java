package org.callistasoftware.cadec2014.legacy;

public class Book {

    private final String description;
    private final String title;
    private String thumbnailUrl;
    private byte[] thumbnail;

    public Book(String title, String thumbnailUrl, String description) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public byte[] getThumbnail() {
        return thumbnail;
    }
}
