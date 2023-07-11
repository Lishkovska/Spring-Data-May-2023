package com.example.springintrobookdatabase.service;

import com.example.springintrobookdatabase.model.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;


    List<Book> getAllBooksAfter(int year);
    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

}
