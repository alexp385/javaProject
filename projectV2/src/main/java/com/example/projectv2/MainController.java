package com.example.projectv2;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class MainController {

    public static final  String BOOK = "/books";

    public static final  String AUTHOR = "/authors";

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @GetMapping(BOOK)
    public @ResponseBody Iterable<Book> getBooks() {
        return bookRepo.findAll();
    }

    @GetMapping(path=BOOK + "/{isbn}")
    public @ResponseBody Book getBook(@PathVariable String isbn) {
        return bookRepo.findBookByIsbn(isbn);
    }

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

    // delete book
    @DeleteMapping(path = BOOK + "/{isbn}")
    public @ResponseBody String deleteBook(@PathVariable String isbn) {
        Book book = bookRepo.findBookByIsbn(isbn);
        if (book != null) {
            bookRepo.delete(book);
            return "Book deleted";
        }
        return "Book not found";
    }

    //=============================================================================

    @GetMapping(path = AUTHOR)
    public @ResponseBody Iterable<Author> getAuthors() {
        return authorRepo.findAll();
    }

    @GetMapping(path=AUTHOR + "/{authorID}")
    public @ResponseBody Author getAuthor(@PathVariable Integer authorID) {
        return authorRepo.findById(authorID).orElse(null);
    }

    @PostMapping(path = AUTHOR)
    public @ResponseBody Author addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepo.save(author);
        return author;
    }

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
