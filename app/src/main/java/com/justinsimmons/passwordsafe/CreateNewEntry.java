package com.justinsimmons.passwordsafe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

public class CreateNewEntry extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.newEntrySubmit);
        dbHelper = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SQLiteDatabase db;
                db = dbHelper.getWritableDatabase();
                // Create a new map of values, where column names are the keys
                //helper.insertPerson("Website", "Test");

                EditText textName = (EditText)(findViewById(R.id.txtEntryName));
                EditText textUser = (EditText)(findViewById(R.id.txtEntryUser));
                EditText textPass = (EditText)(findViewById(R.id.txtEntryPass));

                String name = textName.getText().toString();
                String user = textUser.getText().toString();
                String pass = textPass.getText().toString();

                dbHelper.insertPerson(name, user, pass, null);


                Intent intent = new Intent(getApplicationContext(), DisplayTableItems.class);
                startActivity(intent);
            }
        });
    }

}
