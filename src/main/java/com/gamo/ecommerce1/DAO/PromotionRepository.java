package com.gamo.ecommerce1.DAO;

import com.gamo.ecommerce1.model.Promotion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository {
    List<Promotion> findAll();
    Promotion findById(int id);
    Promotion insert(Promotion promotion);
    void update(Promotion promotion);
    boolean delete(int id);
}
