package com.example.etailor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import com.example.etailor.R;
import com.example.etailor.Sign_in_Activity;

public class MainActivity extends AppCompatActivity {

    private Timer timer= new Timer();
    private Intent in = new Intent();
    private TimerTask i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLogic();
        //transparent actionbar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

    }

    private void initializeLogic() {
        i = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        in.setClass(getApplicationContext(), Sign_in_Activity.class);
                        startActivity(in);
                        finish();
                    }
                });
            }
        };
        timer.schedule(i,(int)(1000));
    }
}