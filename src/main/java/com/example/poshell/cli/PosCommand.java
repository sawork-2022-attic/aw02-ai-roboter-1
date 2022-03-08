package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.ArrayList;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    @ShellMethodAvailability("cartExistCheck")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }


    @ShellMethod(value = "Print Cart's items", key = "print")
    @ShellMethodAvailability("cartExistCheck")
    public String printCart() {
        if (posService.getCart() == null) {
            return "No Cart, You Need new Cart";
        }
        return posService.getCart().toString();
    }

    @ShellMethod(value = "empty your cart", key = "empty")
    @ShellMethodAvailability("cartExitsCheck")
    public String emptyCart() {
        posService.getCart().setItems(new ArrayList<>());
        return "Empty Successfully";
    }

    @ShellMethod(value = "modify a item's amount for", key = "d")
    @ShellMethodAvailability("cartExistCheck")
    public String modify(String productId, int amount) {
        Item target = null;
        for (var item: posService.getCart().getItems()) {
            if (productId.equals(item.getProduct().getId())) {
                target = item;
                break;
            }
        }
        if (target != null) {
            target.setAmount(amount);
            return "modify successfully";
        } else {
            return "item does not exist";
        }

    }

    private Availability cartExistCheck() {
        return posService.getCart() != null ? Availability.available():
                Availability.unavailable("The cart does not exist, You Need new Cart");
    }


}
