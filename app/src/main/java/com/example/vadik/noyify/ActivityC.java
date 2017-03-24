package com.example.vadik.noyify;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityC extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

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



        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/fonty.ttf");
        tw1.setTypeface(typeface);
        tw2.setTypeface(typeface);
        tw3.setTypeface(typeface);
        tw4.setTypeface(typeface);
        tw5.setTypeface(typeface);
        tw_score.setTypeface(typeface);

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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button1.getText()==corr_answ.get(i)){
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                }
                else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                }

                if(i!=question_.size()-1){
                    i++;
                    answersSet(i);

                }
                else {
                    finish();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button2.getText()==corr_answ.get(i)){
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                }

                else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);

                }

                if(i!=question_.size()-1){
                    i++;
                    answersSet(i);

                }
                else {
                    finish();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button3.getText()==corr_answ.get(i)){
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                }
                else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                }

                if(i!=question_.size()-1){
                    i++;
                    answersSet(i);

                }
                else {
                    finish();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button4.getText() == corr_answ.get(i)) {
                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
                } else {
                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
                }

                if (i != question_.size()-1 ) {
                    i++;
                    answersSet(i);

                } else {
                    finish();
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


    }


//package com.example.vadik.noyify;
//
//        import android.content.Intent;
//        import android.database.Cursor;
//        import android.database.sqlite.SQLiteDatabase;
//        import android.graphics.Color;
//        import android.graphics.Typeface;
//        import android.provider.ContactsContract;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.ImageView;
//        import android.widget.RelativeLayout;
//        import android.widget.TextView;
//
//        import java.util.ArrayList;
//        import java.util.Collections;
//
//public class ActivityC extends AppCompatActivity {
//    String pic_name;
//    TextView question_tv,tw1,tw2,tw3,tw4,tw5,tw_score;
//
//    Button button1,button2,button3,button4;
//    Cursor cursor;
//    ArrayList<Button> buttonArrayList;
//    SQLiteDatabase database;
//    DBHelper dbHelper;
//    ArrayList<String>  question_, corr_answ, incor_ans1,incor_ans2,incor_ans3;
//    ImageView iw1,iw2,iw3,iw4,iw5;
//    ArrayList<ImageView> imageViewArrayList;
//    int i = 0;
//    int answerwNum;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_c);
//
//        button1 = (Button) findViewById(R.id.ans_btn1);
//        button2 = (Button) findViewById(R.id.ans_btn2);
//        button3 = (Button) findViewById(R.id.ans_btn3);
//        button4 = (Button) findViewById(R.id.ans_btn4);
//
//        iw1 = (ImageView)findViewById(R.id.iw_1);
//        iw2 = (ImageView)findViewById(R.id.iw_2);
//        iw3 = (ImageView)findViewById(R.id.iw_3);
//        iw4 = (ImageView)findViewById(R.id.iw_4);
//        iw5 = (ImageView)findViewById(R.id.iw_5);
//
//        tw1 = (TextView)findViewById(R.id.tw1);
//        tw2 = (TextView)findViewById(R.id.tw2);
//        tw3 = (TextView)findViewById(R.id.tw3);
//        tw4 = (TextView)findViewById(R.id.tw4);
//        tw5 = (TextView)findViewById(R.id.tw5);
//        question_tv = (TextView) findViewById(R.id.textView);
//
//        tw_score= (TextView)findViewById(R.id.tw_score);
//
//
//        imageViewArrayList = new ArrayList<>();
//        imageViewArrayList.add(iw1);
//        imageViewArrayList.add(iw2);
//        imageViewArrayList.add(iw3);
//        imageViewArrayList.add(iw4);
//        imageViewArrayList.add(iw5);
//
//
//        buttonArrayList = new ArrayList<>();
////        buttonArrayList.add(button1);
////        buttonArrayList.add(button2);
////        buttonArrayList.add(button3);
////        buttonArrayList.add(button4);
//
//        Collections.shuffle(buttonArrayList);
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/fonty.ttf");
//        tw1.setTypeface(typeface);
//        tw2.setTypeface(typeface);
//        tw3.setTypeface(typeface);
//        tw4.setTypeface(typeface);
//        tw5.setTypeface(typeface);
//        tw_score.setTypeface(typeface);
//        // question_tv.setTypeface(typeface);
//
//
//        Intent intent = getIntent();
//        pic_name = intent.getStringExtra("mark");
//
//        dbHelper = new DBHelper(this);
//        database = dbHelper.getReadableDatabase();
//
//        String where_ = "picture_name = ?";
//        String[] where_param = {pic_name};
//        cursor = database.query("my_table1", null, where_, where_param, null, null, null);
//
//        cursor.moveToFirst();
//        question_ = new ArrayList<>();
//        corr_answ = new ArrayList<>();
//        incor_ans1 = new ArrayList<>();
//        incor_ans2 = new ArrayList<>();
//        incor_ans3 = new ArrayList<>();
//
//        buttonArrayList.add(button3);
//        buttonArrayList.add(button4);
//        buttonArrayList.add(button2);
//        buttonArrayList.add(button1);
//        Collections.shuffle(buttonArrayList);
//
//        while (!cursor.isAfterLast()) {
//            question_.add(cursor.getString(cursor.getColumnIndex(DBHelper._QUESTION)));
//            corr_answ.add(cursor.getString(cursor.getColumnIndex(DBHelper.CORRECT_ANSWER)));
//            incor_ans1.add(cursor.getString(cursor.getColumnIndex(DBHelper.INCORRECT_ANSWER_1)));
//            incor_ans2.add(cursor.getString(cursor.getColumnIndex(DBHelper.INCORRECT_ANSWER_2)));
//            incor_ans3.add(cursor.getString(cursor.getColumnIndex(DBHelper.INCORRECT_ANSWER_3)));
//            cursor.moveToNext();
//        }
//
//        answersSet(i);
//
//
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (button1.getText()==corr_answ.get(i)){
//                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
//                }
//
//                else {
//                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
//
//                }
//
//                if(i!=question_.size()-1){
//                    i++;
//                    answersSet(i);
//
//                }
//
//                else {
//                    finish();
//                }
//
//
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (button2.getText()==corr_answ.get(i)){
//                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
//                }
//
//                else {
//                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
//
//                }
//
//                if(i!=question_.size()-1){
//                    i++;
//                    answersSet(i);
//
//
//                }
//
//                else {
//                    finish();
//                }
//
//
//            }
//        });
//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (button3.getText()==corr_answ.get(i)){
//                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
//                }
//
//                else {
//                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
//                }
//
//                if(i!=question_.size()-1){
//                    i++;
//                    answersSet(i);
//
//                }
//                else {
//                    finish();
//                }
//            }
//        });
//
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (button4.getText()==corr_answ.get(i)){
//                    imageViewArrayList.get(i).setImageResource(R.drawable.yyyy);
//                }
//                else {
//                    imageViewArrayList.get(i).setImageResource(R.drawable.ttt);
//                }
//
//                if(i!=question_.size()-1){
//                    i++;
//                    answersSet(i);
//
//                }
//                else {
//                    finish();
//                }
//            }
//        });
//    }
//
//    private void answersSet(int i){
//        Collections.shuffle(buttonArrayList);
//        question_tv.setText(question_.get(i));
//        buttonArrayList.get(0).setText(corr_answ.get(i));
//        buttonArrayList.get(1).setText(incor_ans1.get(i));
//        buttonArrayList.get(2).setText(incor_ans2.get(i));
//        buttonArrayList.get(3).setText(incor_ans3.get(i));
//        //Collections.shuffle(buttonArrayList);
//
//
//    }
//
//}





//if (incor_ans3.get(i).equals("ttt") & incor_ans2.get(i).equals("ttt")){
//        button1.setVisibility(View.GONE);
//        button2.setVisibility(View.GONE);
//        buttonArrayList.add(button3);
//        buttonArrayList.add(button4);
//        Collections.shuffle(buttonArrayList);
//        question_tv.setText(question_.get(i));
//        buttonArrayList.get(0).setText(corr_answ.get(i));
//        buttonArrayList.get(1).setText(incor_ans1.get(i));
//        button1.setText(incor_ans2.get(i));
//        button2.setText(incor_ans3.get(i));
//        }
//        if(incor_ans3.get(i).equals("ttt")) {
//        button2.setVisibility(View.VISIBLE);
//        button1.setVisibility(View.GONE);
//        Collections.shuffle(buttonArrayList);
//        buttonArrayList.add(button3);
//        buttonArrayList.add(button4);
//        buttonArrayList.add(button2);
//        question_tv.setText(question_.get(i));
//        buttonArrayList.get(0).setText(corr_answ.get(i));
//        buttonArrayList.get(1).setText(incor_ans1.get(i));
//        buttonArrayList.get(2).setText(incor_ans2.get(i));
//        button1.setText(incor_ans3.get(i));
//
//        } else {
//        button1.setVisibility(View.VISIBLE);
//        button2.setVisibility(View.VISIBLE);
//        buttonArrayList.add(button1);
//        buttonArrayList.add(button2);
//        buttonArrayList.add(button3);
//        buttonArrayList.add(button4);
//        Collections.shuffle(buttonArrayList);
//        question_tv.setText(question_.get(i));
//        buttonArrayList.get(0).setText(corr_answ.get(i));
//        buttonArrayList.get(1).setText(incor_ans1.get(i));
//        buttonArrayList.get(2).setText(incor_ans2.get(i));
//        buttonArrayList.get(3).setText(incor_ans3.get(i));
//        }
//


