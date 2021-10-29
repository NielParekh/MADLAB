package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;
public class MainActivity extends AppCompatActivity {
    EditText contact_no,user_message;
    Button send;
    TextView msgs;
    IntentFilter intentFilter;
    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            msgs.setText(intent.getExtras().getString("message"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new
                String[]{READ_SMS,RECEIVE_SMS,SEND_SMS,READ_PHONE_STATE},1);
        contact_no = findViewById(R.id.contact_number);
        user_message = findViewById(R.id.user_message);
        send = findViewById(R.id.sendButton);
//        msgs = findViewById(R.id.msgs);
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(contact_no.getText().toString(),user_message.getText().toString());
            }
        });
    }
    void sendMsg(String num,String myMsg) {
        String SENT = "Message Sent";
        String DELIVERED = "Message Delivered";
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this,0,new Intent(DELIVERED),0);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(num,null,myMsg,sentPI,deliveredPI);
    }
    @Override
    protected void onPause() {
        unregisterReceiver(intentReceiver);
        super.onPause();
    }
    @Override
    protected void onResume() {
        registerReceiver(intentReceiver,intentFilter);
        super.onResume();
    }
}
