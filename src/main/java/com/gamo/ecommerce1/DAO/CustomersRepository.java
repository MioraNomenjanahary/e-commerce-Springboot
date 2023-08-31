package com.gamo.ecommerce1.DAO;

import com.gamo.ecommerce1.model.Customers;

import javax.crypto.Cipher;
import java.util.List;
    public interface CustomersRepository {
        List<Customers> findAll();
        Customers findById(int id);
        Customers insert(Customers customers);
        void update(Customers customers);
        boolean delete(int id);
}
