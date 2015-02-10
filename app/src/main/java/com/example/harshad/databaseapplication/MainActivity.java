package com.example.harshad.databaseapplication;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener {


    EditText name,bday;
    Button add,next;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.txtSearchName);
        bday = (EditText) findViewById(R.id.txtDate);
        add = (Button) findViewById(R.id.btnAdd);
        next=(Button)findViewById(R.id.btnNext);
        add.setOnClickListener(this);
        next.setOnClickListener(this);


        db = openOrCreateDatabase("FriendsDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS BirthdayList(name VARCHAR,bday VARCHAR);");


    }

    public void onClick(View view)
    {
        // Adding a record
        if(view==add)
        {
            // Checking empty fields
            if(name.getText().toString().trim().length()==0||
                    bday.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            // Inserting record
            db.execSQL("INSERT INTO BirthdayList VALUES('"+name.getText()+"','"+bday.getText()+"');");
            showMessage("Success", "Record added");

        }

        if(view==next)
        {
            Intent i=new Intent(MainActivity.this,SearchActivity.class);
            startActivity(i);
        }


    }


    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
