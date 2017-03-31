package com.xebeche1986.rusliakov.xeb_for_kids.support;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xebeche1986.rusliakov.xeb_for_kids.R;


public  class DBHelper extends SQLiteOpenHelper {
    Context fContext;
    public static final String DATABASE_NAME = "my_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "my_table1";
    public static final String COL_ID = "_id";
    public static final String PICTURE_NAME  =  "picture_name";
    public static final  String _QUESTION = "_question";
    public static final String CORRECT_ANSWER = "_correct";
    public static final String INCORRECT_ANSWER_1= "incorrect_1";
    public static final String INCORRECT_ANSWER_2= "incorrect_2";
    public static final String INCORRECT_ANSWER_3= "incorrect_3";
    public static final String SQL_script= " CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  PICTURE_NAME + " TEXT ," + _QUESTION+ " TEXT ,"  + CORRECT_ANSWER + " TEXT ,"
            + INCORRECT_ANSWER_1 + " TEXT ," + INCORRECT_ANSWER_2 + " TEXT ,"+ INCORRECT_ANSWER_3 + " TEXT );";


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        fContext=context;
    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_script);

        ContentValues contentValues = new ContentValues();
        Resources resources = fContext.getResources();
        String [] pic_name = resources.getStringArray(R.array.image_mark_database);
        String [] question =  resources.getStringArray(R.array._question);
        String [] cor_answ = resources.getStringArray(R.array.correct_answ);
        String [] incor_1 = resources.getStringArray(R.array._incor1);
        String [] incor_2 = resources.getStringArray(R.array._incor2);
        String [] incor_3 = resources.getStringArray(R.array._incor3);

        int length = pic_name.length;

        for (int i =0;i<length;i++){

            contentValues.put(DBHelper.PICTURE_NAME,pic_name[i]);
            contentValues.put(DBHelper._QUESTION,question[i]);
            contentValues.put(DBHelper.CORRECT_ANSWER,cor_answ[i]);
            contentValues.put(DBHelper.INCORRECT_ANSWER_1,incor_1[i]);
            contentValues.put(DBHelper.INCORRECT_ANSWER_2,incor_2[i]);
            contentValues.put(DBHelper.INCORRECT_ANSWER_3,incor_3[i]);
            sqLiteDatabase.insert(DBHelper.TABLE_NAME,null,contentValues);

        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
