package com.gamo.ecommerce1.DAO;

import com.gamo.ecommerce1.model.Settlement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository {
    List<Settlement> findAll();
    Settlement findById(int id);
    Settlement insert(Settlement settlement);
    void update(Settlement settlement);
    boolean delete(int id);
}
