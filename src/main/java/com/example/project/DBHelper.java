package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "user_details.db";
    public DBHelper(Context context) {
        super(context, "user_details.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //MyDB.execSQL("create Table Admin(area_name TEXT, area_pincode TEXT primary key, password TEXT)");
//        MyDB.execSQL("CREATE TABLE \"Notification\" (\n" +
//                "\t\"pincode\"\tTEXT NOT NULL,\n" +
//                "\t\"notification_title\"\tTEXT NOT NULL,\n" +
//                "\t\"notification_details\"\tTEXT NOT NULL,\n" +
//                "\t\"ID\"\tINTEGER,\n" +
//                "\t\"category\"\tTEXT NOT NULL,\n" +
//                "\tPRIMARY KEY(\"ID\",\"pincode\")\n" +
//                ");");
    }

    public Integer i = 0;

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String area_name, String area_pincode, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("area_name", area_name);
        contentValues.put("area_pincode", area_pincode);
        contentValues.put("password", password);
        long result = MyDB.insert("Admin", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String area_pincode) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Admin where area_pincode = ?", new String[]{area_pincode});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean insertNotification(String pincode, String title, String details, Integer ID, String category){
        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Insert into Notification where Pincode = ? and Notification_title = ? ", new String[pincode, title]);
        ContentValues values = new ContentValues();
        values.put("Pincode", pincode);
        values.put("Notification_title", title);
        values.put("Notification_details", details);
//        values.put("ID", i++);
//        values.put("ID", ID);
        values.put("Category", category);

        MyDB.insert("Notification", null, values);
        return true;
    }

    public Boolean checkusernamepassword(String area_pincode, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Admin where area_pincode = ? and password = ?", new String[] {area_pincode,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public ArrayList<String> categoryWiseNotification(String category){
        ArrayList<String>list = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Notification where Category = ?", new String[]{category});

        if (cursor.moveToFirst()) {
            do {
                String pin = cursor.getString(0);
                String title = cursor.getString(1);
                String details = cursor.getString(2);
//                String category = cursor.getString(4);
                list.add(0, pin + " - " + title + " - " + details + " - " + category);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    public Cursor viewData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Notification", null);
//        System.out.println(cursor);
        return cursor;
    }

    public ArrayList<String> getAllNotifications() {
        ArrayList<String> namesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Notification", null, null, null, null, null, "ID");

        if (cursor.moveToFirst()) {
            do {
                String pin = cursor.getString(0);
                String title = cursor.getString(1);
                String details = cursor.getString(2);
                String category = cursor.getString(4);
                namesList.add(0, pin + " - " + title + " - " + details + " - " + category);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return namesList;
    }

//    public ArrayList<String> fetchQueries(String pin){
//        ArrayList<String> queryList = new ArrayList<>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query("UserQuery", null, null, null, null, null, "ID");
//
//        if (cursor.moveToFirst()) {
//            do {
////                String pin = cursor.getString(0);
//                String query = cursor.getString(1);
//                String category = cursor.getString(3);
//                queryList.add(0, pin + " - " + query + " - " + category);
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//
//        return queryList;
//    }

    public ArrayList<String> fetchQueries(String pin){
        ArrayList<String>list = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from UserQuery where Pincode = ?", new String[]{pin});

        if (cursor.moveToFirst()) {
            do {
//                String pin = cursor.getString(0);
                String query = cursor.getString(1);
                String category = cursor.getString(3);
                list.add(0, pin + " - " + query + " - " + category);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }


}