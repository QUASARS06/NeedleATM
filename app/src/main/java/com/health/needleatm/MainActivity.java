package com.health.needleatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton startSurvey;

    ImageView imageView;
    ImageView rewardView;

    String img = "https://i.pinimg.com/originals/c1/1d/05/c11d05415f5ff082abf5155fa6d98e1f.gif";
    String img1 = "https://images.squarespace-cdn.com/content/v1/604f63346c8eba3fe7725278/1615917196172-5N9AT9KHLSBT1P07J6F5/ke17ZwdGBToddI8pDm48kPoswlzjSVMM-SxOp7CV59BZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZamWLI2zvYWH8K3-s_4yszcp2ryTI0HqTOaaUohrI8PI6FXy8c9PWtBlqAVlUS5izpdcIXDZqDYvprRqZ29Pw0o/rewards.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.main_imageView);
        rewardView = findViewById(R.id.main_rewardImageView);

        startSurvey = findViewById(R.id.main_startSurvey);
        startSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(i);
            }
        });
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));

        Glide.with(this).load(img).into(rewardView);
    }
}