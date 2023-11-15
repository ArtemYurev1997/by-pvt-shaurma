package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
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
    public AdminResponse add(@RequestBody @Validated AdminRequest adminRequest) {
        return adminApi.register(adminRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        adminApi.delete(id);
    }


    @PostMapping("/update")
    public List<AdminResponse> update(@RequestBody AdminRequest adminRequest) {
        return adminApi.update(adminRequest);
    }

    @GetMapping("/{id}")
    public AdminResponse findById(@PathVariable("id") Long id) {
        return adminApi.findAdminById(id);
    }

    @PostMapping("/authorise")
    public AdminResponse authorise(@RequestBody AdminRequest adminRequest) throws ServletException {
        return adminApi.authorise(adminRequest);
    }
}
