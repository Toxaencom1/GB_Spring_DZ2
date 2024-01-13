package com.taxah.springdz2.controllers;


import com.taxah.springdz2.model.User;
import com.taxah.springdz2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Handles HTTP GET requests for deleting a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @return A redirection to the "/users" endpoint after deleting the user.
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Handles HTTP GET requests for retrieving information about a specific user by their ID.
     *
     * @param id    The ID of the user to be retrieved.
     * @param model The Model to which the retrieved user information is added.
     * @return The view name "user-update" to display the user information for updating.
     */
    @GetMapping("/user-update/{id}")
    public String getOne(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Handles HTTP POST requests for updating user information.
     *
     * @param user The User object containing updated information.
     * @return A redirection to the "/users" endpoint after updating the user.
     */
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);
        return "redirect:/users";
    }
}
