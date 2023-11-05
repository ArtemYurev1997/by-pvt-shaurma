package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import by.pvt.shaurma.core.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMappers {
    AdminResponse toResponse(Admin admin);
    Admin toEntity(AdminRequest adminRequest);
}
