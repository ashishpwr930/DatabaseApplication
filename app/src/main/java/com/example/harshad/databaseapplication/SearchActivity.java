package com.example.harshad.databaseapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SearchActivity extends Activity implements View.OnClickListener{

    EditText name;
    Button search;
    TextView lblShow;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search=(Button)findViewById(R.id.btnSearch);
        name=(EditText)findViewById(R.id.txtSearchName);
           lblShow=(TextView)findViewById(R.id.lblShow);

        search.setOnClickListener(this);


        db = openOrCreateDatabase("FriendsDB", Context.MODE_PRIVATE, null);


    }


    public void onClick(View view) {
        // Adding a record
       if (view == search) {
            if (name.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Name");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM BirthdayList WHERE name='" + name.getText() + "'", null);
            if (c.moveToFirst()) {
                lblShow.setText(c.getString(1));
            } else {
                showMessage("Error", "Invalid Name");

            }


        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
