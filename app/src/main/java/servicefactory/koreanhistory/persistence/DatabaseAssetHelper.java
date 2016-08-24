package servicefactory.koreanhistory.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by JihwanHome on 2016-08-09.
 */
public class DatabaseAssetHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "quiz";
    private static final int DATABASE_VERSION = 1;

    public DatabaseAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createTableWrongAnswer(SQLiteDatabase database) {
        String createTable = "CREATE TABLE wrong_answer (_id INTEGER PRIMARY KEY AUTOINCREMENT, datetime TEXT NOT NULL, category_major TEXT NOT NULL, category_minor TEXT NOT NULL, quiz_no1 INTEGER NOT NULL, quiz_no2 INTEGER NOT NULL, quiz_no3 INTEGER NOT NULL, quiz_no4 INTEGER NOT NULL, wrong_yn INTEGER NOT NULL);";
        database.execSQL(createTable);
        Log.i("DatabaseOpenHelper", "(onCreate2):: Create Table");
    }
}