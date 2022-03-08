package com.example.poshell.db.reader.posdb;

import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PosDBFileReader implements PosDBReader {


    @Value("${dbPath}")
    String dbPath;

    @Override
    public List<Product> read() {
        File file = new File(dbPath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader.lines().map(line -> {
                String[] token = line.split("\t");
                if (token.length != 3)
                    return null;
                return new Product(token[0], token[1], Integer.parseInt(token[2]));
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
