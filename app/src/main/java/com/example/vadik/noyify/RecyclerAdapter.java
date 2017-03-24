package com.example.vadik.noyify;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    Context mContext;
    String [] _names,_difficulty,img_mark;
    int[] _images;


    public RecyclerAdapter(Context context, String[] names, int [] pics, String[] _difficulty, String[] img_mark){
        this._names=names;
        this._images=pics;
        this._difficulty = _difficulty;
        this.img_mark=img_mark;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
       // _names = mContext.getResources().getStringArray(R.array._names);
        holder.imageView.setImageResource(_images[position]);
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

                public MyHolder( View itemView) {
                    super(itemView);
                    mContext = itemView.getContext();

                    tw_pic = (TextView)itemView.findViewById(R.id.tw_difficulty);
            imageView = (ImageView) itemView.findViewById(R.id.recycler_image);
            textView = (TextView) itemView.findViewById(R.id.item_tv_name);
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

