package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        var target = getItem(item.getProduct().getId());
        if (target != null) {
            target.setAmount(target.getAmount() + item.getAmount());
            return false;
        }
        return items.add(item);
    }

    public Item getItem(String productId) {
        for (Item currentItem : items) {
            if (currentItem.getProduct().getId().equals(productId)) {
                return currentItem;
            }
        }
        return null;
    }

    public boolean deleteItem(String productId) {
        var item = getItem(productId);
        return getItems().remove(item);
    }

    public boolean modifyItem(String productId, int amount) {
        var item = getItem(productId);
        if (item == null) {
            return false;
        }
        item.setAmount(amount);
        return true;
    }

    @Override
    public String toString() {
        if (items.size() == 0) {
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n");

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n");

        stringBuilder.append("Total...\t\t\t").append(total);

        return stringBuilder.toString();
    }
}
