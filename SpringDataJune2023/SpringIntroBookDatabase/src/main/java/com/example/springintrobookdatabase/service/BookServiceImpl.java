package com.example.springintrobookdatabase.service;

import com.example.springintrobookdatabase.model.*;
import com.example.springintrobookdatabase.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private static final String BOOKS_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }
    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBook(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> getAllBooksAfter(int year) {
        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year, 12, 31))
        ;
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        List<String> list = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        for (Book book : bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))) {
            String format = String.format("%s %s", book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName());
            if (uniqueValues.add(format)) {
                list.add(format);
            }
        }
        return list;
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBook(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType,
                releaseDate, copies,
                price, ageRestriction,
                title, author, categories);

    }
}
