package com.example.dbms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    EditText code;
    Button delete;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();

        code = findViewById(R.id.empcode_upd);
        delete = findViewById(R.id.button_search);

        delete.setOnClickListener(new View.OnClickListener() {
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

                dbHelper = new DBHelper(DeleteActivity.this);
                try{
                    dbHelper.deleteEmployee(empcode);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(DeleteActivity.this);
                    builder1.setMessage("Record deleted successfully!!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "GG",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    code.setText("");
                }
                catch(SQLiteException e){
                    Toast.makeText(DeleteActivity.this,e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}