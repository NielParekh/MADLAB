package com.example.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {
    EditText fileContent;
    Button read, write;
    final String dir = "FilePath";
    final String fileName = "myContent.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read = findViewById(R.id.read);
        write = findViewById(R.id.write);
        fileContent = findViewById(R.id.fileContent);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File extFile = new File(getExternalFilesDir(dir), fileName);
                try {
                    FileReader fr = new FileReader(extFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line=br.readLine();
                    fileContent.setText(line);

                }
                catch (Exception e) {
                    fileContent.setError("Unable to read file!");
                    e.printStackTrace();
                }
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = fileContent.getText().toString();
                File extFile = new File(getExternalFilesDir(dir), fileName);
                try {
                    FileOutputStream fs = new FileOutputStream(extFile);
                    fs.write(content.getBytes());
                    fileContent.setText("");
                    Toast toast = Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT);
                    toast.show();

                }
                catch (Exception e) {
                    fileContent.setError("Unable to write file!");
                    e.printStackTrace();
                }
            }
        });


    }

    private boolean checkPermissions(){
        String perms = Environment.getExternalStorageState();
        if (perms.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

}
