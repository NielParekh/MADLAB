package com.example.x3_db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button insert,delete,update, viewer;
    private EditText name, phone;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        insert = findViewById(R.id.btin);
        update = findViewById(R.id.btup);
        viewer = findViewById(R.id.btview);
        delete = findViewById(R.id.btdel);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_val = name.getText().toString();
                String phoone_val = phone.getText().toString();

                Boolean checker = DB.insert_user(name_val,phoone_val);
            }
        });
//
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_val = name.getText().toString();
                Boolean val = DB.delete_user(name_val);
            }
        });
//
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_val = name.getText().toString();
                String phone_val = phone.getText().toString();
                Boolean val = DB.update_user(name_val,phone_val);
            }
        });
//
        viewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor curs = DB.view_user();
                if (curs.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "bruh", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sbuff = new StringBuffer();
                while(curs.moveToNext())
                {
                    sbuff.append("name:" + curs.getString(0) + "\n");
                    sbuff.append("phone:" + curs.getString(1) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("USER ENTRIES");
                builder.setMessage(sbuff);
                builder.show();
            }

        });

    }


}