package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.taylorsoft.taylorsoft.dtos.response.StockResponse;
import org.taylorsoft.taylorsoft.entity.Stock;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TissuColorMapper.class, StockMouvementMapper.class})
public interface StockMapper {

    @Mapping(source = "coutourier.id", target = "couturierId")
    @Mapping(source = "coutourier.nom", target = "coutourierNom")
    @Mapping(source = "coutourier.prenom", target = "coutourierPrenom")
    StockResponse toResponse(Stock stock);

    List<StockResponse> toResponseList(List<Stock> stocks);
}

