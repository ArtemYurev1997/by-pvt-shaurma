package by.pvt.shaurma.core.mapper.spring;

import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.RetentionPolicy;

@Mapper(componentModel = "spring")
public interface ClientMappers {

    ClientResponse toResponse(Client client);

    Client toEntity(ClientRequest clientRequest);
}
