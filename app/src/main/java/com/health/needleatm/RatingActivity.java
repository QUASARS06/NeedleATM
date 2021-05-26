package com.health.needleatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView ratingTxt;

    ImageView cancel;
    ImageView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = findViewById(R.id.rating_bar);
        ratingTxt = findViewById(R.id.rating_text);

        cancel = findViewById(R.id.rating_cancel);
        check = findViewById(R.id.rating_check);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingTxt.setVisibility(View.VISIBLE);
                String val = "Thank You !! for rating us with "+rating+"/5 stars";
                ratingTxt.setText(val);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RatingActivity.this, AssistanceActivity.class);
                startActivity(i);
            }
        });


    }
}