package com.multiplex.ticketBooking.controller;

import com.multiplex.ticketBooking.entity.User;
import com.multiplex.ticketBooking.exception.UserNotCreatedException;
import com.multiplex.ticketBooking.exception.UserNotFoundException;
import com.multiplex.ticketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) throws UserNotCreatedException {
        User checkUser =  userService.createUser(user);
        if(checkUser == null){
            throw new UserNotCreatedException("Entered User Couldn't be Created");
        }
        return checkUser;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        User checkUser = userService.getUserById(id);
        if(checkUser == null){
            throw new UserNotFoundException("Entered User doesn't exist");
        }
        return checkUser;
    }

    @PutMapping("/updateUserById/{id}")
    public User updateUserById(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUserById(user, id);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
