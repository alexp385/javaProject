package com.example.projectv2;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBookByIsbn(String isbn);



}
