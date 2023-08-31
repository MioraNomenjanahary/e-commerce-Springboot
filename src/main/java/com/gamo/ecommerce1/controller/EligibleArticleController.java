package com.gamo.ecommerce1.controller;

import com.gamo.ecommerce1.model.Article;
import com.gamo.ecommerce1.DAO.EligibleArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eligibleArticle")
public class EligibleArticleController {
    @Autowired
    private final EligibleArticle eligibleArticleRepository;
    public EligibleArticleController(EligibleArticle eligibleArticleRepository) {
        this.eligibleArticleRepository = eligibleArticleRepository;
    }

    @GetMapping
    public List<Article> getAllEligibleArticles() {
        return eligibleArticleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Article getEligibleArticleById(@PathVariable int id) {
        return (Article) eligibleArticleRepository.findById(id);
    }

    @PostMapping
    public Article createEligibleArticle(@RequestBody Article article) {
        return eligibleArticleRepository.insert(article);
    }

    @PutMapping("/{id}")
    public void updateEligibleArticle(@PathVariable int id, @RequestBody Article article) {
        article.setId(id);
        eligibleArticleRepository.update(article);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEligibleArticle(@PathVariable int id) {
        return eligibleArticleRepository.delete(id);
    }
}
