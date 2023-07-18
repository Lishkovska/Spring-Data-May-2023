package com.example.springdtoexercise.repository;

import com.example.springdtoexercise.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByTitle(String title);
}
