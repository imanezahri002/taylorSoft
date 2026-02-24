package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.TissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuResponse;

import java.util.List;

public interface TissuService {

    TissuResponse create(TissuRequest request);

    List<TissuResponse> getAll();

    TissuResponse getById(Long id);

    TissuResponse update(Long id, TissuRequest request);

    void delete(Long id);
}

