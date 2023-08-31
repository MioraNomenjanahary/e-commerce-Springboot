package com.gamo.ecommerce1.service;

import com.gamo.ecommerce1.DAO.*;
import com.gamo.ecommerce1.model.Article;
import com.gamo.ecommerce1.model.Customers;
import com.gamo.ecommerce1.model.Settlement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcommerceService {
    private final CustomersRepository customersRepository;
    private final SettlementRepository settlementRepository;
    private final ArticleRepository articleRepository;
    private final PromotionRepository promotionRepository;
    private final EligibleArticle eligibleArticleRepository;

    public EcommerceService(
            CustomersRepository customersRepository,
            SettlementRepository settlementRepository,
            ArticleRepository articleRepository,
            PromotionRepository promotionRepository,
            EligibleArticle eligibleArticleRepository) {
        this.customersRepository = customersRepository;
        this.settlementRepository = settlementRepository;
        this.articleRepository = articleRepository;
        this.promotionRepository = promotionRepository;
        this.eligibleArticleRepository = eligibleArticleRepository;
    }

    // Customers
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customers getCustomerById(int id) {
        return customersRepository.findById(id);
    }

    public Customers createCustomer(Customers customers) {
        return customersRepository.insert(customers);
    }

    public void updateCustomer(Customers customers) {
        customersRepository.update(customers);
    }

    public boolean deleteCustomer(int id) {
        return customersRepository.delete(id);
    }

    // Settlements
    public List<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }

    public Settlement getSettlementById(int id) {
        return settlementRepository.findById(id);
    }

    public Settlement createSettlement(Settlement settlement) {
        return settlementRepository.insert(settlement);
    }

    public void updateSettlement(Settlement settlement) {
        settlementRepository.update(settlement);
    }

    public boolean deleteSettlement(int id) {
        return settlementRepository.delete(id);
    }

    // Articles
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /*
    public Article getArticleById(int id) {
        return articleRepository.findById(id);
    }

    public boolean createArticle(Article article) {
        return articleRepository.insert(article);
    }

    public void updateArticle(Article article) {
        articleRepository.update(article);
    }

    public boolean deleteArticle(int id) {
        return articleRepository.delete(id);
    }

    // Promotions
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionById(int id) {
        return promotionRepository.findById(id);
    }

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.insert(promotion);
    }

    public void updatePromotion(Promotion promotion) {
        promotionRepository.update(promotion);
    }

    public boolean deletePromotion(int id) {
        return promotionRepository.delete(id);
    }

    // Eligible Articles
    public List<Article> getAllEligibleArticles() {
        return eligibleArticleRepository.findAll();
    }

    public Article getEligibleArticleById(int id) {
        return (Article) eligibleArticleRepository.findById(id);
    }

    public Article createEligibleArticle(Article article) {
        return eligibleArticleRepository.insert(article);
    }

    public void updateEligibleArticle(Article article) {
        eligibleArticleRepository.update(article);
    }

    public boolean deleteEligibleArticle(int id) {
        return eligibleArticleRepository.delete(id);
    }

     */
}
