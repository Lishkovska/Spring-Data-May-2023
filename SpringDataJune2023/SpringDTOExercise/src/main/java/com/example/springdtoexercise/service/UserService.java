package com.example.springdtoexercise.service;

import com.example.springdtoexercise.dto.OwnedGamesDto;
import com.example.springdtoexercise.dto.UserLoginDto;
import com.example.springdtoexercise.dto.UserRegisterDto;
import com.example.springdtoexercise.entity.User;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logoutUser();

    List<OwnedGamesDto> printOwnedGames(User loggedInUser);
}
