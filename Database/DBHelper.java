package com.example.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "employee_db";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "employee";

    private static final String EMP_CODE = "code";

    private static final String EMP_NAME = "name";

    private static final String GENDER = "gender";

    private static final String DEPARTMENT = "department";

    private static final String SALARY = "salary";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + EMP_CODE + " TEXT PRIMARY KEY, "
                + EMP_NAME + " TEXT,"
                + GENDER + " TEXT,"
                + DEPARTMENT + " TEXT,"
                + SALARY + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    public void insertEmployee(String code, String name, String gender, String dept, String salary) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EMP_CODE, code);
        values.put(EMP_NAME, name);
        values.put(GENDER, gender);
        values.put(DEPARTMENT, dept);
        values.put(SALARY, salary);

        db.insertOrThrow(TABLE_NAME, null, values);

//        db.close();
    }

    public void updateEmployee(String code, String name, String gender, String dept, String salary){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EMP_NAME, name);
        values.put(GENDER, gender);
        values.put(DEPARTMENT, dept);
        values.put(SALARY, salary);

        db.update(TABLE_NAME, values, "code = ?", new String[]{code});

//        db.close();
    }

    public void deleteEmployee(String code){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "code = ?", new String[]{code});

//        db.close();
    }

    public String[] retrieveEmployee(String code){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] vals = new String[4];
//        Cursor cursor = db.query( TABLE_NAME , new String [] { EMP_NAME , GENDER , DEPARTMENT , SALARY } , "code = ?" , new String [] {code} ,
//        null , null , null );
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE code = '"+code+"';",null);

        if(cursor.moveToNext()){
//            Toast.makeText(DBHelper.class, cursor.toString(), Toast.LENGTH_LONG).show();
            vals[0] = cursor.getString(1);
            vals[1] = cursor.getString(2);
            vals[2] = cursor.getString(3);
            vals[3] = cursor.getString(4);
            return vals;
        }
        else{
            return null;
        }

//        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
