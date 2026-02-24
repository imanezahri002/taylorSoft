package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.MesureRequest;
import org.taylorsoft.taylorsoft.dtos.response.MesureResponse;

import java.util.List;

public interface MesureService {

    MesureResponse create(MesureRequest request);

    List<MesureResponse> getAll();

    MesureResponse getById(Long id);

    MesureResponse getByClientId(Long clientId);

    MesureResponse update(Long id, MesureRequest request);

    void delete(Long id);
}

