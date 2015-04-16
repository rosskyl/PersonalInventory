package com.example.br161.personalinventory;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by al1694bc on 4/15/2015.
 */
public class ParseCategories {

    private ParseObject categories;

    private ParseUser user;

    public ParseCategories() {

        categories = new ParseObject("categories");

        user = ParseUser.getCurrentUser();

        categories.setACL(new ParseACL(user));
    }//end ParseCategories method

    public boolean putCategory(String name) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("inventory");
        query.whereEqualTo("name", name);
        query.whereEqualTo("user", user);
        try {
            ParseObject object = query.getFirst();

            //that category is already in the database
            return false;
        } catch(ParseException e) {
            categories.put("name", name);
            categories.put("user", user);
            categories.saveInBackground();

            return true;
        }//end catch
    }//end putCategory method
}//end ParseCategories class
