package com.pharma.backend.application;



import com.pharma.backend.domain.Alerte;
import com.pharma.backend.domain.Medication;
import com.pharma.backend.infrastructure.persistence.AlerteRepository;
import com.pharma.backend.infrastructure.persistence.MedicationRepository;
import com.pharma.backend.infrastructure.persistence.StockMovementRepository;
import com.pharma.backend.infrastructure.persistence.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private MedicationRepository medicamentRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private AlerteRepository alerteRepository;

    public long getTotalMedications() {
        return medicamentRepository.count();
    }
    


    public long getExpiringSoonCount() {
        LocalDate today = LocalDate.now();
        LocalDate in30Days = today.plusDays(30);
        return stockMovementRepository.countByDateexpirationBefore(in30Days);
    }

    public float getMonthlySalesTotal() {
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        Float total = venteRepository.sumTotalByDateAfter(firstDay);
        return total != null ? total : 0;
    }

    public List<Alerte> getRecentAlerts() {
        return alerteRepository.findTop5ByOrderByDateDesc();
    }
}
