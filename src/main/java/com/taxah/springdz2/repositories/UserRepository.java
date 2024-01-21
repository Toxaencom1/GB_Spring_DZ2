package com.taxah.springdz2.repositories;


import com.taxah.springdz2.config.MagicProperties;
import com.taxah.springdz2.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbc;

    private final MagicProperties magicProperties;

    public List<User> findAll() {
        String sql = magicProperties.getFindAll();

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
        String sql = magicProperties.getSave();
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Deletes a user by their ID from the database.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteById(int id) {
        String sql = magicProperties.getDeleteById();
        jdbc.update(sql, id);
    }

    /**
     * Retrieves information about a user by their ID from the database.
     *
     * @param id The ID of the user to be retrieved.
     * @return A User object containing information about the retrieved user.
     */
    public User getOne(int id) {
        String sql = magicProperties.getGetOne();
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
        String sql = magicProperties.getUpdate();
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}
