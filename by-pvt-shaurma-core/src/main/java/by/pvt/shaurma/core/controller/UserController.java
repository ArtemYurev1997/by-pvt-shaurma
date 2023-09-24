package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.UserApi;
import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.api.dto.UserResponse;

import java.time.LocalDate;
import java.util.List;

public class UserController {
    private final UserApi userApi;

    public UserController(UserApi userApi) {
        this.userApi = userApi;
    }

    public UserResponse register(UserRequest userRequest) {
        return userApi.register(userRequest);
    }

    public UserResponse authorise(String login, String password) {
        return userApi.authorise(login, password);
    }

    public void delete(Long id) {
        userApi.delete(id);
    }

    public List<UserResponse> getAllUsers() {
        return userApi.getAllUsers();
    }

    public UserResponse findClientById(Long id) {
        return userApi.findClientById(id);
    }

    public List<UserResponse> update(UserRequest userRequest) {
        return userApi.update(userRequest);
    }

}
