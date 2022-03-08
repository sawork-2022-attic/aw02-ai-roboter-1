package com.example.poshell.biz;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosService {
    Cart getCart();

    Cart newCart();

    void checkout(Cart cart);

    void total(Cart cart);

    boolean add(Product product, int amount);

    boolean add(String productId, int amount);


    List<Product> products();
}
