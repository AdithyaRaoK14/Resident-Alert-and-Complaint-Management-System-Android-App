package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "user_details.db";
    private static final String DATABASE_NAME = "user_details.db";
    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the UserDetails table
        db.execSQL(UserDetails.CREATE_TABLE);
        db.execSQL("create Table Admin(area_name TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the UserDetails table if it exists
        db.execSQL(UserDetails.DROP_TABLE);
        // Create a new UserDetails table
    }

    public class UserDetails {

        public static final String TABLE_NAME = "UserDetails";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_AREA = "area";
        public static final String COLUMN_PIN_CODE = "pin_code";
        public static final String COLUMN_BUILDING_NAME = "building_name";
        public static final String COLUMN_PASSWORD = "password";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_FIRST_NAME + " TEXT,"
                        + COLUMN_LAST_NAME + " TEXT,"
                        + COLUMN_PHONE + " TEXT,"
                        + COLUMN_EMAIL + " TEXT,"
                        + COLUMN_AREA + " TEXT,"
                        + COLUMN_PIN_CODE + " TEXT,"
                        + COLUMN_BUILDING_NAME + " TEXT,"
                        + COLUMN_PASSWORD + " TEXT"
                        + ")";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from UserDetails where email = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkAdminUsernamePassword(String adminUsername, String adminPassword){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
        SQLiteDatabase MyDB = this.getReadableDatabase();
//        String q = "Select * from UserDetails where email = " + adminUsername + " and password = " + adminPassword;
        Cursor cursor = MyDB.rawQuery("Select * from Admin where area_name = ? and password = ?", new String[] {adminUsername, adminPassword});
//        Cursor cursor = MyDB.rawQuery(q, null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public class UserQuery {



        public static final String TABLE_NAME = "UserQuery";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_PIN_CODE = "Pincode";
        public static final String COLUMN_QUERY = "Query";
        public static final String COLUMN_CATEGORY = "Category";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_PIN_CODE + " TEXT,"
                        + COLUMN_QUERY + "TEXT,"
                        + COLUMN_CATEGORY + " TEXT"
                        + ")";

        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
