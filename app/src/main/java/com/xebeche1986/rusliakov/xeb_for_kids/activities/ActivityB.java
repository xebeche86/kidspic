package com.xebeche1986.rusliakov.xeb_for_kids.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.xebeche1986.rusliakov.xeb_for_kids.R;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityB extends Activity {
    ImageView imageView;
    int duration;
  //  private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_b);

//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        interstitialAd = new InterstitialAd(ActivityB.this);
//        interstitialAd.setAdUnitId(getString(R.string.Intersitial_Ad));
//        interstitialAd.loadAd(adRequest);
//


       imageView = (ImageView) findViewById(R.id.image_actB);

        Intent intent = getIntent();
        final String img_mark= intent.getStringExtra("mark");
        final String difficulty = intent.getStringExtra("difficulty");
        switch (img_mark){
            case "a1":
                setDrawableIV(R.drawable.img_26);
                break;
            case "a2":
               setDrawableIV(R.drawable.img_15);
                break;
            case "a3":
                setDrawableIV(R.drawable.forrest_animals2);
                break;
            case "a4":
                setDrawableIV(R.drawable.kids);
                break;
            case "a5":
                setDrawableIV(R.drawable.img_18);
                break;
            case "a6":
               setDrawableIV(R.drawable.img_20);
                break;
            case "a7":
                setDrawableIV(R.drawable.vesna);
                break;
            case "a8":
                setDrawableIV(R.drawable.ribak_);
                break;
            case "a9":
                setDrawableIV(R.drawable.peizazh);
                break;
            case "a10":
                setDrawableIV(R.drawable.dino_yayco);
                break;
            case "a11":
                setDrawableIV(R.drawable.img_27);
                break;
            case "a12":
                setDrawableIV(R.drawable.img_8);
                break;
            case "a13":
                setDrawableIV(R.drawable.img_23);
                break;
            case "a14":
                setDrawableIV(R.drawable.img_6);
                break;
            case "a15":
                setDrawableIV(R.drawable.img_5);
                break;
            case "a16":
                setDrawableIV(R.drawable.podarki_);
                break;
            case "a17":
                setDrawableIV(R.drawable.pic_2);
                break;
            case "a18":
                setDrawableIV(R.drawable.pirat1);
                break;
            case "a19":
                setDrawableIV(R.drawable.musicants_);
                break;
            case "a20":
                setDrawableIV(R.drawable.animals_for1);
                break;
            case "a21":
                setDrawableIV(R.drawable.img_16);
                break;
            case "a22":
                setDrawableIV(R.drawable.img_13);
                break;
            case "a23":
                setDrawableIV(R.drawable.img_12);
                break;
            case "a24":
                setDrawableIV(R.drawable.img_11);
                break;
            case "a25":
                setDrawableIV(R.drawable.img_19);
                break;
            case "a26":
                setDrawableIV(R.drawable.img_17);
                break;
            case "a27":
                setDrawableIV(R.drawable.img_7);
                break;
            case "a28":
                setDrawableIV(R.drawable.na_dereve);
                break;
            case "a29":
                setDrawableIV(R.drawable.img_22);
                break;

        }
        if (difficulty.equals("a")) {
            duration = 10000;

        } else if (difficulty.equals("b")) {
            duration = 8000;
        }
        else if (difficulty.equals("c")) {
            duration = 6500;

        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Intent intent1 = new Intent(ActivityB.this,ActivityC.class);
                intent1.putExtra("mark",img_mark);
                startActivity(intent1);

            }
        },duration);


    }


    private void setDrawableIV(int drawableIV){
       Bitmap mbitmap = ((BitmapDrawable)ContextCompat.getDrawable(this,drawableIV)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);
        imageView.setImageBitmap(imageRounded);

    }
//    public void displayAd() {
//        if (interstitialAd.isLoaded()) {
//            interstitialAd.show();
//        }
//    }


}

