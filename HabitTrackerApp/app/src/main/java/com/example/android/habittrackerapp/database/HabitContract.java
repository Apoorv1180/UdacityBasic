package com.example.android.habittrackerapp.database;

import android.provider.BaseColumns;

/**
 * Created by Apoorv on 20-09-2017.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static abstract class HabitTrackerData implements BaseColumns {
        //Defining constants and table schema
        public static final String TABLE_NAME = "habit";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "habitname";
        public static final String COLUMN_HABIT_REPEAT = "habitrepeat";
        public static final String COLUMN_HABIT_DURATION = "duration";
        public static final String COLUMN_TIME_OF_OCCURENCE = "time";
        public static final String COLUMN_HABIT_DESCRIPTION = "description";
    }
}
