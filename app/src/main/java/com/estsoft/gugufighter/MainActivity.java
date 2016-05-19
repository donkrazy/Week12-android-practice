package com.estsoft.gugufighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        Button btn_record = (Button) findViewById(R.id.btn_record);
        btn_record.setOnClickListener(this);

        Button btn_help = (Button) findViewById(R.id.btn_help);
        btn_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch(viewid) {
            case R.id.btn_start:
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_record:
                Intent intent2 = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_help:
                Intent intent3 = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
