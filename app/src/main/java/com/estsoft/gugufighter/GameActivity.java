package com.estsoft.gugufighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timer.schedule( new MyTimerTask(), 1000, 1000 );
    }

    private class MyTimerTask extends TimerTask {
        private int seconds = 10;

        @Override
        public void run() {
            if( --seconds < 0 ) {
                timer.cancel();
                //startActivity();
                Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                startActivityForResult(intent, 0);
                finish();
                return;
            }

            // UI 변경
            runOnUiThread( new Runnable(){
                @Override
                public void run() {
                    TextView tv = (TextView)findViewById( R.id.textView3 );
                    tv.setText( ""+seconds );
                }
            });
        }
    }
}
