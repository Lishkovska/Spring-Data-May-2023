package com.example.springintrobookdatabase;

import com.example.springintrobookdatabase.model.Book;
import com.example.springintrobookdatabase.service.AuthorService;
import com.example.springintrobookdatabase.service.BookService;
import com.example.springintrobookdatabase.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService,
                                 AuthorService authorService,
                                 BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
         getAllBooksAfter(2000);
         getAllAuthorsWithReleaseDateBefore(1990);
        getAllAuthorsAndNumberOfTheirBooks();
        getAllBooksByAuthorNameOrderByReleaseDate("George", "Powell");
    }

    private void getAllBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        for (String author : bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)) {
            System.out.println(author);
        }
    }

    private void getAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void getAllAuthorsWithReleaseDateBefore(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void getAllBooksAfter(int year) {

        for (Book book : bookService.getAllBooksAfter(year)) {
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
