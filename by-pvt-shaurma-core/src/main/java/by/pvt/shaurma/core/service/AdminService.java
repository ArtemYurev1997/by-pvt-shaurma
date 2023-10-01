package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.mapper.AdminMapper;
import by.pvt.shaurma.core.repository.AdminDao;

import java.util.List;
import java.util.stream.Collectors;

public class AdminService implements AdminApi {
    private final AdminDao adminDao;
    private final AdminMapper adminMapper;

    public AdminService(AdminDao adminDao, AdminMapper adminMapper) {
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminResponse register(AdminRequest adminRequest) {
      Admin admin = adminMapper.mapToAdminEntity(adminRequest);
      adminDao.addUser(admin);
      return adminMapper.mapToAdminDto(admin);
    }

    @Override
    public AdminResponse authorise(String login, String password) {
        List<AdminResponse> admins = getAllAdmins();
        AdminResponse adminResponse = admins.stream().filter(admin -> admin.getLogin().equals(login)).findFirst().orElseThrow(() -> new RuntimeException("Клиент с логином" + login + " не найден"));
        if (!adminResponse.getPassword().equals(password)) {
            throw new RuntimeException("Не верно введён пароль!");
        }
        return adminResponse;
    }

    @Override
    public void delete(Long id) {
        adminDao.delete(id);
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminDao.getAllAdmins().stream().map(adminMapper::mapToAdminDto).collect(Collectors.toList());
    }

    @Override
    public AdminResponse findAdminById(Long id) {
        return adminMapper.mapToAdminDto(adminDao.getAdminById(id));
    }

    @Override
    public List<AdminResponse> update(AdminRequest adminRequest) {
        adminDao.update(adminMapper.mapToAdminEntity(adminRequest));
        return getAllAdmins();
    }
}
