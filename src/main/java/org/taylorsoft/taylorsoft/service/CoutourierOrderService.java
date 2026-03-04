package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.CoutourierOrderRequest;
import org.taylorsoft.taylorsoft.dtos.response.CoutourierOrderResponse;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;

import java.util.List;

public interface CoutourierOrderService {

    CoutourierOrderResponse create(CoutourierOrderRequest request);

    List<CoutourierOrderResponse> getAll();

    CoutourierOrderResponse getById(Long id);

    CoutourierOrderResponse update(Long id, CoutourierOrderRequest request);

    void delete(Long id);

    List<CoutourierOrderResponse> getByCouturierId(Long couturierId);

    List<CoutourierOrderResponse> getByFournisseurId(Long fournisseurId);

    List<CoutourierOrderResponse> getByStatus(CoutourierOrderStatus status);

    CoutourierOrderResponse updateStatus(Long id, CoutourierOrderStatus status);
}

