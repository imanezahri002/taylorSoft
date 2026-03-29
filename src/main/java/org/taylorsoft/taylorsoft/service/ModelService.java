package org.taylorsoft.taylorsoft.service;

import org.springframework.web.multipart.MultipartFile;
import org.taylorsoft.taylorsoft.dtos.request.ModelRequest;
import org.taylorsoft.taylorsoft.dtos.response.ModelResponse;

import java.util.List;

public interface ModelService {
    ModelResponse create(ModelRequest request, List<MultipartFile> files);

    List<ModelResponse> getAll();

    ModelResponse getById(Long id);

//    ModelResponse update(Long id, ModelRequest request);

    void delete(Long id);

    List<ModelResponse> getByTissuId(Long tissuId);

    List<ModelResponse> getByCouturierId(Long couturierId);

    List<ModelResponse> getActiveOnly();
}

