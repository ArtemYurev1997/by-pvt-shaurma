package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import by.pvt.shaurma.core.entity.Admin;

public class AdminMapper {
    public AdminResponse mapToAdminDto(Admin admin) {
        AdminResponse dto = new AdminResponse();
        dto.setDateEnter(admin.getDateEnter());
        dto.setDateExit(admin.getDateExit());
        dto.setPost(admin.getPost());
        dto.setSalary(admin.getSalary());
        return dto;
    }

    public Admin mapToAdminEntity(AdminRequest adminRequest) {
        Admin dto = new Admin();
        dto.setDateEnter(adminRequest.getDateEnter());
        dto.setDateExit(adminRequest.getDateExit());
        dto.setPost(adminRequest.getPost());
        dto.setSalary(adminRequest.getSalary());
        return dto;
    }
}
