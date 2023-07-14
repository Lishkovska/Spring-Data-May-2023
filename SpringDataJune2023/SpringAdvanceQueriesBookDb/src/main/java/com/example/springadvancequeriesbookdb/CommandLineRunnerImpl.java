package com.example.springadvancequeriesbookdb;


import com.example.springadvancequeriesbookdb.entity.AgeRestriction;
import com.example.springadvancequeriesbookdb.entity.Book;
import com.example.springadvancequeriesbookdb.entity.EditionType;
import com.example.springadvancequeriesbookdb.service.AuthorService;
import com.example.springadvancequeriesbookdb.service.BookService;
import com.example.springadvancequeriesbookdb.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService,
                                 AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        // seedData();
        System.out.println("Please enter task number :");
        int task = Integer.parseInt(bufferedReader.readLine());
        switch (task){
            case 1 -> bookTitlesByAgeRestriction();
            case 2 ->  printGoldenBooks();
            case 3 ->   booksWithPriceLowerThanHigherThan();
            case 4 -> printBooksNotReleasedInInputYear();
            case 5 ->  printBookReleasedBeforeGivenDate();
            case 6 -> printAuthorsWithFirstNameEndsWith();
            case 7 -> printTitlesOfBooksThatContainGivenString();
            case 8 -> printTitlesOfBooksWithAuthorsLastNameStartsWith();
            case 9 -> printTheNumberOfBooksWhoseTitleIsLongerThan();
            case 10 -> printAuthorsWithTotalCopies();
            case 11 -> printReducedBook();
        }


        //    printAllBooksAfterYear(2000);
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        // printAllAuthorsAndNumberOfTheirBooks();
        //pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void printReducedBook() throws IOException {
        System.out.println("Please enter a title: ");
        String title = bufferedReader.readLine();

        this.bookService.printInformationForBookWithGivenTitle(title)
                .forEach(System.out::println);
    }

    private void printTheNumberOfBooksWhoseTitleIsLongerThan() throws IOException {
        System.out.println("Please enter size:");
        int size = Integer.parseInt(bufferedReader.readLine());
        System.out.println(this.bookService.printCountOfBooksWithGivenCount(size));
    }

    private void printTitlesOfBooksWithAuthorsLastNameStartsWith() throws IOException {
        System.out.println("Please enter starts string: ");
        String start = bufferedReader.readLine();
        this.bookService.printTitlesOfBooksWithAuthorLastNamwStartWith(start)
                .forEach(System.out::println);
    }

    private void printTitlesOfBooksThatContainGivenString() throws IOException {
        System.out.println("Please enter string :");
        String contain = bufferedReader.readLine();
        this.bookService.printTitlesWhichContainsGivenString(contain)
                .forEach(System.out::println);
    }

    private void printAuthorsWithFirstNameEndsWith() throws IOException {
        System.out.println("Please enter end string: ");
        String end = bufferedReader.readLine();
        this.authorService.printAuthorsNameThatEndsWithGivenString(end)
                .forEach(System.out::println);
    }

    private void printBookReleasedBeforeGivenDate() throws IOException {
        System.out.println("Please enter date in date format dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        this.bookService.printTitlesEditionTypeAndPrice(String.valueOf(localDate))
                .forEach(System.out::println);

    }


    private void printBooksNotReleasedInInputYear() throws IOException {
        System.out.println("Please enter year:");
        int year = Integer.parseInt(bufferedReader.readLine());

        this.bookService.printTitlesNotReleasedInInputYear(year)
                .forEach(System.out::println);
    }

    private void printGoldenBooks() {
        int copies = 5000;
        this.bookService.findBooksWithGoldenTypeAndCopies(EditionType.GOLD, copies)
                .forEach(System.out::println);
    }

    private void bookTitlesByAgeRestriction() throws IOException {
        System.out.println("Please enter age restriction: ");
        String age = bufferedReader.readLine().toUpperCase();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(age);
        this.bookService.findAllByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }


    private void printAuthorsWithTotalCopies() {
        this.authorService.printAuthorsWithTotalCopies()
                .forEach(System.out::println);
    }




    private void booksWithPriceLowerThanHigherThan() {
        BigDecimal lowerPrice = new BigDecimal(5);
        BigDecimal upperPrice = new BigDecimal(40);
        this.bookService.findAllByPriceLowerThanUpperThan(lowerPrice, upperPrice)
                .forEach(System.out::println);
    }



    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        this.bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        for (Book book : bookService.findAllBooksAfterYear(year)) {
            String title = book.getTitle();
            System.out.println(title);
        }
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
