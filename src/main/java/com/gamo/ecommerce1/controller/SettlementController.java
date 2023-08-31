package com.gamo.ecommerce1.controller;

import com.gamo.ecommerce1.model.Settlement;
import com.gamo.ecommerce1.DAO.SettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Settlement")
public class SettlementController {
    @Autowired
    private final SettlementRepository settlementRepository;

    public SettlementController(SettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    @GetMapping
    public List<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Settlement getSettlementById(@PathVariable int id) {
        return settlementRepository.findById(id);
    }

    @PostMapping
    public Settlement createSettlement(@RequestBody Settlement settlement) {
        return settlementRepository.insert(settlement);
    }

    @PutMapping("/{id}")
    public void updateSettlement(@PathVariable int id, @RequestBody Settlement settlement) {
        settlement.setId(id);
        settlementRepository.update(settlement);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSettlement(@PathVariable int id) {
        return settlementRepository.delete(id);
    }
}
