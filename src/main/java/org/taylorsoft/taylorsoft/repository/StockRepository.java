package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Stock;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByTissuColor_IdAndCoutourier_Id(Long tissuColorId, Long couturierId);

    List<Stock> findByCoutourier_Id(Long couturierId);

    List<Stock> findByTissuColor_Id(Long tissuColorId);
}

