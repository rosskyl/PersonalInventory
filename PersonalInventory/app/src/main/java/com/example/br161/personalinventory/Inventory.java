package com.example.br161.personalinventory;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by al1694bc on 4/13/2015.
 */
public class Inventory {

    private ParseObject inventory;

    public Inventory() {
        inventory = new ParseObject("inventory");
    }//end Inventory method

    public boolean putItem(String name, String description, int quantity) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("inventory");
        query.whereEqualTo("name", name);
        try {
            ParseObject object = query.getFirst();

            //nothing in the database
            return false;
        } catch(ParseException e) {
            inventory.put("name", name);
            inventory.put("description", description);
            inventory.put("quantity", quantity);
            inventory.saveInBackground();

            return true;
        }//end catch
    }//end putItem method

    private void put() {

    }//end put method

    public Item getItem(String name) {


        return null;
    }//end getItem method
}//end Inventory class
