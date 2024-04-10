package com.example.projectv2;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * MainController class
 * Controller class for the project
 * Handles requests for books and authors
 * Contains methods to get, add, update and delete books and authors
 * Handles all api requests for books and authors
 */

@RestController
@RequestMapping("/")
public class MainController {

    public static final  String BOOK = "/books";

    public static final  String AUTHOR = "/authors";


    /**
     * BookRepository object
     * Used to access the database and perform operations on the books table
     * Autowired by Spring
     * Used to get, add, update and delete books
     */
    @Autowired
    private BookRepository bookRepo;

    /**
     * AuthorRepository object
     * Used to access the database and perform operations on the authors table
     * Autowired by Spring
     * Used to get, add, update and delete authors
     */
    @Autowired
    private AuthorRepository authorRepo;

    /**
     * Get all books
     * GetMapping for /books by Spring
     * @return all books
     */
    @GetMapping(BOOK)
    public @ResponseBody Iterable<Book> getBooks() {
        return bookRepo.findAll();
    }

    /**
     * Get book by isbn
     * GetMapping for /books/{isbn} by Spring
     * @param isbn
     * @return book with the given isbn
     */
    @GetMapping(path=BOOK + "/{isbn}")
    public @ResponseBody Book getBook(@PathVariable String isbn) {
        return bookRepo.findBookByIsbn(isbn);
    }

    /**
     * Add book
     * PostMapping for /books by Spring
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyRight
     * @param authorId
     * @return message
     */
    @PostMapping(path = BOOK)
    public @ResponseBody String addBook(@RequestParam String isbn, @RequestParam String title, @RequestParam String editionNumber, @RequestParam String copyRight, @RequestParam Integer authorId) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(editionNumber);
        book.setCopyRight(copyRight);

        Optional <Author> author = authorRepo.findById(authorId);
        if (author.isPresent()) {
            book.addAuthor(author.get());
            bookRepo.save(book);
            return "Book added";
        }

        return "Author not found";
    }

    /**
     * Update book
     * PutMapping for /books/{isbn} by Spring
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyRight
     * @param authorId
     * @return message
     */
    @PutMapping(path = BOOK + "/{isbn}")
    public @ResponseBody String updateBook(@PathVariable String isbn, @RequestParam String title, @RequestParam String editionNumber, @RequestParam String copyRight, @RequestParam Integer authorId) {
        Book book = bookRepo.findBookByIsbn(isbn);
        if (book != null) {
            book.setTitle(title);
            book.setEditionNumber(editionNumber);
            book.setCopyRight(copyRight);

            Optional <Author> author = authorRepo.findById(authorId);
            if (author.isPresent()) {
                book.addAuthor(author.get());
            }

            bookRepo.save(book);

            return "Book updated";
        }
        return "Book not found";
    }

    /**
     * Delete book
     * DeleteMapping for /books/{isbn} by Spring
     * @param isbn
     * @return message
     */
    @DeleteMapping(path = BOOK + "/{isbn}")
    public @ResponseBody String deleteBook(@PathVariable String isbn) {
        Book book = bookRepo.findBookByIsbn(isbn);
        if (book != null) {
            bookRepo.delete(book);
            return "Book deleted";
        }
        return "Book not found";
    }

    /**
     * Get all authors
     * GetMapping for /authors by Spring
     * @return all authors
     */
    @GetMapping(path = AUTHOR)
    public @ResponseBody Iterable<Author> getAuthors() {
        return authorRepo.findAll();
    }

    /**
     * Get author by authorID
     * GetMapping for /authors/{authorID} by Spring
     * @param authorID
     * @return author with the given authorID
     */
    @GetMapping(path=AUTHOR + "/{authorID}")
    public @ResponseBody Author getAuthor(@PathVariable Integer authorID) {
        return authorRepo.findById(authorID).orElse(null);
    }

    /**
     * Add author
     * PostMapping for /authors by Spring
     * @param firstName
     * @param lastName
     * @return author
     */
    @PostMapping(path = AUTHOR)
    public @ResponseBody Author addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepo.save(author);
        return author;
    }

    /**
     * Update author
     * PutMapping for /authors/{authorID} by Spring
     * @param authorID
     * @param firstName
     * @param lastName
     * @return author
     */
    @PutMapping(path = AUTHOR + "/{authorID}")
    public @ResponseBody Author updateAuthor(@PathVariable Integer authorID, @RequestParam String firstName, @RequestParam String lastName) {
        Author author = authorRepo.findById(authorID).orElse(null);
        if (author != null) {
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authorRepo.save(author);
        }
        return author;
    }

    /**
     * Delete author
     * DeleteMapping for /authors/{authorID} by Spring
     * @param authorID
     * @return message
     */
    @DeleteMapping(path = AUTHOR + "/{authorID}")
    public @ResponseBody String deleteAuthor(@PathVariable Integer authorID) {
        Author author = authorRepo.findById(authorID).orElse(null);
        if (author != null) {
            authorRepo.delete(author);
            return "Author deleted";
        }
        return "Author not found";
    }

}
