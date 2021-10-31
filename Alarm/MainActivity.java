package com.example.sms;

import android.app.TimePickerDialog; import android.media.Ringtone; import android.media.RingtoneManager; import android.os.Build;
import   android.os.Bundle; import android.os.Handler; import   android.os.Looper; import android.provider.Settings; import android.util.Log;
import   android.view.View; import android.widget.Button; import android.widget.TextClock; import android.widget.TextView; import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity; import java.util.Calendar;
import java.util.Timer; import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger; public class MainActivity extends AppCompatActivity {
    TimePicker alarmtime;
    TextClock currtime;
    TextView t;
    Button setAlarm,cancel,snz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currtime = findViewById(R.id.currTime);
        setAlarm = findViewById(R.id.button);
        alarmtime = findViewById(R.id.timePicker);
        cancel = findViewById(R.id.cancel);
        t = findViewById(R.id.text);
        snz= findViewById(R.id.snooze);
        final AtomicInteger value = new AtomicInteger(0);
        final AtomicInteger stop = new AtomicInteger(0);
        final Timer timer = new Timer();
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefault Uri(RingtoneManager.TYPE_ALARM));
        setAlarm.setOnClickListener(new View.OnClickListener() {
 	@Override


            alarmtime.setVisibility(View.VISIBLE);
 	stop.getAndSet(0);
 	timer.scheduleAtFixedRate(new TimerTask() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {

                    Log.i("here1",currtime.getText().toString());
                    Log.i("here2",AlarmTime());

                    if(currtime.getText().toString().equals(AlarmTime())&&stop.get()==0){
                        Log.i("here3","Works"); r.play();
                        runOnUiThread(new Runnable() { @Override
                        public void run() { t.setText("ALARM IS RINGING");
                            snz.setVisibility(View.VISIBLE);
                        }
                        });
                    }
                    else{
                        runOnUiThread(new Runnable() { @Override
                        public void run() { t.setText("");
                            snz.setVisibility(View.INVISIBLE);
                        }
                        });
                        r.stop();
                    }
                }
            },0,1000); //you want the delay to be zero because the task should be immediate and you want to check each second
        }
    });
cancel.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) { stop.getAndSet(1);
        }
    });

 	snz.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            Integer alarmMin = alarmtime.getMinute();

            alarmtime.setMinute(alarmMin+5);


        }
    });
}
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String AlarmTime(){
        Integer alarmHour = alarmtime.getHour();
        Integer alarmMin = alarmtime.getMinute();

        String alarm_time,end="AM",min,hr;
        if (alarmHour>=12){
            end = "PM";
            if (alarmHour!=12)
                alarmHour-=12;
        }
        if (alarmMin<10)
            min = "0"+String.valueOf(alarmMin);
        else{
            min = String.valueOf(alarmMin);
        }
        if (alarmHour==0)
            hr="12";
        else{
            hr = String.valueOf(alarmHour);
        }
        alarm_time = hr+":"+min+" "+end;
        return alarm_time;
    }
}

