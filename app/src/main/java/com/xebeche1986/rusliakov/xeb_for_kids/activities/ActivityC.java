package com.xebeche1986.rusliakov.xeb_for_kids.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.xebeche1986.rusliakov.xeb_for_kids.support.DBHelper;
import com.xebeche1986.rusliakov.xeb_for_kids.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityC extends Activity {
    String pic_name;
    TextView question_tv,tw1,tw2,tw3,tw4,tw5,tw_score;

    Button button1,button2,button3,button4;
    Cursor cursor;
    ArrayList<Button> buttonArrayList;
    SQLiteDatabase database;
    DBHelper dbHelper;
    ArrayList<String>  question_, corr_answ, incor_ans1,incor_ans2,incor_ans3;
    ImageView iw1,iw2,iw3,iw4,iw5;
    ArrayList<ImageView> imageViewArrayList;
    int i=0;
    private InterstitialAd interstitialAd;
    int duration=2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_c);
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        interstitialAd = new InterstitialAd(ActivityC.this);
//        interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
//        interstitialAd.loadAd(adRequest);
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                displayAd();
//            }
//        });




        button1 = (Button) findViewById(R.id.ans_btn1);
        button2 = (Button) findViewById(R.id.ans_btn2);
        button3 = (Button) findViewById(R.id.ans_btn3);
        button4 = (Button) findViewById(R.id.ans_btn4);

        iw1 = (ImageView)findViewById(R.id.iw_1);
        iw2 = (ImageView)findViewById(R.id.iw_2);
        iw3 = (ImageView)findViewById(R.id.iw_3);
        iw4 = (ImageView)findViewById(R.id.iw_4);
        iw5 = (ImageView)findViewById(R.id.iw_5);

        tw1 = (TextView)findViewById(R.id.tw1);
        tw2 = (TextView)findViewById(R.id.tw2);
        tw3 = (TextView)findViewById(R.id.tw3);
        tw4 = (TextView)findViewById(R.id.tw4);
        tw5 = (TextView)findViewById(R.id.tw5);
        question_tv = (TextView) findViewById(R.id.textView);

        tw_score= (TextView)findViewById(R.id.tw_score);


        imageViewArrayList = new ArrayList<>();
        imageViewArrayList.add(iw1);
        imageViewArrayList.add(iw2);
        imageViewArrayList.add(iw3);
        imageViewArrayList.add(iw4);
        imageViewArrayList.add(iw5);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "journ.ttf");

        tw1.setTypeface(typeface);
        tw2.setTypeface(typeface);
        tw3.setTypeface(typeface);
        tw4.setTypeface(typeface);
        tw5.setTypeface(typeface);
        tw_score.setTypeface(typeface);
        question_tv.setTypeface(typeface);

        button1.setTypeface(typeface);
        button2.setTypeface(typeface);
        button3.setTypeface(typeface);
        button4.setTypeface(typeface);

        Intent intent = getIntent();
        pic_name = intent.getStringExtra("mark");

        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();

        String where_ = "picture_name = ?";
        String[] where_param = {pic_name};
        cursor = database.query("my_table1", null, where_, where_param, null, null, null);

        cursor.moveToFirst();
        question_ = new ArrayList<>();
        corr_answ = new ArrayList<>();
        incor_ans1 = new ArrayList<>();
        incor_ans2 = new ArrayList<>();
        incor_ans3 = new ArrayList<>();

        buttonArrayList = new ArrayList<>();
        buttonArrayList.add(button1);
        buttonArrayList.add(button2);
        buttonArrayList.add(button3);
        buttonArrayList.add(button4);

        while (!cursor.isAfterLast()) {
            question_.add(cursor.getString(cursor.getColumnIndex(DBHelper._QUESTION)));
            corr_answ.add(cursor.getString(cursor.getColumnIndex(DBHelper.CORRECT_ANSWER)));
            incor_ans1.add(cursor.getString(cursor.getColumnIndex(DBHelper.INCORRECT_ANSWER_1)));
            incor_ans2.add(cursor.getString(cursor.getColumnIndex(DBHelper.INCORRECT_ANSWER_2)));
            incor_ans3.add(cursor.getString(cursor.getColumnIndex(DBHelper.INCORRECT_ANSWER_3)));
            cursor.moveToNext();
        }
        answersSet(i);

        final MediaPlayer correct_sound = MediaPlayer.create(this,R.raw.positive);
        final MediaPlayer incorrect_sound = MediaPlayer.create(this,R.raw.negative);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button1.getText()==corr_answ.get(i)){
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                   correct_sound.start();
                }
                else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                    incorrect_sound.start();
                }

                if(i!=question_.size()-1){
                    i++;
                    answersSet(i);

                }
                else {
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd = new InterstitialAd(ActivityC.this);
                    interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
                    interstitialAd.loadAd(adRequest);
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            displayAd();
                        }
                    });

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            finish();
                        }
                    },duration);

                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.getText()==corr_answ.get(i)){
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                    correct_sound.start();
                }

                else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                    incorrect_sound.start();

                }

                if(i!=question_.size()-1){
                    i++;
                    answersSet(i);

                }
                else {
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd = new InterstitialAd(ActivityC.this);
                    interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
                    interstitialAd.loadAd(adRequest);
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            displayAd();
                        }
                    });

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            finish();
                        }
                    },duration);

                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button3.getText()==corr_answ.get(i)){
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                    correct_sound.start();
                }
                else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                    incorrect_sound.start();
                }

                if(i!=question_.size()-1){
                    i++;
                    answersSet(i);

                }
                else {

                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd = new InterstitialAd(ActivityC.this);
                    interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
                    interstitialAd.loadAd(adRequest);
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            displayAd();
                        }
                    });

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            finish();
                        }
                    },duration);

                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button4.getText() == corr_answ.get(i)) {
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                    correct_sound.start();
                } else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                    incorrect_sound.start();
                }

                if (i != question_.size()-1 ) {
                    i++;
                    answersSet(i);

                } else {

                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd = new InterstitialAd(ActivityC.this);
                    interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
                    interstitialAd.loadAd(adRequest);
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            displayAd();
                        }
                    });

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            finish();
                        }
                    },duration);

                }
            }
        });
    }

    private void answersSet(int i){

        if (incor_ans3.get(i).equals("ttt") & incor_ans2.get(i).equals("ttt")){
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
            buttonArrayList = new ArrayList<>();
            buttonArrayList.add(button3);
        buttonArrayList.add(button4);
        Collections.shuffle(buttonArrayList);
        question_tv.setText(question_.get(i));
        buttonArrayList.get(0).setText(corr_answ.get(i));
        buttonArrayList.get(1).setText(incor_ans1.get(i));
        }
        else if(incor_ans3.get(i).equals("ttt")) {
        button2.setVisibility(View.VISIBLE);
        button1.setVisibility(View.INVISIBLE);
            buttonArrayList = new ArrayList<>();
            buttonArrayList.add(button3);
            buttonArrayList.add(button4);
            buttonArrayList.add(button2);
        Collections.shuffle(buttonArrayList);
        question_tv.setText(question_.get(i));
        buttonArrayList.get(0).setText(corr_answ.get(i));
        buttonArrayList.get(1).setText(incor_ans1.get(i));
        buttonArrayList.get(2).setText(incor_ans2.get(i));

        } else {
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
            buttonArrayList = new ArrayList<>();
            buttonArrayList.add(button1);
        buttonArrayList.add(button2);
        buttonArrayList.add(button3);
        buttonArrayList.add(button4);
        Collections.shuffle(buttonArrayList);
        question_tv.setText(question_.get(i));
        buttonArrayList.get(0).setText(corr_answ.get(i));
        buttonArrayList.get(1).setText(incor_ans1.get(i));
        buttonArrayList.get(2).setText(incor_ans2.get(i));
        buttonArrayList.get(3).setText(incor_ans3.get(i));
        }

        buttonArrayList.clear();
    }
    public void displayAd(){
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }}
    }






