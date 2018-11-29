package com.bignerdranch.android.reminder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "Reminder";
    private static final int DB_VER = 1;
    public  static  final String DB_TABLE =  "Duty";
    public static final String  DB_COLUMN = "DutyName";
    public DbHelper(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Duty (_id INTEGER PRIMARY KEY AUTOINCREMENT, DutyName TEXT NOT NULL);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String str = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE);
        db.execSQL(str);
        onCreate(db);
    }

    public void add_duty(String duty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN,duty);
        db.insert(DB_TABLE,null,values);
        db.close();
    }

    public void delete_duty(String duty){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,"DutyName = ?", new String[] {duty});
        db.close();
    }

    public ArrayList<String> getDutyList(){
        ArrayList<String> dutyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE,new String[]{DB_COLUMN},null,null,null,null,null);
        int index = cursor.getColumnIndex(DB_COLUMN);
        while(cursor.moveToNext()){
            dutyList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return dutyList;
    }
}
