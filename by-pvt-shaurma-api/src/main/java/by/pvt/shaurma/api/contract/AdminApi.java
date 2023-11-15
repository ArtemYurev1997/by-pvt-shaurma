package by.pvt.shaurma.api.contract;



import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import jakarta.servlet.ServletException;


import java.util.List;

public interface AdminApi {
    AdminResponse register(AdminRequest adminRequest);

    AdminResponse authorise(AdminRequest adminRequest) throws ServletException;

    void delete(Long id);

    List<AdminResponse> getAllAdmins();

    AdminResponse findAdminById(Long id);

    List<AdminResponse> update(AdminRequest adminRequest);
}
