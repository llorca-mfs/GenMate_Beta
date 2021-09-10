package com.mobdeve.s17.llorca.madrid.genmate_beta.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QrListDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FRIENDS = "users";
    public static final String FRIENDS_ID = "id";
    public static final String FRIENDS_UID = "uid";
    public static final String FRIENDS_IGN = "ign";

    private static final String CREATE_FRIEND_TABLE = "create table " + TABLE_FRIENDS +
            "("
            + FRIENDS_ID + " integer primary key autoincrement, "
            + FRIENDS_IGN + " text, "
            + FRIENDS_UID + " text ); ";


    public QrListDatabase(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        onCreate(db);
    }
}
