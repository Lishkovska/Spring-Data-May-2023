package com.example.springdtoexercise.service;

import com.example.springdtoexercise.dto.AllGamesDto;
import com.example.springdtoexercise.dto.DetailGameDto;
import com.example.springdtoexercise.dto.GameAddDto;
import com.example.springdtoexercise.dto.UserLoginDto;
import com.example.springdtoexercise.entity.Game;
import com.example.springdtoexercise.repository.GameRepository;
import com.example.springdtoexercise.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
    private final UserServiceImpl userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper mapper,
                           ValidationUtil validationUtil, UserServiceImpl userService) {
        this.gameRepository = gameRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }
    @Override
    public void addGame(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.violation(gameAddDto);

        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = mapper.map(gameAddDto, Game.class);
        gameRepository.save(game);

    }

    @Override
    public List<AllGamesDto> allGames() {
        return gameRepository.findAll().stream()
                .map(game -> mapper.map(game, AllGamesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetailGameDto detailGame(String title) {
        Game game = gameRepository.findGameByTitle(title);
        DetailGameDto detailGameDto = null;
        if (game != null) {
            detailGameDto = mapper.map(game, DetailGameDto.class);
        }
        return detailGameDto;
    }

    @Override
    public void deleteGame(long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            System.out.println("There is no game with this ID.");
            return;
        }
        gameRepository.delete(game.get());

    }

    @Override
    public void editGame(long id, BigDecimal price, double size) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            System.out.println("There is no game with this ID.");
            return;
        }
        if (size < 0 && price.compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("Enter positive price and size");
            return;

        }
        game.get().setPrice(price);
        game.get().setSize(size);
        gameRepository.save(game.get());
        System.out.println("Edited " + game.get().getTitle());

    }
}
