package com.gamo.ecommerce1.repository;

import com.gamo.ecommerce1.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EligibleArticle {
    List<Article> findAll();
    EligibleArticle findById(int id);
    EligibleArticle insert(EligibleArticle eligibleArticle);
    void update(EligibleArticle eligibleArticle);

    Article insert(Article article);

    void update(Article article);

    boolean delete(int id);
}
