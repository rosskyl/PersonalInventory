package com.example.br161.personalinventory;

import com.parse.ParseACL;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.Serializable;

/**
 * Created by al1694bc on 4/13/2015.
 */
@ParseClassName("inventory")
public class Item extends ParseObject implements Serializable {

    private ParseUser user;

    //Default constructor needed for Parse
    public Item() {}//end Item method

    public Item(String name, String description, int quantity, String category) {
        user = ParseUser.getCurrentUser();

        this.setACL(new ParseACL(user));

        setName(name);
        setDescription(description);
        setQuantity(quantity);
        setCategory(category);
        setFavorite(false);

        saveInBackground();
    }//end Item method

    public Item(String name, String description, int quantity, String category, boolean isFavorite) {
        user = ParseUser.getCurrentUser();

        this.setACL(new ParseACL(user));

        setName(name);
        setDescription(description);
        setQuantity(quantity);
        setCategory(category);
        setFavorite(isFavorite);

        saveInBackground();
    }//end Item method

    public String getName() {
        return getString("name");
    }//end getName method

    public void setName(String name) {
        put("name", name);
    }//end setName method

    public String getDescription() {
        return getString("description");
    }//end getDescription method

    public void setDescription(String description) {
        put("description", description);
    }//end setDescription method

    public int getQuantity() {
        return getInt("quantity");
    }//end getQuantity method

    public void setQuantity(int quantity) {
        put("quantity", quantity);
    }//end setQuantity method

    public String getCategory() {
        return getString("category");
    }//end getCategory method

    public void setCategory(String category) {
        put("category", category);
    }//end setCategory method

    public boolean isFavorite() {
        return getBoolean("isFavorite");
    }//end isFavorite method

    public void setFavorite(boolean isFavorite) {
        put("isFavorite", isFavorite);
    }//end setFavorite method

}//end Item