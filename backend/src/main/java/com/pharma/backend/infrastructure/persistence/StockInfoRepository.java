package com.pharma.backend.infrastructure.persistence;

import com.pharma.backend.domain.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {
}