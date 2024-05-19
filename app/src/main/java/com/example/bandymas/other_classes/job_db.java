package com.example.bandymas.other_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class job_db extends SQLiteOpenHelper {

    private static final String DB_NAME = "job.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "job_forms";
    private static final String TITLE_CO = "title";
    private static final String DESCRIPTION_COL = "description";
    private static final String CATEGORY_COL = "category";
    private static final String CATEGORY_TOWN = "category_town";

    public job_db(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + TITLE_CO + " TEXT, " + DESCRIPTION_COL + " TEXT, "
                + CATEGORY_COL + " TEXT, "
                + CATEGORY_TOWN + " TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    //insertion of data:
    public void insertData(String title, String descriptionn, String catg_one, String catg_two)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(TITLE_CO, title);
        vals.put(DESCRIPTION_COL, descriptionn);
        vals.put(CATEGORY_COL, catg_one);
        vals.put(CATEGORY_TOWN, catg_two);
        db.insert(TABLE_NAME, null, vals);
        db.close();

    }
    //function that user can delete his job advertisement
    public void Delete(String title_pav)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "title=?", new String[]{title_pav});
        db.close();

    }
    //function so that user can change parameters of his job advertisement
    public void updateJobData()
    {

    }


}
