package com.pharma.backend.application;

import com.pharma.backend.domain.StockInfo;
import com.pharma.backend.domain.Lot;
import com.pharma.backend.infrastructure.persistence.LotRepository;
import com.pharma.backend.infrastructure.persistence.StockInfoRepository;
import com.pharma.backend.presentation.dto.StockInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockInfoService {

    private final StockInfoRepository stockRepo;
    private final LotRepository lotRepo;

    public StockInfoService(StockInfoRepository stockRepo, LotRepository lotRepo) {
        this.stockRepo = stockRepo;
        this.lotRepo = lotRepo;
    }

    public List<StockInfo> findAll() {
        return stockRepo.findAll();
    }

    public StockInfo findById(int id) {
        return stockRepo.findById(id).orElseThrow();
    }

    public StockInfo save(StockInfoDTO dto) {
        Lot lot = lotRepo.findById(dto.lotId()).orElseThrow(() ->
                new IllegalArgumentException("Lot introuvable avec id: " + dto.lotId()));
        StockInfo stock = new StockInfo(dto.matricule(), dto.quantiteStock(), dto.date(), lot);
        return stockRepo.save(stock);
    }

    public void delete(int id) {
        stockRepo.deleteById(id);
    }
}
