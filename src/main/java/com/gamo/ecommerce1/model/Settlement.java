package com.gamo.ecommerce1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Settlement {
    private int id;
    private Long payment;
    private String payment_method;
    private LocalDate date;

    public void setId(int id) {
        this.id = id;
    }

    public void setPayment(double payment) {
        this.payment = (long) payment;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
