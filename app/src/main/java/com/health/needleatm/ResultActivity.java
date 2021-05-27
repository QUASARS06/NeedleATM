package com.health.needleatm;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class ResultActivity extends AppCompatActivity {

    int points = 9;

    CircularProgressIndicator scoreProgress;
    TextView scoreValue;
    TextView scoreTitleText;

    TextView scoreMssg;

    MaterialButton rewBtn;
    MaterialButton rewQuiz;
    MaterialButton rewCont;

    String pass = "Congratulations! You have won the quiz. We would like to gift you a reward for learning with us";
    String fail = "Sorry! Unfortunately your score falls below ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTitleText = findViewById(R.id.res_scoretext);

        scoreProgress = findViewById(R.id.res_scoreProgress);
        scoreValue = findViewById(R.id.res_scoreValue);
        scoreMssg = findViewById(R.id.res_mssg);
        rewBtn = findViewById(R.id.res_collectReward);
        rewQuiz = findViewById(R.id.res_takeQuiz);
        rewCont = findViewById(R.id.res_continue);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("points")) {
            points = (int) bundle.getInt("points");
        }

        scoreTitleText.setText("You scored - "+points+"/15");

        double percent = (points * 100.0) / 15;
        fail += (int)percent+"% so I cannot gift you for learning with us. You may reattempt the quiz to get a better score.";

        ValueAnimator animator = ValueAnimator.ofInt(0, (int) percent);
        animator.setDuration(750);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                scoreProgress.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
                scoreValue.setText(animation.getAnimatedValue().toString() + "%");
            }
        });

        if(percent>50){
            scoreMssg.setText(pass);
            rewBtn.setVisibility(View.VISIBLE);
            rewQuiz.setVisibility(View.GONE);
            rewCont.setVisibility(View.GONE);

            scoreProgress.setIndicatorColor(getResources().getColor(R.color.green));
//            scoreMssg.setTextColor(getResources().getColor(R.color.green));
        } else{
            scoreMssg.setText(fail);
            rewBtn.setVisibility(View.GONE);
            rewQuiz.setVisibility(View.VISIBLE);
            rewCont.setVisibility(View.VISIBLE);

            scoreProgress.setIndicatorColor(getResources().getColor(R.color.red));
        }
        animator.start();

        rewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, RewardActivity.class);
                startActivity(intent);
            }
        });

        rewCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, RatingActivity.class);
                startActivity(intent);
            }
        });

        rewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}