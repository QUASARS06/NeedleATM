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
                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(i);
            }
        });
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));

        Glide.with(this).load(img).into(rewardView);
    }
}