package com.xebeche1986.rusliakov.xeb_for_kids.activities;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.xebeche1986.rusliakov.xeb_for_kids.support.DBHelper;
import com.xebeche1986.rusliakov.xeb_for_kids.R;
import com.xebeche1986.rusliakov.xeb_for_kids.support.RecyclerAdapter;
public class MainActivity extends Activity {

    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager  layoutManager;
    SQLiteDatabase database;
    DBHelper dbHelper;
    String []diff, names,img_mark;
    int [] images;
    Intent incomingIntent;
    int difficulty;
    private InterstitialAd interstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        AdRequest adRequest = new AdRequest.Builder().build();

        interstitialAd = new InterstitialAd(MainActivity.this);
        interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
        interstitialAd.loadAd(adRequest);





        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        incomingIntent = getIntent();

        difficulty = incomingIntent.getIntExtra("Bucket",0);
        switch (difficulty){
            case 1:
                diff = getResources().getStringArray(R.array.text_difficultyA);
                names = getResources().getStringArray(R.array.names_a);
                img_mark = getResources().getStringArray(R.array.image_mark_recycler_a);
                images = new int[]{R.drawable.zast_26,R.drawable.zast_15,R.drawable.forrest2_zast,R.drawable.kids,R.drawable.zast_18,R.drawable.zast_20,R.drawable.vesna_zast,
                        R.drawable.ribak_zast,R.drawable.peizazh_zast,R.drawable.dino_yayco_zast};
                recyclerAdapter  =  new RecyclerAdapter(this,names,images,diff,img_mark);
                break;
            case 2:
                diff = getResources().getStringArray(R.array.text_difficultyA);
                names = getResources().getStringArray(R.array.names_b);
                images = new int[]{R.drawable.zast_27,R.drawable.zast_8,R.drawable.zast_23,R.drawable.zast_6,
                        R.drawable.zast_5,R.drawable.podarki_zast,R.drawable.zast_2,R.drawable.pirat1_zast,R.drawable.musicants_zast,R.drawable.animals_for1_zast};
                img_mark = getResources().getStringArray(R.array.image_mark_recycler_b);
                recyclerAdapter  =  new RecyclerAdapter(this,names,images,diff,img_mark);
                break;
            case 3:
                diff = getResources().getStringArray(R.array.text_difficultyA);
                names = getResources().getStringArray(R.array.names_c);
                images = new int[]{R.drawable.zast16,R.drawable.zast_13,R.drawable.zast_12,R.drawable.zast11,
                        R.drawable.zast_19,R.drawable.zast_17,R.drawable.zast_7,R.drawable.na_dereve_zast,R.drawable.zast_22};
                img_mark = getResources().getStringArray(R.array.image_mark_recycler_c);
                recyclerAdapter  =  new RecyclerAdapter(this,names,images,diff,img_mark);
                break;
        }

        recyclerView.setAdapter(recyclerAdapter);
        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();




    }
    public void displayAd(){
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }



//private void setupWindowAnimation(){
//    Fade fade = new Fade();
//    fade.setDuration(1000);
//    getWindow().setEnterTransition(fade);
//}

}

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayAd();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayAd();
            }
        });

    }



}


