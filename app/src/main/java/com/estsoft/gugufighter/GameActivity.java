package com.estsoft.gugufighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private Timer timer = new Timer();
    private static int score;
    private static int count;
    private final int NUM_BUTTONS = 9;
    private static List<Button> buttons = new ArrayList<>();
    private List<Integer> pool = generateAnsPool();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
        timer.schedule( new MyTimerTask(), 1000, 1000 );
        initGame(buttons);
    }


    //게임 도중 뒤로가기 버튼을 눌렀을 때. onPause override해줘도 된다
    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }

    //매판 시작마다 button mapping, score&count 초기화
    public void initialize(){
        Button btn0 = (Button) findViewById(R.id.button);
        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);
        Button btn6 = (Button) findViewById(R.id.button6);
        Button btn7 = (Button) findViewById(R.id.button7);
        Button btn8 = (Button) findViewById(R.id.button8);
        buttons = new ArrayList<>(); //이전 화면 buttons 비워야함
        buttons.add(btn0);
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        score=0;
        count=0;
    }

    public void initGame(List<Button> buttons ){
        updateScoreCount();
        Collections.shuffle(pool);
        List<Integer> problemSet = pool.subList(0, NUM_BUTTONS);

        //generate a problem set and map this to a button
        for(int i=0; i<problemSet.size(); i++){
            int n = problemSet.get(i);
            buttons.get(i).setText(String.valueOf(n));
        }

        //set a location of answer
        int answerIndex = new Random().nextInt(9); // 0<= n <= 8


        //generate an answer
        int[] answerSet = generateAnswer();
        //prevent multiple answers
        while(problemSet.contains(answerSet[2])){
            answerSet = generateAnswer();
        }
        int leftInt = answerSet[0];
        int rightInt = answerSet[1];
        int answer = answerSet[2];


        //map answer to a button
        buttons.get(answerIndex).setText(""+answer);
        TextView tv_left = (TextView) findViewById(R.id.leftInt);
        TextView tv_right = (TextView) findViewById(R.id.rightInt);
        tv_left.setText("" + leftInt);
        tv_right.setText("" + rightInt);

        //set listener to buttons
        setListener(buttons, answerIndex);
    }

    public int[] generateAnswer(){
        int leftInt = new Random().nextInt(9) + 1; // 1<= n <= 9
        int rightInt = new Random().nextInt(9) + 1; // 1<= n <= 9
        int answer = leftInt * rightInt;
        int[] answer_set = {leftInt, rightInt, answer};
        return answer_set;
    }



    public void setListener(List<Button> buttons, int answerIndex) {
        //오답 listener
        for ( Button btn:buttons ) {
            btn.setOnClickListener(null); //listener 다 떼어냄
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    initGame(GameActivity.buttons);
                }
            });
        }

        //정답 listener
        Button btn_answer = buttons.get(answerIndex);
        btn_answer.setOnClickListener(null);
        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                count++;
                initGame(GameActivity.buttons);
            }
        });
    }

    public void updateScoreCount(){
        TextView score_count = (TextView) findViewById(R.id.score_count);
        score_count.setText(score+"/"+count);
    }

    //구구단 풀 생성
    public List<Integer> generateAnsPool(){
        List<Integer> pool = new ArrayList();
        for(int i=1;i<=9;i++){
            for(int j=1;j<=9;j++){
                if(!pool.contains(i*j)){
                    pool.add(i*j);
                }
            }
        }
        return pool;
    }

    //타이머
    private class MyTimerTask extends TimerTask {
        private int seconds = 3;

        @Override
        public void run() {
            if( --seconds < 0 ) {
                timer.cancel();
                //startActivity();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent( GameActivity.this, ResultActivity.class );
                        intent.putExtra( "data-score", score );
                        intent.putExtra( "data-count", count );
                        startActivity( intent );
                        finish();
                    }
                });
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
