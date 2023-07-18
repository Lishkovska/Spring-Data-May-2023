package com.example.springdtoexercise.service;

import com.example.springdtoexercise.dto.AllGamesDto;
import com.example.springdtoexercise.dto.DetailGameDto;
import com.example.springdtoexercise.dto.GameAddDto;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    List<AllGamesDto> allGames();

    DetailGameDto detailGame(String title);

    void deleteGame(long id);

    void editGame(long id, BigDecimal price, double size);
}
