package com.example.poshell.db;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosDB {

    List<Product> getProducts();

    Cart saveCart(Cart cart);

    Cart getCart();

    Product getProduct(String productId);

}
