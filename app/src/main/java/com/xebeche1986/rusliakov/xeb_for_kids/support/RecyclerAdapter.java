package com.xebeche1986.rusliakov.xeb_for_kids.support;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xebeche1986.rusliakov.xeb_for_kids.R;
import com.xebeche1986.rusliakov.xeb_for_kids.activities.ActivityB;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    Context mContext;
    String [] _names,_difficulty,img_mark;
    int[] _images;

    public RecyclerAdapter(Context context, String[] names,int[] pics, String[] _difficulty, String[] img_mark){
        this._names=names;
        this._images=pics;
        this._difficulty = _difficulty;
        this.img_mark=img_mark;
      //this.mContext=context;
    }

//    public Bitmap roundingIMG(int posit){
//        Bitmap mbitmap = ((BitmapDrawable) ContextCompat.getDrawable(mContext,posit)).getBitmap();
//        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
//        Canvas canvas = new Canvas(imageRounded);
//        Paint mpaint = new Paint();
//        mpaint.setAntiAlias(true);
//        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//      //  canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);
//        canvas.drawRoundRect((new RectF(0,0,mbitmap.getWidth(),mbitmap.getHeight())),100,100,mpaint);
//        return imageRounded;
//    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
       holder.imageView.setImageResource(_images[position]);
      //  holder.imageView.setImageBitmap(roundingIMG(_images[position]));
        holder.textView.setText(_names[position]);
        holder.tw_pic.setText(_difficulty[position]);
        holder.twImgMark.setText(img_mark[position]);

    }

            @Override
            public int getItemCount() {
                return _names.length;
            }

            class MyHolder extends RecyclerView.ViewHolder {
                ImageView imageView;
                TextView textView,tw_pic,twImgMark;

                public MyHolder(View itemView) {
                    super(itemView);
                    mContext = itemView.getContext();

                    tw_pic = (TextView)itemView.findViewById(R.id.tw_difficulty);
            imageView = (ImageView) itemView.findViewById(R.id.recycler_image);
            textView = (TextView) itemView.findViewById(R.id.item_tv_name);
                    Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"rus.ttf");
                    textView.setTypeface(typeface);
                    twImgMark = (TextView)itemView.findViewById(R.id.tw_img_mark);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent = new Intent(mContext,ActivityB.class);
                            intent.putExtra("mark",twImgMark.getText().toString());
                            intent.putExtra("difficulty",tw_pic.getText().toString());
                            mContext.startActivity(intent);


                }
            });

        }
    }

}

