package com.estsoft.gugufighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        //데이터 수신
        Intent intent = getIntent();
        Integer score = intent.getIntExtra( "data-score", -1 );
        Integer count = intent.getIntExtra( "data-count", -1 );

        //점수
        String result = score+"/"+count;
        TextView tv_result = (TextView)findViewById( R.id.result );
        tv_result.setText( result );


        //등수
        int ranking = checkRecord(score, count);
        TextView tv_rank = (TextView)findViewById( R.id.ranking );
        tv_rank.setText(ranking+"등");

        //랭킹 등록
        TextView tv_name = (TextView)findViewById(R.id.yourname);
        Button btn_submit = (Button)findViewById(R.id.submit);
        btn_submit.setOnClickListener(this);



        //한판더 또는 이제 그만
        Button btn_regame = (Button) findViewById(R.id.btn_regame);
        btn_regame.setOnClickListener(this);
        Button btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);

    }

    private int checkRecord(Integer score, Integer count) {
        //정답 수와 정답률을 고려하여 딥러닝후 순위 산출
        return 3;
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
        else if( viewid==R.id.submit ){
            //TODO: DB에 이름과 기록 저장후 명예의 전당으로
            Toast.makeText( getApplicationContext(), "DB에 이름과 기록 저장", Toast.LENGTH_LONG ).show();
            Intent intent = new Intent(ResultActivity.this, RecordActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
