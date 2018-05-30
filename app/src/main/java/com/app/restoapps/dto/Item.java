package com.app.restoapps.dto;

/**
 * TODO: Add a class header comment!
 */
public class Item {
    int id;
    String name;
    int price;
    int qty;

    public Item(){
        this.qty = 0;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
