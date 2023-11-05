package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.exception.AccountException;
import by.pvt.shaurma.core.mapper.spring.AdminMappers;
import by.pvt.shaurma.core.repository.spring.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceApi implements AdminApi {
    private final AdminRepository adminRepository;
    private final AdminMappers adminMapper;

    @Transactional
    @Override
    public AdminResponse register(AdminRequest adminRequest) {
        if(adminRequest.getName().equals("") | adminRequest.getSurname().equals("") | adminRequest.getLogin().equals("") | adminRequest.getPassword().equals("")) {
            throw new AccountException("Введите обязательные поля!");
        }
        else if(adminRequest.getLogin() != null) {
            throw new AccountException("Логин занят!");
        }
        Admin admin = adminMapper.toEntity(adminRequest);
        adminRepository.save(admin);
        return adminMapper.toResponse(admin);
    }

    @Transactional
    @Override
    public AdminResponse authorise(String login, String password) {
        Admin admin = adminRepository.authorise(login, password);
        if(admin != null){
            return adminMapper.toResponse(admin);
        } else {
            throw new AccountException("Пользователь не найден!");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

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
    public List<AdminResponse> update(AdminRequest adminRequest) {
        AdminResponse admin =adminMapper.toResponse(adminRepository.save(adminMapper.toEntity(adminRequest)));
        return getAllAdmins();
    }
}
