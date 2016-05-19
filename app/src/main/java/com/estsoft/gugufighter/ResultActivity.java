package com.estsoft.gugufighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button btn_regame = (Button) findViewById(R.id.btn_regame);
        btn_regame.setOnClickListener(this);
        Button btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        if(viewid==R.id.btn_regame){
            Intent intent = new Intent(ResultActivity.this, GameActivity.class);
            startActivity(intent);
        }
        else if (viewid==R.id.btn_exit){
            Toast.makeText( getApplicationContext(), "수고하셨습니다", Toast.LENGTH_LONG ).show();
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
