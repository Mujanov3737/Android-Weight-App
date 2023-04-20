package com.example.ahmetweighttrackingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDBWeights extends SQLiteOpenHelper {
    private static SQLiteDBWeights sqlManager;

    private static final String NAME_T = "WeightTable";
    private static final String COUNT_T = "Counter";
    private static final String ID_F = "id";
    private static final String WEIGHT_F = "weight";
    private static final String DATE_F = "date";
    private static final String DEL_F = "deleted";
    public static final String NAME_DB = "Weights.db";

    // Constructor for weights DB
    public SQLiteDBWeights(Context context) {
        super(context, NAME_DB, null, 1);
    }

    // Method to initialize DB
    public static SQLiteDBWeights initDB(Context context){
        if (sqlManager == null)
            sqlManager = new SQLiteDBWeights(context);
        return sqlManager;
    }

    // Creates table for weights with appropriate headers
    @Override
    public void onCreate(SQLiteDatabase DB) {
        StringBuilder query;
        query = new StringBuilder()
                .append("CREATE TABLE ")
                .append(NAME_T)
                .append("(")
                .append(COUNT_T)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_F)
                .append(" INT, ")
                .append(WEIGHT_F)
                .append(" TEXT, ")
                .append(DATE_F)
                .append(" TEXT, ")
                .append(DEL_F)
                .append(" TEXT)");
        DB.execSQL(query.toString());
    }

    // Inserts weight values to the database
    public void addWeightToDB(Weight weight) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_F, weight.getId());
        contentValues.put(WEIGHT_F, weight.getWeight());
        contentValues.put(DATE_F, weight.getDate());
        contentValues.put(DEL_F, weight.getDeleted());

        DB.insert(NAME_T, null, contentValues);
    }

    // Populates the static array list with database data
    public void popWeightList()
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        try (Cursor result = DB.rawQuery("SELECT * FROM " + NAME_T, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    String weightVal = result.getString(2);
                    String date = result.getString(3);
                    String deleted = result.getString(4);
                    Weight weight = new Weight(id,weightVal,date,deleted);
                    Weight.weightsList.add(weight);
                }
            }
        }
    }

    // Method to update the database when editing weights
    public void updateWeight(Weight weight)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_F, weight.getId());
        contentValues.put(WEIGHT_F, weight.getWeight());
        contentValues.put(DATE_F, weight.getDate());
        contentValues.put(DEL_F, weight.getDeleted());
        DB.update(NAME_T, contentValues, ID_F + " =? ", new String[]{String.valueOf(weight.getId())});
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
