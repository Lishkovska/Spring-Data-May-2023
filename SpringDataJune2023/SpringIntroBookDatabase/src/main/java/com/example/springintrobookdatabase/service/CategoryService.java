package com.example.springintrobookdatabase.service;

import com.example.springintrobookdatabase.model.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
