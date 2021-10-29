package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RetrieveActivity extends AppCompatActivity {

    EditText code;
    TextView name, gender, dept, salary;
    Button search;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        Intent intent = getIntent();

        code = findViewById(R.id.empcode_upd);
        search = findViewById(R.id.button_search);

        name = findViewById(R.id.empname_display);
        gender = findViewById(R.id.gender_display);
        dept = findViewById(R.id.dept_display);
        salary = findViewById(R.id.salary_display);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empcode = code.getText().toString();

                if (empcode.length() == 0) {
                    code.requestFocus();
                    code.setError("FIELD CANNOT BE EMPTY");
                } else if (!empcode.matches("[a-zA-Z0-9 ]+")) {
                    code.requestFocus();
                    code.setError("ENTER ONLY ALPHANUMERICAL CHARACTER");
                }

                dbHelper = new DBHelper(RetrieveActivity.this);

                String[] vals = dbHelper.retrieveEmployee(empcode);
//                Cursor c = dbHelper.retrieveEmployee(empcode);
//                if(c.moveToNext()){
//                    name.setText(c.getString(0));
//                }
                if(vals == null) {
                    Toast.makeText(RetrieveActivity.this, "Nothing returned", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    String empname = vals[0];
                    String sex = vals[1];
                    String department = vals[2];
                    String sal = vals[3];

                    name.setText(empname);
                    gender.setText(sex);
                    dept.setText(department);
                    salary.setText(sal);

                    code.setText("");
                }
            }
        });

    }
}