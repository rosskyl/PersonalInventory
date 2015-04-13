package com.example.br161.personalinventory;

/**
 * Created by al1694bc on 4/13/2015.
 */
public class Item {

    private String name;

    private String description;

    private int quantity;

    public Item(String name, String description, int quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }//end Item method

    public String getName() {
        return name;
    }//end getName method

    public void setName(String name) {
        this.name = name;
    }//end setName method

    public String getDescription() {
        return description;
    }//end getDescription method

    public void setDescription(String description) {
        this.description = description;
    }//end setDescription method

    public int getQuantity() {
        return quantity;
    }//end getQuantity method

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }//end setQuantity method
}//end Item class
