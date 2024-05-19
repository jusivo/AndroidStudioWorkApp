package com.example.bandymas.other_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class sql_db extends SQLiteOpenHelper
{
    private static final String DB_NAME = "Login.db";
    private static final String TABLE_NAME = "registration";
    private static final String USERNAME_COL = "username";
    private static final String NAME_COL = "name";
    private static final String SURNAME_COL = "surname";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    public sql_db(Context context) {
        super(context, "Login.db", null, 1);
    }
    //create database
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL("create Table users(EMAIL_COL TEXT primary key, password TEXT)");
        String query = "CREATE TABLE " + TABLE_NAME + " (" + NAME_COL + " TEXT, " + SURNAME_COL + " TEXT, " + USERNAME_COL + " TEXT, "
                + EMAIL_COL + " TEXT, "
                + PASSWORD_COL + " TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insertData(String name, String surname, String username, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(NAME_COL, name);
        vals.put(SURNAME_COL, surname);
        vals.put(USERNAME_COL, username);
        vals.put(EMAIL_COL, email);
        vals.put(PASSWORD_COL, password);
        db.insert(TABLE_NAME, null, vals);
        db.close();

    }
    //checking email if correct:
    public boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor -android dataThis interface provides random
        // read-write access
        // to the result set returned by a database query.
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COL + " = ?";
        Cursor curs = db.rawQuery(query, new String[]{email});
        if (curs.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //check password:
    public boolean checkPasswordEmail(String password, String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + EMAIL_COL + " = ?" +
                " AND " + PASSWORD_COL + " = ?";
        Cursor cur = db.rawQuery(query, new String[]{email, password});
        if (cur.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //jeigu user pamirso password:
    public void updatePassword(String email, String new_passw)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(PASSWORD_COL, new_passw);
        database.update(TABLE_NAME, vals, EMAIL_COL + " = ?", new String[]{email});
        database.close();
    }
    public void updateEmail(String email, String new_e)
    {
        updatingEmail(email, new_e);
    }
    //jei user nori pakeisti savo email:
    private void updatingEmail(String email, String newEmail)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(EMAIL_COL, newEmail);
        database.update(TABLE_NAME, vals, EMAIL_COL + " = ?", new String[]{email});
        database.close();

    }
    private void removeAccount(String email)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, EMAIL_COL + " = ?", new String[]{email});
        database.close();

    }
    public void removalProcess(String email)
    {
        removeAccount(email);

    }
    public void displayInformation(TextView email_, TextView passw_col)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor!=null)
        {
            int email_ind = cursor.getColumnIndex(EMAIL_COL);
            int passw_ind = cursor.getColumnIndex(PASSWORD_COL);
            if (email_ind != -1 && passw_ind!= -1)
            {
                String email = cursor.getString(email_ind);
                String passw = cursor.getString(passw_ind);
                email_.setText(email);
                passw_col.setText(passw);
            }
            cursor.close();
        }
        db.close();
    }
    public String getEmail()
    {
        String email = getEmailAddress();
        return email;
    }
    //method for getting the email of a user:
    private String getEmailAddress()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{EMAIL_COL}, null,
                null, null, null, null);
        String email_ = "";
        if (!cursor.equals(null) )
        {
            int emailIndex = cursor.getColumnIndex(EMAIL_COL);
            if (emailIndex != -1)
            {
                email_ = cursor.getString(emailIndex);

            }
        }
        return email_;
    }




}
