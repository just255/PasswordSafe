package com.justinsimmons.passwordsafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by just_ on 7/27/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 1;
    public static final String PERSON_TABLE_NAME = "person";
    public static final String PERSON_COLUMN_ID = "_id";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_GENDER = "gender";

    public String columnName = "name";
    public String columnUser = "username";
    public String columnPass = "password";
    public String columnCombo = "combination";

    public static String tableName;
    public static String itemName;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creates one table called 'person'
        db.execSQL("CREATE TABLE " + PERSON_TABLE_NAME + "(" +
                PERSON_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                PERSON_COLUMN_NAME + " TEXT, " +
                PERSON_COLUMN_GENDER + " TEXT " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertPerson(String name, String user, String pass, String combo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, name);
        contentValues.put(columnUser, user);
        contentValues.put(columnPass, pass);
        contentValues.put(columnCombo, combo);
        db.insert(getTableName(), null, contentValues);
        return true;
    }

    public ArrayList<String> getItemContent() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arrTblItems = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM " + getTableName() + " WHERE " + columnName + "='" + getItemName() + "'", null);
        c.move(1);
        while (!c.isAfterLast()) {
            String user = c.getString(c.getColumnIndex(columnUser));
            String pass = c.getString(c.getColumnIndex(columnPass));
            arrTblItems.add("User: " + user + "   Password: " + pass + "\n");
            c.moveToNext();
        }
        // make sure to close the cursor
        c.close();
        return arrTblItems;
    }

    public ArrayList<String> getAllTables() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        c.move(2);
        while (!c.isAfterLast()) {

            arrTblNames.add(c.getString(c.getColumnIndex("name")));
            c.moveToNext();
        }
        // make sure to close the cursor
        c.close();
        return arrTblNames;
    }

    public ArrayList<String> getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arrTblItems = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM " + getTableName(), null);
        c.move(1);
        while (!c.isAfterLast()) {

            arrTblItems.add(c.getString(c.getColumnIndex("name")));
            c.moveToNext();
        }
        // make sure to close the cursor
        c.close();
        return arrTblItems;
    }

    public void createDynamicTables(String tableName)
    {
        SQLiteDatabase dbs;
        dbs = this.getWritableDatabase();
        dbs.execSQL("DROP TABLE IF EXISTS " + tableName);
        String query = "CREATE TABLE " + tableName + "(" +
                PERSON_COLUMN_ID + " TEXT PRIMARY KEY, " +
                columnName + " TEXT, " +
                columnUser + " TEXT, " +
                columnPass + " TEXT, " +
                columnCombo + " TEXT " +
        ")";

        dbs.execSQL(query);
        dbs.close();
    }

    public void dropTable(){
        SQLiteDatabase dbs;
        dbs = this.getWritableDatabase();
        dbs.execSQL("DROP TABLE IF EXISTS " + tableName);
        dbs.close();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /*---------------------------------------------------------------------------------------------
     * Unused methods
     *---------------------------------------------------------------------------------------------
     */
    /*
    public Integer deletePerson(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PERSON_TABLE_NAME,
                PERSON_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public boolean updatePerson(Integer id, String name, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_GENDER, gender);
        db.update(PERSON_TABLE_NAME, contentValues, PERSON_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Cursor getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + getTableName() + " WHERE " +
                PERSON_COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }
    public ArrayList<String> getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arrTblNames = new ArrayList<String>();
        String[] projection = {
                PERSON_COLUMN_ID,
                columnName,
                columnUser,
                columnPass,
                columnCombo
        };
        //Cursor c = db.rawQuery( "SELECT * FROM " + getTableName(), null );
        Cursor c = db.query(getTableName(), projection, null, null, null, null, null);
        c.move(1);
        //while (!c.isAfterLast()) {
        String user = c.getString(c.getColumnIndex(columnUser));
        String pass = c.getString(c.getColumnIndex(columnPass));
        arrTblNames.add("User: " + user + "   Password: " + pass + "\n");
        //   c.moveToNext();
        //}
        // make sure to close the cursor
        c.close();
        return arrTblNames;
    }
    */
}
