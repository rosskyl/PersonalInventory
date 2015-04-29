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

    }//end ParseInventory method

    public Item getItem(String tmpName) {
        ParseQuery<Item> query = ParseQuery.getQuery("inventory");
        query.whereEqualTo("name", tmpName);
        try {
            Item item = query.getFirst();

            return item;
        } catch(ParseException e) {
            Log.d("getItem", e.getMessage().toString());
            return null;
        }//end catch
    }//end getItem method

    public ArrayList<Item> getAllItems() {
        ParseQuery<Item> query = ParseQuery.getQuery("inventory");

        try {
            List<Item> objects = query.find();

            return (ArrayList<Item>) objects;
        }//end try
        catch(ParseException e) {
            Log.d("getAllItems", e.getMessage().toString());
            return null;
        }//end catch
    }//end getAllItems method
}//end ParseInventory class
