package com.codecool.borrowable;


import java.util.ArrayList;
import java.util.List;

public class Library implements java.io.Serializable {
    private List<Borrowable> stock;

    public Library() {
        stock = new ArrayList<>();
    }

    public void addItem(Borrowable item) {
        stock.add(item);
    }

    public List<Borrowable> getStock() {
        return stock;
    }


    public boolean isInStock(String name) {
        for (Borrowable item : stock) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


}
