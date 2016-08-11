package com.justinsimmons.passwordsafe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class CreateNewEntry extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner spinner = (Spinner) findViewById(R.id.entrySelect);
        TextView spinnerLabel = (TextView) findViewById(R.id.lblSaveIn);
        Button button = (Button) findViewById(R.id.newEntrySubmit);
        dbHelper = new DBHelper(this);

        //Sets 'Save In' and spinner to visible if a table hasn't been selected and visible if
        //a table is already defined
        if (dbHelper.getTableName().equals("")) {
            spinnerLabel.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            ArrayList<String> stringArray = dbHelper.getAllTables();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        } else {
            spinnerLabel.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
        }
        //Sets and submits entered data into selected table
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SQLiteDatabase db;
                db = dbHelper.getWritableDatabase();

                EditText textName = (EditText)(findViewById(R.id.txtEntryName));
                EditText textUser = (EditText)(findViewById(R.id.txtEntryUser));
                EditText textPass = (EditText)(findViewById(R.id.txtEntryPass));

                //Checks for empty values in text fields
                if(textName.getText().toString().trim().length() > 0 && textUser.getText().toString().trim().length() > 0
                        && textPass.getText().toString().trim().length() > 0) {
                    String name = textName.getText().toString();
                    String user = textUser.getText().toString();
                    String pass = textPass.getText().toString();

                    dbHelper.insertPerson(name, user, pass, null);


                    Intent intent = new Intent(getApplicationContext(), DisplayTableItems.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "All Fields Required!", Toast.LENGTH_LONG).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Selects which table to store new entry
                dbHelper.setTableName(spinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
/*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DisplayTableItems.class);
        startActivity(intent);
    }
*/
}
