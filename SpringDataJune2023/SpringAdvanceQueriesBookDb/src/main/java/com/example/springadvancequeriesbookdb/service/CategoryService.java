package com.example.springadvancequeriesbookdb.service;



import com.example.springadvancequeriesbookdb.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
