package com.example.poshell.db.reader.posdb;

import com.example.poshell.model.Product;

import java.util.List;

public interface PosDBReader {
    List<Product> read();
}
