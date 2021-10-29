package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

EditText e1,e2;
TextView t1;
int n1,n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean getvalues(){
        e1= (EditText) findViewById(R.id.Value1);
        e2 = (EditText) findViewById(R.id.Value2);
        t1 = (TextView) findViewById(R.id.Result);
String s1 =e1.getText().toString();
String s2 = e2.getText().toString();
if ((s1.equals("")) || (s2.equals(""))){
    t1.setText("Enter values");
    Toast.makeText(getApplicationContext(), "Enter value",Toast.LENGTH_SHORT).show();
    return false; }
else{
    n1 = Integer.parseInt(s1);
    Log.d("I",s1);
    n2 = Integer.parseInt(s2);
    Log.d("I",s2);
}
return true;
    }
public void doSum(View v){
        if(getvalues()){
            int sum  = n1 + n2;
t1.setText(Integer.toString(sum));
        }

}

}

