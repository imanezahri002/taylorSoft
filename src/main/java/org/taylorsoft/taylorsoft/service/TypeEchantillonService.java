package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.TypeEchantillonRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeEchantillonResponse;

import java.util.List;

public interface TypeEchantillonService {

    TypeEchantillonResponse create(TypeEchantillonRequest request);

    List<TypeEchantillonResponse> getAll();

    TypeEchantillonResponse getById(Long id);

    TypeEchantillonResponse update(Long id, TypeEchantillonRequest request);

    void delete(Long id);
}
