package com.example.vadik.noyify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerAdapter  recyclerAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager  layoutManager;
    SQLiteDatabase database;
    DBHelper dbHelper;
    String []diff, names,img_mark;
    int [] images;
    Intent incomingIntent;
    int difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        R.drawable.zast_10,R.drawable.zast_19,R.drawable.zast_17,R.drawable.zast_7,R.drawable.na_dereve_zast,R.drawable.zast_4,R.drawable.zast_22};
                img_mark = getResources().getStringArray(R.array.image_mark_recycler_c);
                recyclerAdapter  =  new RecyclerAdapter(this,names,images,diff,img_mark);
                break;
        }

        recyclerView.setAdapter(recyclerAdapter);
        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();


    }

}


//    Timer timer = new Timer();
//timer.schedule(new TimerTask() {
//
//public void run() {
//
//        //here you can start your Activity B.
//        // startActivityForResult(new Intent(ActivityB.this,ActivityC.class));
//
//
//        }
//
//        }, 3000);


