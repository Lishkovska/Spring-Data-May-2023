package com.example.springintrobookdatabase.service;

import com.example.springintrobookdatabase.model.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
