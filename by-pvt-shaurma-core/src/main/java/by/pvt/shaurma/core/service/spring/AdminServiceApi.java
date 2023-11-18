package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.exception.AccountException;
import by.pvt.shaurma.core.mapper.spring.AdminMappers;
import by.pvt.shaurma.core.repository.spring.AdminRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Primary
public class AdminServiceApi implements AdminApi, UserDetailsService {
    private final AdminRepository adminRepository;
    private final AdminMappers adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest httpServletRequest;

    @Transactional
    @Secured({"ADMIN"})
    @Override
    public AdminResponse register(AdminRequest adminRequest) {
        if(adminRequest.getLogin() != null) {
            throw new AccountException("Логин занят!");
        }
        Admin admin = adminMapper.toEntity(adminRequest);
        String password = passwordEncoder.encode(adminRequest.getPassword());
        admin.setPassword(password);
        adminRepository.save(admin);
        return adminMapper.toResponse(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin adminResponse = adminRepository.loadUserByUserName(login);
        UserDetails admin = User.builder()
                .username(adminResponse.getLogin())
                .password(adminResponse.getPassword())
                .roles(adminResponse.getRole())
                .build();
        return admin;
    }

//    @Transactional
//    @Override
//    public AdminResponse authorise(AdminRequest adminRequest) throws ServletException {
//        httpServletRequest.login(adminRequest.getLogin(), adminRequest.getPassword());
//        return new AdminResponse();
//        Admin admin = adminRepository.authorise(adminRequest.getLogin(), adminRequest.getPassword());
//        if(admin != null){
//            return adminMapper.toResponse(admin);
//        } else {
//            throw new AccountException("Пользователь не найден!");
//        }
//
//    }

    @Transactional
    @Override
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Secured({"ADMIN"})
    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminRepository.findAll().stream().map(adminMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AdminResponse findAdminById(Long id) {
        Optional<AdminResponse> admin = Optional.of(adminMapper.toResponse(adminRepository.findById(id).orElseThrow(() -> new AccountException("404!"))));
        return admin.get();
    }

    @Override
//    @Secured({"ADMIN"})
    public List<AdminResponse> update(AdminRequest adminRequest) {
        Optional<Admin> admin = adminRepository.findById(adminRequest.getId());
        String password = passwordEncoder.encode(adminRequest.getPassword());
        if (admin.isPresent()) {
            admin.get().setPassword(password);
            adminRepository.save(admin.get());
        }
        return getAllAdmins();
    }
}
