package com.example.br161.personalinventory;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by al1694bc on 4/13/2015.
 */
public class ParseInventory {

    private ParseObject inventory;

    private ParseUser user;

    public ParseInventory() {

        inventory = new ParseObject("inventory");

        user = ParseUser.getCurrentUser();

        inventory.setACL(new ParseACL(user));
    }//end ParseInventory method

    public boolean putItem(String name, String description, int quantity) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("inventory");
        query.whereEqualTo("name", name);
        query.whereEqualTo("user", user);
        try {
            ParseObject object = query.getFirst();

            //that item name is already in the database
            return false;
        } catch(ParseException e) {
            inventory.put("name", name);
            inventory.put("description", description);
            inventory.put("quantity", quantity);
            inventory.put("category", "");
            inventory.put("isFavorite", false);
            inventory.put("user", user);
            inventory.saveInBackground();

            return true;
        }//end catch
    }//end putItem method

    public Item getItem(String tmpName) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("inventory");
        query.whereEqualTo("name", tmpName);
        try {
            ParseObject object = query.getFirst();

            String name = object.getString("name");
            String description = object.getString("description");
            int quantity = object.getInt("quantity");
            String category = object.getString("category");
            boolean isFavorite = object.getBoolean("isFavorite");

            Item item = new Item(name, description, quantity, category, isFavorite);
            return item;
        } catch(ParseException e) {
            Log.d("getItem", e.getMessage().toString());
            return null;
        }//end catch
    }//end getItem method

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("inventory");

        try {
            List<ParseObject> objects = query.find();

            for (int i = 0; i < objects.size(); i += 1) {
                String name = objects.get(i).getString("name");
                String description = objects.get(i).getString("description");
                int quantity = objects.get(i).getInt("quantity");
                String category = objects.get(i).getString("category");
                boolean isFavorite = objects.get(i).getBoolean("isFavorite");


                items.add(new Item(name, description, quantity, category, isFavorite));
            }//end for loop
        }//end try
        catch(ParseException e) {
            Log.d("getAllItems", e.getMessage().toString());
        }//end catch

        return items;
    }//end getAllItems method
}//end ParseInventory class
