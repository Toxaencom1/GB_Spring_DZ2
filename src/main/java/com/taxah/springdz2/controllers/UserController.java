package com.taxah.springdz2.controllers;


import com.taxah.springdz2.model.User;
import com.taxah.springdz2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/**
 * UserController Class
 * <p>
 * This class serves as a Spring MVC controller handling HTTP requests related to user operations.
 * It includes methods for finding all users, displaying a user list, rendering a user creation form,
 * creating a new user, and deleting a user by ID.
 * <p>
 * Dependencies:
 * - UserService: The service for handling user-related business logic.
 * <p>
 * Annotations:
 * - @Controller: Indicates that this class is a Spring MVC controller.
 * - @AllArgsConstructor: Lombok annotation to generate a constructor with all required fields.
 * - @Log: Lombok annotation to automatically generate a logger field.
 * <p>
 * Methods:
 * - findAll(Model model): Handles GET requests to "/users" and displays a user list.
 * - createUserForm(User user): Handles GET requests to "/user-create" and renders the user creation form.
 * - createUser(User user): Handles POST requests to "/user-create" and creates a new user.
 * - deleteUser(int id): Handles GET requests to "/user-delete/{id}" and deletes a user by ID.
 */
@Controller
@AllArgsConstructor
@Log
public class UserController {
    private final UserService userService;

    /**
     * Find All Users
     * <p>
     * Handles GET requests to "/users" and displays a user list.
     *
     * @param model Model: The Spring MVC model for passing data to the view.
     * @return String: The view name ("user-list") to be rendered.
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        log.info("Логирование отображения всех пользователей");
        return "user-list";
    }

    /**
     * Create User Form
     * <p>
     * Handles GET requests to "/user-create" and renders the user creation form.
     *
     * @param user User: The model attribute representing user data for the form.
     * @return String: The view name ("user-create") to be rendered.
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("Логирование отображение формы для создания пользователя");
        return "user-create";
    }

    /**
     * Create User
     * <p>
     * Handles POST requests to "/user-create" and creates a new user.
     *
     * @param user User: The model attribute representing user data from the form submission.
     * @return String: A redirection to the "/users" endpoint after creating the user.
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        log.info("Логирование сохранения пользователя");
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
        log.info("Логирование удаления пользователя");
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
        log.info("Логирование получения пользователя");
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
        log.info("Логирование обновление данных пользователя");
        return "redirect:/users";
    }
}
