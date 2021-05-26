package com.health.needleatm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;

public class RewardActivity extends AppCompatActivity {

    CardView rew_syrn;
    CardView rew_choco;
    CardView rew_con;

//    MaterialButton cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        rew_syrn = findViewById(R.id.reward_syrn);
        rew_choco = findViewById(R.id.reward_chocolate);
        rew_con = findViewById(R.id.reward_condom);
//        cont = findViewById(R.id.reward_cont);

        rew_syrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardActivity.this, RatingActivity.class);
                startActivity(intent);
            }
        });

    }
}