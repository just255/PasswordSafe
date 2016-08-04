package com.justinsimmons.passwordsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class DisplayTableItems extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_table_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(this);

        final ArrayList<String> stringArray = dbHelper.getAllItems();

        ListView myListView = (ListView) findViewById(R.id.lvTableItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, stringArray);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                dbHelper.setItemName(stringArray.get(position));

                Intent intent = new Intent(getApplicationContext(), DisplayItemContent.class);
                startActivity(intent);

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
            Intent intent = new Intent(this, CreateNewEntry.class);
            startActivity(intent);
        } else if (id == R.id.action_settings) {
            //Intent intent = new Intent(this, CategoriesActivity.class);
            //startActivity(intent);
            Log.i("Test", "Selected Settings");
        }
        return super.onOptionsItemSelected(item);
    }
}
