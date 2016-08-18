package com.justinsimmons.passwordsafe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Cursor cursor;
    protected static String categoryTable;
    protected static int manageCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        dbHelper = new DBHelper(this);
        final ArrayList<String> stringArray = dbHelper.getAllTables();

        final ListView myListView = (ListView) findViewById(R.id.lvCategories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, stringArray);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                dbHelper.setTableName(stringArray.get(position));
                System.out.println(stringArray.get(position));
                Intent newActivity = new Intent(CategoriesActivity.this, DisplayTableItems.class);
                startActivity(newActivity);
            }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        int id = item.getItemId();

        if (id == R.id.addCategory) {
            setManageCat(0);
            Intent intent = new Intent(this, CreateCategory.class);
            startActivity(intent);
            Log.i("Test", "Selected Add Category");
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            Log.i("Test", "Selected Settings");
        } else if (id == R.id.removeCategory){
            setManageCat(1);
            Intent intent = new Intent(this, CreateCategory.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public static int getManageCat() {
        return manageCat;
    }

    public static void setManageCat(int manageCat) {
        CategoriesActivity.manageCat = manageCat;
    }

}
