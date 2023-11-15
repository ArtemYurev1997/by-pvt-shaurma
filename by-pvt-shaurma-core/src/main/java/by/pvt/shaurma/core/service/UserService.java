package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.UserApi;
import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.api.dto.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserService  {
//    private final UserDao userDao;
//    private final UserMapper userMapper;
//
//    public UserService(UserDao userDao, UserMapper userMapper) {
//        this.userDao = userDao;
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public UserResponse register(UserRequest userRequest) {
//        UserResponse userResponse = userMapper.mapToUserDto(userMapper.mapToUserEntity(userRequest));
//        userDao.addUser(userMapper.mapToUserEntity(userRequest));
//        return userResponse;
//    }
//
//    @Override
//    public UserResponse authorise(String login, String password) {
//        List<UserResponse> users = getAllUsers();
//        UserResponse user = users.stream().filter(client1 -> client1.getLogin().equals(login)).findFirst().orElseThrow(() -> new RuntimeException("Клиент с логином" + login + " не найден"));
//        if (!user.getPassword().equals(password)) {
//            throw new RuntimeException("Не верно введён пароль!");
//        }
//        return user;
//    }
//
//    @Override
//    public void delete(Long id) {
//        userDao.delete(id);
//    }
//
//    @Override
//    public List<UserResponse> getAllUsers() {
//        return userDao.getAllUsers().stream().map(userMapper::mapToUserDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public UserResponse findClientById(Long id) {
//        return userMapper.mapToUserDto(userDao.getClientById(id));
//    }
//
//    @Override
//    public List<UserResponse> update(UserRequest userRequest) {
//        userDao.update(userMapper.mapToUserEntity(userRequest));
//        return getAllUsers();
//    }
}
