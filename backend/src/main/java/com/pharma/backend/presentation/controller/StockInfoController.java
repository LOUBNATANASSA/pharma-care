package com.pharma.backend.presentation.controller;

import com.pharma.backend.application.StockInfoService;
import com.pharma.backend.domain.StockInfo;
import com.pharma.backend.presentation.dto.StockInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockinfo")
@CrossOrigin(origins = "*")
public class StockInfoController {

    private final StockInfoService service;

    public StockInfoController(StockInfoService service) {
        this.service = service;
    }

    @GetMapping
    public List<StockInfo> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StockInfo getOne(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockInfo create(@RequestBody StockInfoDTO dto) {
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
