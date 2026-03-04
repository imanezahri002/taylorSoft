package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.taylorsoft.taylorsoft.dtos.response.StockMouvementResponse;
import org.taylorsoft.taylorsoft.entity.StockMouvement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMouvementMapper {

    StockMouvementResponse toResponse(StockMouvement stockMouvement);

    List<StockMouvementResponse> toResponseList(List<StockMouvement> stockMouvements);
}

