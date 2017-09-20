package com.example.android.habittrackerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habittrackerapp.MainActivity;
import com.example.android.habittrackerapp.database.HabitContract.HabitTrackerData;

/**
 * Created by Apoorv on 20-09-2017.
 */

public class HabitReaderDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MainActivity.class.getName();
    //Defining database version and name for the database
    //Change the version if there is a upgrade or change in database schema
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habitdatabase.db";
    //CREATE TABLE STRING
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + HabitTrackerData.TABLE_NAME + "(" +
            HabitTrackerData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HabitTrackerData.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
            HabitTrackerData.COLUMN_HABIT_REPEAT + " INTEGER NOT NULL DEFAULT 1, " +
            HabitTrackerData.COLUMN_HABIT_DURATION + " TEXT NOT NULL DEFAULT 1, " +
            HabitTrackerData.COLUMN_TIME_OF_OCCURENCE + " TEXT NOT NULL DEFAULT 1, " +
            HabitTrackerData.COLUMN_HABIT_DESCRIPTION + " TEXT );";
    //DELETE TABLE STRING
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE" + HabitTrackerData.TABLE_NAME + ";";

    public HabitReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        Log.d(LOG_TAG, SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
        Log.d(LOG_TAG, SQL_DELETE_ENTRIES);
    }


    /**
     * Method to insert a record of habit in habitdatabase
     *
     * @param habitname   Short name for a habit
     * @param habitrepeat no. of time to repeat in week
     * @param duration    duration of habit in hours
     * @param time        remind the start of habit
     * @param description description of habit
     */
    public void insertMyHabit(String habitname, int habitrepeat, String duration, String time, String description) {
        //Creating an object of ContentValues

        ContentValues values = new ContentValues();
        values.put(HabitTrackerData.COLUMN_HABIT_NAME, habitname);
        values.put(HabitTrackerData.COLUMN_HABIT_REPEAT, habitrepeat);
        values.put(HabitTrackerData.COLUMN_HABIT_DURATION, duration);
        values.put(HabitTrackerData.COLUMN_TIME_OF_OCCURENCE, time);
        values.put(HabitTrackerData.COLUMN_HABIT_DESCRIPTION, description);

        //GET THE WRITABLE SQLITE DATABASE OBJECT

        SQLiteDatabase writableDatabase = getWritableDatabase();
        long resultStatus = writableDatabase.insert(HabitTrackerData.TABLE_NAME, null, values);
        if (resultStatus != -1) {
            Log.i(LOG_TAG, "Record Inserted Successfully");
        } else
            Log.i(LOG_TAG, "Insertion Failed");
    }

    /**
     * Method to read and print records present in database in the logs.
     *
     * @return Cursor object
     */

    public Cursor readMyHabit() {
        //GET THE READABLE SQLITE DATABASE OBJECT

        SQLiteDatabase readableDatabase = getReadableDatabase();

        String[] dataset = {
                HabitTrackerData._ID,
                HabitTrackerData.COLUMN_HABIT_NAME,
                HabitTrackerData.COLUMN_HABIT_REPEAT,
                HabitTrackerData.COLUMN_HABIT_DURATION,
                HabitTrackerData.COLUMN_TIME_OF_OCCURENCE,
                HabitTrackerData.COLUMN_HABIT_DESCRIPTION
        };

        Cursor myCursor = readableDatabase.query(HabitTrackerData.TABLE_NAME, dataset, null, null, null, null, null);
        return myCursor;
    }
}


