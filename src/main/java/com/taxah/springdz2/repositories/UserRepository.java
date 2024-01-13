package com.taxah.springdz2.repositories;


import com.taxah.springdz2.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Deletes a user by their ID from the database.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    /**
     * Retrieves information about a user by their ID from the database.
     *
     * @param id The ID of the user to be retrieved.
     * @return A User object containing information about the retrieved user.
     */
    public User getOne(int id) {
        String sql = "SELECT id, firstName, lastName FROM userTable WHERE id=?";
        return jdbc.queryForObject(sql, (r, i) ->
                        new User(
                                r.getInt("id"),
                                r.getString("firstName"),
                                r.getString("lastName")
                        ),
                id
        );
    }

    /**
     * Updates user information in the database.
     *
     * @param user The User object containing updated information.
     * @return The updated User object.
     */
    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}
