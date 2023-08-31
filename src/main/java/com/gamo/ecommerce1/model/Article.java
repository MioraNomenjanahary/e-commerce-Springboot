package com.gamo.ecommerce1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private int id;
    private String name;
    private String description;
    private String size;
    private Long price;
    private String gender;
    private String category;
    private Boolean availability;

    public Article() {
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

}
