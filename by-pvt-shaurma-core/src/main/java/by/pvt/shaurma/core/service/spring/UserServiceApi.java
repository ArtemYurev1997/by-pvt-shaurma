package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.UserApi;
import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.api.dto.UserResponse;
import by.pvt.shaurma.core.repository.spring.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceApi implements UserApi, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserResponse register(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse authorise(String login, String password) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        by.pvt.shaurma.core.entity.User user = userRepository.loadUserByUserName(login);
        UserDetails userDetails = User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        return userDetails;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }

    @Override
    public UserResponse findClientById(Long id) {
        return null;
    }

    @Override
    public List<UserResponse> update(UserRequest userRequest) {
        return null;
    }

}
