package com.example.springadvancequeriesbookdb.repository;

import com.example.springadvancequeriesbookdb.entity.AgeRestriction;
import com.example.springadvancequeriesbookdb.entity.Book;
import com.example.springadvancequeriesbookdb.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle
            (String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByPriceIsLessThanAndPriceGreaterThan(BigDecimal lowerPrice,
                                                                     BigDecimal upperPrice);

    List<Book> findAllByEditionTypeAndCopies(EditionType editionType, int copies);
    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);
    List<Book> findAllByTitleContaining(String contain);
    List<Book> findAllByAuthor_LastNameStartsWith(String start);
    List<Book> findAllByTitle(String title);

    @Query("SELECT count(b) FROM Book b " +
            "where length(b.title) > :param ")
    int countBookByTitleGreaterThan(@Param(value = "param") int size);

}
