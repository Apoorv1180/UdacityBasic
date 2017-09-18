
package com.example.android.projectseven;

public class Book {

    /**
     * Title of the earthquake event
     */
    public final String title;
    public final String[] author;
    private String bookInfoLink;
    public final String description;

    public Book(String bookTitle, String[] bookAuthor, String bookDescription, String booklink) {
        title = bookTitle;
        author = bookAuthor;
        description = bookDescription;
        bookInfoLink = booklink;
    }

    public String getBookTitle() {
        return "Title :" + title;
    }

    public String getBookInfoLink() {
        return bookInfoLink;
    }

    public String[] getAuthors() {
        return author;
    }

    public String getAllAuthors() {
        String s = "";
        for (int i = 0; i < author.length; i++) {
            if (i == author.length - 1)
                s += author[i];
            else
                s += author[i] + ", ";
        }
        return "Authors :" + s;
    }

    public String getBookDescription() {
        return "Description :" + description;
    }

}
