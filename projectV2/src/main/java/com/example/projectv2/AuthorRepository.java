package com.example.projectv2;

import org.springframework.data.repository.CrudRepository;

/**
 * AuthorRepository interface
 * Used to access the database and perform operations on the authors table
 * Extends CrudRepository
 * Used to get, add, update and delete authors
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
