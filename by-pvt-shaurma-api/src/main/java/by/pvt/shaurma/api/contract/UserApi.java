package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.api.dto.UserResponse;

import java.util.List;

public interface UserApi {
    UserResponse register(UserRequest userRequest);

    UserResponse authorise(String login, String password);

    void delete(Long id);

    List<UserResponse> getAllUsers();

    UserResponse findClientById(Long id);

    List<UserResponse> update(UserRequest userRequest);
}
