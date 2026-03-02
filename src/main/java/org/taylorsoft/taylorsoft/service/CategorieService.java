package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.CategorieRequest;
import org.taylorsoft.taylorsoft.dtos.response.CategorieResponse;

import java.util.List;

public interface CategorieService {

    CategorieResponse create(CategorieRequest request);

    List<CategorieResponse> getAll();

    CategorieResponse getById(Long id);

    CategorieResponse update(Long id, CategorieRequest request);

    void delete(Long id);
}

