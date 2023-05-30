package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.exception.UserNotCreatedException;
import com.multiplex.ticketBooking.exception.UserNotFoundException;
import com.multiplex.ticketBooking.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user) throws UserNotCreatedException {
        logger.info("Creating user");
        User checkUser =  userService.createUser(user);
        if(checkUser == null){
            logger.error("User not created");
            throw new UserNotCreatedException("Entered User Couldn't be Created");
        }
        return checkUser;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUser() {
        logger.info("Getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        logger.info("Getting user by id");
        User checkUser = userService.getUserById(id);
        if(checkUser == null){
            logger.error("User cannot be found");
            throw new UserNotFoundException("Entered User doesn't exist");
        }
        logger.info("Getting user by id");
        return checkUser;
    }

    @PutMapping("/updateUserById/{id}")
    public User updateUserById(@Valid @RequestBody User user, @PathVariable Long id) {
        logger.info("Updating user");
        return userService.updateUserById(user, id);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        logger.info("Deleting user");
        userService.deleteUserById(id);
    }
}
