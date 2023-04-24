package com.kisonich.learntest.magaz.controllers;

import com.kisonich.learntest.magaz.model.User;
import com.kisonich.learntest.magaz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private  UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

//    @GetMapping("/search")
//    public List<User> searchUsers(@RequestParam(name = "email", required = false) String email,
//                                  @RequestParam(name = "name", required = false) String name) {
//        if (email != null && name != null) {
//            return userService.searchByEmailAndName(email, name);
//        } else if (email != null) {
//            return userService.searchByEmail(email);
//        } else if (name != null) {
//            return userService.searchByName(name);
//        } else {
//            return userService.getAllUsers();
//        }
//    }
}

//@RestController
//@RequestMapping("/api/v1/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable long id) {
//        return userService.getUserById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable long id, @RequestBody User updatedUser) throws UserNotFoundException {
//        return userService.updateUser(id, updatedUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable long id) {
//        userService.deleteUser(id);
//    }
//}