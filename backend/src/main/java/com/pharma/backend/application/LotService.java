package com.pharma.backend.application;

import com.pharma.backend.domain.Lot;
import com.pharma.backend.infrastructure.persistence.LotRepository;
import com.pharma.backend.presentation.dto.LotDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {

    private final LotRepository lotRepository;

    public LotService(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public Lot getLotById(int id) {
        return lotRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Lot avec l'id " + id + " introuvable")
        );
    }

    public Lot createLot(LotDTO dto) {
        Lot lot = new Lot();
        lot.setNom_lot(dto.Nom_lot());
        lot.setDateReception(dto.dateReception());
        lot.setMaxQuantite(dto.maxQuantite());
        lot.setType(dto.type());
        return lotRepository.save(lot);
    }

    public Lot updateLot(int id, LotDTO dto) {
        Lot lot = lotRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Lot avec l'id " + id + " introuvable")
        );
        lot.setNom_lot(dto.Nom_lot());
        lot.setDateReception(dto.dateReception());
        lot.setMaxQuantite(dto.maxQuantite());
        lot.setType(dto.type());
        return lotRepository.save(lot);
    }

    public void deleteLot(int id) {
        lotRepository.deleteById(id);
    }
}
