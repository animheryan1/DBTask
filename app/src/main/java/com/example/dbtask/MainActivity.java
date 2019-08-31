package com.example.dbtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
    }

    public void saveToDB(View view) {
        SQLiteDatabase database = new SampleSQLiteDBHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleSQLiteDBHelper.PERSON_COLUMN_NAME, nameEditText.getText().toString());
        values.put(SampleSQLiteDBHelper.PERSON_COLUMN_EMAIL, emailEditText.getText().toString());
        database.delete(SampleSQLiteDBHelper.PERSON_TABLE_NAME, null, null);
        long newRowId = database.insert(SampleSQLiteDBHelper.PERSON_TABLE_NAME, null, values);

        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

    public void readFromDB(View view) {

        SQLiteDatabase database = new SampleSQLiteDBHelper(this).getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + SampleSQLiteDBHelper.PERSON_TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        String as = cursor.getString(cursor.getColumnIndex(SampleSQLiteDBHelper.PERSON_COLUMN_NAME));
        Log.d("AAA", as);
//        Log.d("AAA", cursor.getString(1));


    }
}
