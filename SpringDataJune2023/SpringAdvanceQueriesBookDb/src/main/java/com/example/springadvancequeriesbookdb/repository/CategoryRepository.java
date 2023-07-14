package com.example.springadvancequeriesbookdb.repository;


import com.example.springadvancequeriesbookdb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
