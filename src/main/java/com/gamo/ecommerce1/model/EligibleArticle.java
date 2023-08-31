package com.gamo.ecommerce1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EligibleArticle {
    private int id;
    private int promotion;
    private int article;

    public void setId(int id) {
        this.id = id;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void setArticle(int article) {
        this.article = article;
    }
}
