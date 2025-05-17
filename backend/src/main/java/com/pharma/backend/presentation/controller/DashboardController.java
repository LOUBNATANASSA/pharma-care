package com.pharma.backend.presentation.controller;



import com.pharma.backend.domain.Alerte;
import com.pharma.backend.domain.Medication;
import com.pharma.backend.application.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/total-medications")
    public long getTotalMedications() {
        return dashboardService.getTotalMedications();
    }



    @GetMapping("/expiring-soon")
    public long getExpiringMedicationsCount() {
        return dashboardService.getExpiringSoonCount();
    }

    @GetMapping("/monthly-sales")
    public float getMonthlySales() {
        return dashboardService.getMonthlySalesTotal();
    }

    @GetMapping("/recent-alerts")
    public List<Alerte> getRecentAlerts() {
        return dashboardService.getRecentAlerts();
    }
}
