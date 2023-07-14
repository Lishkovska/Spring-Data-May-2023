package com.example.springadvancequeriesbookdb.service;



import com.example.springadvancequeriesbookdb.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<String> printAuthorsWithTotalCopies();

    List<String> printAuthorsNameThatEndsWithGivenString(String end);
}
