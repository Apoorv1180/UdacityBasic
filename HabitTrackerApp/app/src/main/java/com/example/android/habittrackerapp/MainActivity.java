package com.example.android.habittrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittrackerapp.database.HabitContract;
import com.example.android.habittrackerapp.database.HabitReaderDbHelper;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitReaderDbHelper habitReaderDbHelper = new HabitReaderDbHelper(this);

        //INSERTING RECORDS

        habitReaderDbHelper.insertMyHabit(
                "Play Fifa",
                4,
                "1 hr",
                "10 AM",
                "Play video games "
        );
        habitReaderDbHelper.insertMyHabit(
                "Read Books",
                2,
                "1 hr",
                "12 PM",
                "Read my fav books"
        );

        //READ RECORDS

        Cursor myCursor = habitReaderDbHelper.readMyHabit();

        try {
            int habitID = myCursor.getColumnIndex(HabitContract.HabitTrackerData._ID);
            int habitName = myCursor.getColumnIndex(HabitContract.HabitTrackerData.COLUMN_HABIT_NAME);
            int habitRepeat = myCursor.getColumnIndex(HabitContract.HabitTrackerData.COLUMN_HABIT_REPEAT);
            int habitDuration = myCursor.getColumnIndex(HabitContract.HabitTrackerData.COLUMN_HABIT_DURATION);
            int habitTimeOfOccurrence = myCursor.getColumnIndex(HabitContract.HabitTrackerData.COLUMN_TIME_OF_OCCURENCE);
            int habitDescription = myCursor.getColumnIndex(HabitContract.HabitTrackerData.COLUMN_HABIT_DESCRIPTION);

            //PRINT IN LOG TAG
            Log.i(LOG_TAG, "  ID  |  HABIT_NAME   |  HABIT_REPEAT  |  HABIT_DURATION  |  TIME_OF_OCCURRENCE | HABIT_DESCRIPTION");

            while (myCursor.moveToNext()) {
                //get Column data through index
                int id = myCursor.getInt(habitID);
                String hname = myCursor.getString(habitName);
                int hrepeat = myCursor.getInt(habitRepeat);
                String hduration = myCursor.getString(habitDuration);
                String htime = myCursor.getString(habitTimeOfOccurrence);
                String hdescription = myCursor.getString(habitDescription);
                //Print the data in logs fetched by cursor
                Log.i(LOG_TAG, id + "        " + hname + "        " + hrepeat + "        " + hduration + "       " + htime + "       " + hdescription);
            }
        } finally {
            //close the cursor
            myCursor.close();
        }
    }
}
