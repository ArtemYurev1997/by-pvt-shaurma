package by.pvt.shaurma.core.repository;



import by.pvt.shaurma.core.entity.Admin;

import java.util.List;

public interface AdminDao {
    Admin authorise(String login, String password);

    void addUser(Admin admin);

    List<Admin> getAllAdmins();

    void delete(Long id);

    void update(Admin admin);

    Admin getAdminById(Long id);
}
