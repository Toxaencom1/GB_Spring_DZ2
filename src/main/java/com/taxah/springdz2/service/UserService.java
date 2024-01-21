package com.taxah.springdz2.service;


import com.taxah.springdz2.model.User;
import com.taxah.springdz2.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID using the UserRepository.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Retrieves information about a user by their ID using the UserRepository.
     *
     * @param id The ID of the user to be retrieved.
     * @return A User object containing information about the retrieved user.
     */
    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    /**
     * Updates user information using the UserRepository.
     *
     * @param user The User object containing updated information.
     * @return The updated User object.
     */
    public User update(User user) {
        return userRepository.update(user);
    }
}
