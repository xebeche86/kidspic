package com.example.vadik.noyify;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    CardView cardView1,cardView2,cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        final Intent intent = new Intent(this,MainActivity.class);

        cardView1 = (CardView)findViewById(R.id.cv_easy);
        cardView2 = (CardView)findViewById(R.id.cv_medium);
        cardView3 = (CardView)findViewById(R.id.cv_hard);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Bucket",1);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Bucket",2);
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Bucket",3);
                startActivity(intent);
            }
        });




    }


}
