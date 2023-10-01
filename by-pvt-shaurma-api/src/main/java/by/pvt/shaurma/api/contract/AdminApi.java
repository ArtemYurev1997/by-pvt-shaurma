package by.pvt.shaurma.api.contract;



import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;

import java.util.List;

public interface AdminApi {
    AdminResponse register(AdminRequest adminRequest);

    AdminResponse authorise(String login, String password);

    void delete(Long id);

    List<AdminResponse> getAllAdmins();

    AdminResponse findAdminById(Long id);

    List<AdminResponse> update(AdminRequest adminRequest);
}
