package com.finance.babilonia.mapper;

import com.finance.babilonia.controller.request.SpentRequest;
import com.finance.babilonia.model.Spent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SpentMapper extends  GenericMapper<Spent, SpentRequest, UUID>{

    @Mapping(target = "uuid" ,ignore = true)
    @Mapping( target = "userId", ignore = true)
    void updateEntityToDTO(SpentRequest request, @MappingTarget Spent spent);

    @Mapping(source = "uuid" , target ="uuid" )
    SpentRequest  entityToDTO(Spent spent);
}

