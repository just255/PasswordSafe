package com.justinsimmons.passwordsafe;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

public class CreateCategory extends AppCompatActivity {

    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.btnSubmitNewPass);
        helper = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SQLiteDatabase db;
                db = helper.getWritableDatabase();

                String table;
                EditText category = (EditText)(findViewById(R.id.txtEnterNewCat));

                table = category.getText().toString();

                helper.createDynamicTables(table);

                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                startActivity(intent);
            }
        });
    }

}
