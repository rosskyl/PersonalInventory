package com.example.br161.personalinventory;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by al1694bc on 4/15/2015.
 */
public class ParseCategories {

    private ParseObject categories;

    public ParseCategories() {

        categories = new ParseObject("categories");
    }//end ParseCategories method

    public boolean putCategory(String name) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("inventory");
        query.whereEqualTo("name", name);
        try {
            ParseObject object = query.getFirst();

            //that category is already in the database
            return false;
        } catch(ParseException e) {
            categories.put("name", name);
            categories.saveInBackground();

            return true;
        }//end catch
    }//end putCategory method
}//end ParseCategories class
