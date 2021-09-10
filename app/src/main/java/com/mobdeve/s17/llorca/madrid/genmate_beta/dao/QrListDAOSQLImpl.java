package com.mobdeve.s17.llorca.madrid.genmate_beta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.mobdeve.s17.llorca.madrid.genmate_beta.model.QrFriend;

import java.util.ArrayList;

public class QrListDAOSQLImpl implements QrListDAO{

    private QrListDatabase qrListDatabase;
    private SQLiteDatabase database;

    public QrListDAOSQLImpl(Context context){
        qrListDatabase = new QrListDatabase(context);
    }

    @Override
    public long addQrFriend(QrFriend qrFriend) {
        ContentValues values = new ContentValues();

        values.put(QrListDatabase.FRIENDS_ID, qrFriend.getId());
        values.put(QrListDatabase.FRIENDS_IGN, qrFriend.getIgn());
        values.put(QrListDatabase.FRIENDS_UID, qrFriend.getUid());


        database = qrListDatabase.getWritableDatabase();

        long id = database.insert(QrListDatabase.TABLE_FRIENDS,null,values);

        if (database != null){
            qrListDatabase.close();
        }

        return id;
    }

    @Override
    public ArrayList<QrFriend> getQrFriends() {
        ArrayList<QrFriend> result = new ArrayList<>();
        String[] columns = {QrListDatabase.FRIENDS_ID, QrListDatabase.FRIENDS_IGN, QrListDatabase.FRIENDS_UID};

        database = qrListDatabase.getReadableDatabase();

        Cursor cursor = database.query(QrListDatabase.TABLE_FRIENDS, columns, null, null,null,null,null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            QrFriend temp = new QrFriend(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            result.add(temp);
            cursor.moveToNext();
        }

        if(cursor != null){
            cursor.close();
        }

        if (database != null){
            qrListDatabase.close();
        }

        return result;
    }

    @Override
    public QrFriend getQrFriend(int qrFriendId) {
        QrFriend qrFriend = null;

        String query = " SELECT * from " + qrListDatabase.TABLE_FRIENDS + " where " + QrListDatabase.FRIENDS_ID + " = " + qrFriendId;

        Cursor cursor = null;
        database = qrListDatabase.getReadableDatabase();

        try{
            cursor = database.rawQuery(query,null);
            cursor.moveToFirst();

            do{
                qrFriend.setId(cursor.getInt(cursor.getColumnIndex(QrListDatabase.FRIENDS_ID)));
                qrFriend.setIgn(cursor.getString(cursor.getColumnIndex(QrListDatabase.FRIENDS_IGN)));
                qrFriend.setUid(cursor.getString(cursor.getColumnIndex(QrListDatabase.FRIENDS_UID)));

            }while(cursor.moveToNext());
        }
        catch (SQLiteException e){
            return null;
        }

        if(cursor != null){
            cursor.close();
        }

        if (database != null){
            qrListDatabase.close();
        }

        return null;
    }
}
