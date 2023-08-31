package com.gamo.ecommerce1.DAO;

import com.gamo.ecommerce1.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
    List<Article> findAll();

    Article findById(int id);
    boolean insert(Article article);
    boolean update(Article article);
    boolean delete(int id);
}
