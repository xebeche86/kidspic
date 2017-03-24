package com.example.vadik.noyify;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.radius;
import static android.R.attr.width;
import static com.example.vadik.noyify.R.attr.height;

public class ActivityB extends AppCompatActivity {
    ImageView imageView;
    int duration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
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


}

