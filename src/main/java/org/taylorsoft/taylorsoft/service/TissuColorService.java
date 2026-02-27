package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.TissuColorRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuColorResponse;

import java.util.List;

public interface TissuColorService {

    TissuColorResponse create(TissuColorRequest request);

    List<TissuColorResponse> getAll();

    TissuColorResponse getById(Long id);

    TissuColorResponse update(Long id, TissuColorRequest request);

    void delete(Long id);

    List<TissuColorResponse> getByTissuId(Long tissuId);

    List<TissuColorResponse> getByCouleurId(Long couleurId);

    List<TissuColorResponse> getByFournisseurId(Long fournisseurId);

    List<TissuColorResponse> getActiveOnly();
}


