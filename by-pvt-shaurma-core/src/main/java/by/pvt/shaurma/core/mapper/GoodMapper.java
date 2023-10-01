package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.GoodRequest;
import by.pvt.shaurma.api.dto.GoodResponse;
import by.pvt.shaurma.core.entity.Good;

public class GoodMapper {
    public GoodResponse mapToGoodDto(Good good){
        GoodResponse dto = new GoodResponse();
        dto.setId(good.getId());
        dto.setName(good.getName());
        dto.setDescription(good.getDescription());
        dto.setCode(good.getCode());
        dto.setPrice(good.getPrice());
        return dto;
    }
    //из dto в entity
    public Good mapToGoodEntity(GoodRequest dto){
        Good entity = new Good();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCode(dto.getCode());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
