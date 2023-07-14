package com.example.springadvancequeriesbookdb.service;



import com.example.springadvancequeriesbookdb.entity.AgeRestriction;
import com.example.springadvancequeriesbookdb.entity.Book;
import com.example.springadvancequeriesbookdb.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllByPriceLowerThanUpperThan(BigDecimal lowerPrice, BigDecimal upperPrice);

    List<String >findBooksWithGoldenTypeAndCopies(EditionType editionType, int copies);

    List<String> printTitlesNotReleasedInInputYear(int year);

    List<String> printTitlesEditionTypeAndPrice(String date);

     List<String> printTitlesWhichContainsGivenString(String contain);

    List<String> printTitlesOfBooksWithAuthorLastNamwStartWith(String start);

    int printCountOfBooksWithGivenCount(int size);

    List<String> printInformationForBookWithGivenTitle(String title);
}
