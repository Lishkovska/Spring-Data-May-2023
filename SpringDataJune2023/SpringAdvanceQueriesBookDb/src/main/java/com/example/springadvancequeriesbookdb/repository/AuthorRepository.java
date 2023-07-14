package com.example.springadvancequeriesbookdb.repository;


import com.example.springadvancequeriesbookdb.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

   List<Author> findAuthorsByFirstNameEndsWith(String end);

    @Query("Select a.firstName , a.lastName, sum (ab.copies) as bcopies from Author as a " +
            "join a.books ab " +
            "GROUP BY a.id " +
            "order by bcopies DESC")
    List<String> findAuthorsByBooksCopies();
}
