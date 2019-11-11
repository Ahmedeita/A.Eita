package com.example.intentshare.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String db_Name="TODOList.dp";
    public static final int db_Version=1;

    public DbHelper(@Nullable Context context) {
        super(context, db_Name, null, db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQueryCreate="CREATE TABLE "+ Config.DataEntry.table_Name+ " ("+
                Config.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Config.DataEntry.column_Name + " TEXT," +
                Config.DataEntry.column_TimeStamp + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                Config.DataEntry.column_Amount + " INTEGER);";
        db.execSQL(sqlQueryCreate);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+ Config.DataEntry.column_Name);
    }
}
