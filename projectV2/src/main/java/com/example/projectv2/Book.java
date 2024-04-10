package com.example.projectv2;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Book class
 * Represents a book
 * A book has an isbn, a title, an edition number, a copy right and a list of authors
 */

@Entity(name = "titles")
public class Book {
    @Id
    private String isbn;
    private String title;
    private String editionNumber;
    private String copyRight;

    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "authorID"))
    private List<Author> authors = new ArrayList<Author>();



    /**
     * Get isbn
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set isbn
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get editionNumber
     * @return editionNumber
     */
    public String getEditionNumber() {
        return editionNumber;
    }

    /**
     * Set editionNumber
     * @param editionNumber
     */
    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Get copyRight
     * @return copyRight
     */
    public String getCopyRight() {
        return copyRight;
    }

    /**
     * Set copyRight
     * @param copyRight
     */
    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    /**
     * Add author
     * @param author
     */
    public void addAuthor(Author author) {
        authors.add(author);
    }

    /**
     * Remove author
     * @param author
     */
    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    /**
     * Get authors
     * @return authors
     */
    public List<Author> getAuthors() {
        return authors;
    }
}
