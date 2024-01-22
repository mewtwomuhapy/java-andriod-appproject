package com.example.projectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_name = "RME.db";
    public  static  final String table_name ="RME_table";


    public DBHelper( @Nullable Context context) {
        super(context, DB_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE bmr_table (bmr_id INTEGER PRIMARY KEY AUTOINCREMENT, bmr_date DATETIME DEFAULT CURRENT_TIMESTAMP, bmr_result DOUBLE)");
        db.execSQL("CREATE TABLE rme_table (rme_idp INTEGER PRIMARY KEY AUTOINCREMENT, rme_id TEXT, rme_pw TEXT, rme_app TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  table_name);
        onCreate(db);

    }

    public Boolean AddData(String rme_id, String rme_pw, String rme_app ){

SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rme_id",rme_id);
        contentValues.put("rme_pw",rme_pw);
        contentValues.put("rme_app",rme_app);

        long result=DB.insert(table_name,null,contentValues);
        if(result==-1){
            return false;

        }else{
            return true;
        }

    }
    public Boolean updateuserdata( String rme_id , String rme_pw, String rme_app ){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rme_id",rme_id);
        contentValues.put("rme_pw",rme_pw);
        Cursor cursor = DB.rawQuery("select * from RME_table where rme_app =?", new String[]{rme_app});
        if(cursor.getCount()>0) {

            long result = DB.update(table_name, contentValues, "rme_app=?", new String[]{rme_app});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else{
            return  false;

        }

    }

    public Boolean deletedata(String rme_app ){

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from RME_table where rme_app =?", new String[]{rme_app});
        if(cursor.getCount()>0) {

            long result = DB.delete(table_name,  "rme_app=?", new String[]{rme_app});
            if (result == -1) {
                return false;

            } else {
                return true;
            }

        }else{
            return  false;

        }

    }

    public Cursor getdata ( ) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from RME_table", null);
        return cursor;


    }

    public void DeleteALL(){
        //public Integer DeleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, null, null);
        //Integer qty = db.delete(table_name, null, null);
        //return qty;
    }
}



