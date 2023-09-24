package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.core.entity.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    List<User> getAllUsers();

    void delete(Long id);

    void update(User user);

    User getClientById(Long id);

}
