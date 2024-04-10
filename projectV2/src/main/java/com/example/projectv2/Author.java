package com.example.projectv2;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author class
 * Represents an author
 * An author has an authorID, a first name, a last name and a list of books
 */

@Entity(name = "authors")
public class Author {

    /**
     * authorID
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorID;
    /**
     * firstName
     */
    private String firstName;
    /**
     * lastName
     */
    private String lastName;

    /**
     * books
     * Many to many relationship with books for spring data jpa
     * An author can have many books
     * A book can have many authors
     * Join table author_isbn
     * isbn is the foreign key
     * authorID is the foreign key
     */
    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private List<Book> books = new ArrayList<Book>();

    /**
     * Get authorID
     * @return authorID
     */
    public Integer getAuthorID() {
        return authorID;
    }

    /**
     * Set authorID
     * @param authorID
     */
    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    /**
     * Get firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Add book
     * @param book
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Remove book
     * @param book
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Get books
     * @return books
     */
    public List<Book> getBooks() {
        return books;
    }


}
