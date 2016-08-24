package servicefactory.koreanhistory.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by JihwanHome on 2016-08-23.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wrong_answer.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i("DatabaseOpenHelper", "(onCreate1):: Create Table");
        String createTable = "CREATE TABLE wrong_answer (_id INTEGER PRIMARY KEY AUTOINCREMENT, datetime TEXT NOT NULL, category_major TEXT NOT NULL, category_minor TEXT NOT NULL, category_theme TEXT NOT NULL, category_quiz TEXT NOT NULL, quiz_no1 INTEGER NOT NULL, quiz_no2 INTEGER NOT NULL, quiz_no3 INTEGER NOT NULL, quiz_no4 INTEGER NOT NULL, wrong_yn INTEGER NOT NULL);";
        database.execSQL(createTable);
        Log.i("DatabaseOpenHelper", "(onCreate2):: Create Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
