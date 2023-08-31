package com.gamo.ecommerce1.controller;

import com.gamo.ecommerce1.model.Promotion;
import com.gamo.ecommerce1.DAO.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Promotion")
public class PromotionController {
    @Autowired
    private final PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable int id) {
        return promotionRepository.findById(id);
    }

    @PostMapping
    public Promotion createPromotion(@RequestBody Promotion promotion) {
        return promotionRepository.insert(promotion);
    }

    @PutMapping("/{id}")
    public void updatePromotion(@PathVariable int id, @RequestBody Promotion promotion) {
        promotion.setId(id);
        promotionRepository.update(promotion);
    }

    @DeleteMapping("{id}")
    public boolean deletePromotion(@PathVariable int id) {
        return promotionRepository.delete(id);
    }
}
