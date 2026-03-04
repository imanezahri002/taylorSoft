package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.CoutourierOrder;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;

import java.util.List;

@Repository
public interface CoutourierOrderRepository extends JpaRepository<CoutourierOrder, Long> {

    List<CoutourierOrder> findByCoutourier_Id(Long couturierId);

    List<CoutourierOrder> findByFournisseur_Id(Long fournisseurId);

    List<CoutourierOrder> findByStatus(CoutourierOrderStatus status);

    List<CoutourierOrder> findByCoutourier_IdAndStatus(Long couturierId, CoutourierOrderStatus status);

    List<CoutourierOrder> findByFournisseur_IdAndStatus(Long fournisseurId, CoutourierOrderStatus status);
}

