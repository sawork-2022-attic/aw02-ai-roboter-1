package com.example.poshell.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private double price;

    @Override
    public String toString() {
        return getId() + "\t" + getName() + "\t" + getPrice();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Product) {
            Product pr = (Product) o;
            return Objects.equals(pr.getId(), getId());
        } else {
            return false;
        }
    }

}
