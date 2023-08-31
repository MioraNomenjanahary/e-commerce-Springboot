package com.gamo.ecommerce1.controller;

import com.gamo.ecommerce1.model.Article;
import com.gamo.ecommerce1.service.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final EcommerceService ecommerceService;

    @Autowired
    public ArticleController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }
    @GetMapping
    public List<Article> getAllArticles() {
        return ecommerceService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable int id) {
        return articleRepository.getArticleById(id);
    }

    @PostMapping
    public void createArticle(@RequestBody Article article) {
        articleRepository.createArticle(article);
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable int id, @RequestBody Article article) {
        article.setId(id);
        articleRepository.updateArticle(article);
    }

    @DeleteMapping("/{id}")
    public boolean deleteArticle(@PathVariable int id) {
        return articleRepository.deleteArticle(id);
    }
}
