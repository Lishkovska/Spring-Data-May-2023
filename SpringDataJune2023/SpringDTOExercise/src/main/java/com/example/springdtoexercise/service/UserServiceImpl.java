package com.example.springdtoexercise.service;

import com.example.springdtoexercise.dto.UserLoginDto;
import com.example.springdtoexercise.dto.UserRegisterDto;
import com.example.springdtoexercise.entity.User;
import com.example.springdtoexercise.repository.UserRepository;
import com.example.springdtoexercise.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;
   private User loggedInUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper,
                           ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password!");
            return;
        }
        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violation(userRegisterDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        User user = mapper.map(userRegisterDto, User.class);
        userRepository.save(user);
        System.out.println(userRegisterDto.getFullName() + " was registered");

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.violation(userLoginDto);

        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

            Optional<User> user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(),
                    userLoginDto.getPassword());
            if (user.isEmpty()) {
                System.out.println("Incorrect username / password");
                return;
            }
            loggedInUser = user.get();
            System.out.printf("Successfully logged in %s%n", user.get().getFullName());
        }

    @Override
    public void logoutUser() {
            if (loggedInUser == null) {
                System.out.println("Cannot log out. No user was logged in.");
                return;
            }
            System.out.printf("User %s successfully logged out%n", loggedInUser.getFullName());
            loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
    }