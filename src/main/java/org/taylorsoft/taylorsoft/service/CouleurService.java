package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.CouleurRequest;
import org.taylorsoft.taylorsoft.dtos.response.CouleurResponse;

import java.util.List;

public interface CouleurService {

    CouleurResponse create(CouleurRequest request);

    List<CouleurResponse> getAll();

    CouleurResponse getById(Long id);

    CouleurResponse update(Long id, CouleurRequest request);

    void delete(Long id);
}

