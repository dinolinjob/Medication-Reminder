package com.example.dinolin.MedicationReminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dinolin on 09-12-2017.
 */


public class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Patient.db";
    public static final String TABLE_NAME = "Patient_details";
    public static final String COL_1 = "SNO";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PATIENT ID";
    public static final String COL_4 = "DISEASES";
    public static final String COL_5 = "MEDICATION";


    public Databasehelper(Context context) {
        super(context,DATABASE_NAME,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(SNO INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PATIENTID TEXT,DISEASES TEXT,MEDICATION TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertdata(String name, String patient_id,String diseases,String medication){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,patient_id);
        contentValues.put(COL_4,diseases);
        contentValues.put(COL_5,medication);


        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return  false;
        else
            return true;
    }
}
