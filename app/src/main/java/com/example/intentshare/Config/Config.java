package com.example.intentshare.Config;

import android.provider.BaseColumns;
public class Config{

    private Config(){}
    public static final class DataEntry implements BaseColumns {
   public static final String table_Name="TODO_LIST";
   public static final String column_Name="name";
   public static final String column_Amount="amount";
   public static final String column_TimeStamp="timestamp";




}
}