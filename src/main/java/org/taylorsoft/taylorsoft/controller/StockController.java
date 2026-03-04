package org.taylorsoft.taylorsoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.response.StockResponse;
import org.taylorsoft.taylorsoft.entity.Stock;
import org.taylorsoft.taylorsoft.mapper.StockMapper;
import org.taylorsoft.taylorsoft.repository.StockRepository;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    /**
     * Consulter tous les stocks
     */
    @GetMapping
    public ResponseEntity<List<StockResponse>> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return ResponseEntity.ok(stockMapper.toResponseList(stocks));
    }

    /**
     * Consulter les stocks d'un couturier
     */
    @GetMapping("/coutourier/{couturierId}")
    public ResponseEntity<List<StockResponse>> getStocksByCouturier(@PathVariable Long couturierId) {
        List<Stock> stocks = stockRepository.findByCoutourier_Id(couturierId);
        return ResponseEntity.ok(stockMapper.toResponseList(stocks));
    }

    /**
     * Consulter le stock d'un tissu-couleur spécifique
     */
    @GetMapping("/tissu-color/{tissuColorId}")
    public ResponseEntity<List<StockResponse>> getStocksByTissuColor(@PathVariable Long tissuColorId) {
        List<Stock> stocks = stockRepository.findByTissuColor_Id(tissuColorId);
        return ResponseEntity.ok(stockMapper.toResponseList(stocks));
    }

    /**
     * Consulter un stock spécifique
     */
    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getStockById(@PathVariable Long id) {
        return stockRepository.findById(id)
                .map(stockMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

