package com.example.myapplication.LocalDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Model.RestaurantsDetails;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    int id=0;
    private static final String DATABASE_NAME = "restaurantDetails";
    private static final String TABLE_RESTAURANT = "restaurant";
    private static final String RES_NAME = "name";
    private static final String RES_PRICE = "price";
    private static final String RES_ADDRESS = "address";
    private static final String RES_CUISINES = "cuisines";

    public SQLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_RESTAURANT + "("
                + RES_NAME + " TEXT,"
                + RES_PRICE + " TEXT,"
                + RES_ADDRESS+ " TEXT ,"
                +RES_CUISINES+ " TEXT "+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addContact(RestaurantsDetails contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RES_NAME, contact.getName()); // Contact Name
        values.put(RES_ADDRESS, contact.getAddress()); // Contact address
        values.put(RES_CUISINES, contact.getCuisines()); // Contact cuisines
        values.put(RES_PRICE, contact.getPrice()); // Contact price

        // Inserting Row
        db.insert(TABLE_RESTAURANT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all contacts in a list view
    public List<RestaurantsDetails> getAllContacts() {
        List<RestaurantsDetails> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RestaurantsDetails contact = new RestaurantsDetails();

                contact.setName(cursor.getString(0));
                contact.setAddress((cursor.getString(1)));
                contact.setCuisines(cursor.getString(2));
                contact.setPrice(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}

