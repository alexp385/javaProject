package com.example.projectv2;

import org.springframework.data.repository.CrudRepository;

/**
 * BookRepository interface
 * Used to access the database and perform operations on the books table
 * Extends CrudRepository
 * Used to get, add, update and delete books
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * Find book by isbn
     * @param isbn
     * @return book with the given isbn
     */
    Book findBookByIsbn(String isbn);



}
