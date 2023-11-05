package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admins")
public class AdminController {
    private final AdminApi adminApi;

    public AdminController(@Qualifier("adminServiceApi") AdminApi adminApi) {
        this.adminApi = adminApi;
    }

    @GetMapping("/getAll")
    public List<AdminResponse> findAll() {
        return adminApi.getAllAdmins();
    }

    @PostMapping("/add")
    public AdminResponse add(@RequestBody AdminRequest adminRequest) {
        return adminApi.register(adminRequest);
    }

    @PostMapping("/delete")
    public void delete(Long id) {
        adminApi.delete(id);
    }


    @PostMapping("/update")
    public List<AdminResponse> update(AdminRequest adminRequest) {
        return adminApi.update(adminRequest);
    }

    @GetMapping("/{id}")
    public AdminResponse findById(@PathVariable("id") Long id) {
        return adminApi.findAdminById(id);
    }

    @PostMapping("/authorise")
    public AdminResponse authorise(@RequestBody AdminRequest adminRequest) {
        String login = adminRequest.getLogin();
        String password = adminRequest.getPassword();
        return adminApi.authorise(login, password);
    }
}
