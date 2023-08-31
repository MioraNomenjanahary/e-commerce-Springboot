package com.gamo.ecommerce1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class Promotion {
    private int id;

    private String type;

    private LocalDate end_date;

    private LocalDate start_date;

    private Double values;

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public void setValues(Double values) {
        this.values = values;
    }
}

