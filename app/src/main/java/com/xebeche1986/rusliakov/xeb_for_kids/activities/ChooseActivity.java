package com.xebeche1986.rusliakov.xeb_for_kids.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.xebeche1986.rusliakov.xeb_for_kids.R;
import com.xebeche1986.rusliakov.xeb_for_kids.fragments.AbouFragment;

public class ChooseActivity extends AppCompatActivity {

    CardView cardView1,cardView2,cardView3;
    TextView tw_easy,tw_medium,tw_hard,about;

    InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        getSupportActionBar().hide();

//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        interstitialAd = new InterstitialAd(ChooseActivity.this);
//        interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
//        interstitialAd.loadAd(adRequest);
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//               displayAd();
//            }
//        });


        final Intent intent = new Intent(this,MainActivity.class);


        cardView1 = (CardView)findViewById(R.id.cv_easy);
        cardView2 = (CardView)findViewById(R.id.cv_medium);
        cardView3 = (CardView)findViewById(R.id.cv_hard);

        tw_easy = (TextView)findViewById(R.id.easy_tw);
        tw_medium = (TextView)findViewById(R.id.medium_tw);
        tw_hard = (TextView)findViewById(R.id.hard_tw);
        about= (TextView)findViewById(R.id.about);



        Typeface typeface = Typeface.createFromAsset(getAssets(), "reet.ttf");
        tw_easy.setTypeface(typeface);
        tw_medium.setTypeface(typeface);
        tw_hard.setTypeface(typeface);
        about.setTypeface(typeface);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                AbouFragment fragment = new AbouFragment();
                fragment.show(fragmentManager,"dsf");

            }
        });


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
public void displayAd(){
    if (interstitialAd.isLoaded()){
        interstitialAd.show();
    }
}

}
