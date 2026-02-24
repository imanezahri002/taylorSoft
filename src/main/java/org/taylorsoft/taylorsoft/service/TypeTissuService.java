package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.TypeTissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeTissuResponse;

import java.util.List;

public interface TypeTissuService {

    TypeTissuResponse create(TypeTissuRequest request);

    List<TypeTissuResponse> getAll();

    TypeTissuResponse getById(Long id);

    TypeTissuResponse update(Long id, TypeTissuRequest request);

    void delete(Long id);
}
