package servicefactory.koreanhistory.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import servicefactory.koreanhistory.persistence.DatabaseOpenHelper;

/**
 * Created by JihwanHome on 2016-08-20.
 */
public class WrongAnswerHandler {
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;
    private static WrongAnswerHandler wrongAnswerInstance;

    // database init.
    private WrongAnswerHandler(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    // database singleton instance
    public static WrongAnswerHandler getInstance(Context context) {
        if (wrongAnswerInstance == null) {
            wrongAnswerInstance = new WrongAnswerHandler(context);
        }
        return wrongAnswerInstance;
    }

    // database open.
    public void open() {
        Log.i("WrongAnswerHandler", "open");
        this.database = openHelper.getWritableDatabase();
    }

    // database close.
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void insertWrongQuizInfo(String dateTime, String category_major, String category_minor, String category_theme, String category_quiz, int q1, int q2, int q3, int q4, int wrong_yn) {
        String query = "insert into wrong_answer (datetime, category_major, category_minor, category_theme, category_quiz, quiz_no1, quiz_no2, quiz_no3, quiz_no4, wrong_yn) values ('" + dateTime + "', '" + category_major + "', '" + category_minor + "', '" + category_theme + "', '" + category_quiz + "', " + q1 + ", " + q2 + ", " + q3 + ", " + q4 + ", " + wrong_yn + ");";
        database.rawQuery(query, null);
    }

    public Cursor selectWrongQuizInfo() {
        String query = "select * from wrong_answer";
        return database.rawQuery(query, null);
    }

    public void selectCategoryCount() {
        // datetime
//        String query = "select * from wrong_answer group by datetime";
        // category
//        String query = "select * from wrong_answer where datetime = '2016년 08월 24일. 오전 05시 17분' group by category_major";
        // wrong answer count
        String query = "select count(*) from wrong_answer where datetime = '2016년 08월 24일. 오전 05시 17분' and wrong_yn = 0";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Log.i("Helloworld", "(Test) " + cursor.getString(0));
//            Log.i("Helloworld", "(Test) " +  cursor.getString(0) + ", "+ cursor.getString(1) + ", "+ cursor.getString(2) + ", "+ cursor.getString(3) + ", "+ cursor.getString(4) + ", "+ cursor.getString(5) + ", "+ cursor.getString(6) + ", "+ cursor.getString(7) + ", "+ cursor.getString(8));
        }
    }

    public Cursor selectDateList() {
        String query = "select * from wrong_answer group by datetime";
        return database.rawQuery(query, null);
    }

    public String selectCategoryCount(String dateTime) {
        String query = "select count(category_major) from (select * from wrong_answer where datetime = '" + dateTime + "' group by category_major)";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String totalQuizCount(String dateTime) {
        String query = "select count(*) from wrong_answer where datetime = '" + dateTime + "'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String wrongQuizCount(String dateTime) {
        String query = "select count(*) from wrong_answer where datetime = '" + dateTime + "' and wrong_yn = 0";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Cursor createReviewTotal(String mDatetime) {
        String query = "select * from wrong_answer where datetime = '" + mDatetime + "'";
        Cursor cursor = database.rawQuery(query, null);
//        while(cursor.moveToNext()){
//            Log.i("WrongAnswerHandler", "(createReviewTotal) " + cursor.getString(0) + ", "+ cursor.getString(1) + ", "+ cursor.getString(2) + ", "+ cursor.getString(3) + ", "+ cursor.getString(4) + ", "+ cursor.getString(5) + ", "+ cursor.getString(6) + ", "+ cursor.getString(7) + ", "+ cursor.getString(8));
//        }
        return cursor;
    }

    public Cursor createReviewIncorrect(String mDatetime) {
        String query = "select * from wrong_answer where datetime = '" + mDatetime + "' and wrong_yn = 0";
        Cursor cursor = database.rawQuery(query, null);
//        while(cursor.moveToNext()){
//            Log.i("WrongAnswerHandler", "(createReviewIncorrect) " + cursor.getString(0) + ", "+ cursor.getString(1) + ", "+ cursor.getString(2) + ", "+ cursor.getString(3) + ", "+ cursor.getString(4) + ", "+ cursor.getString(5) + ", "+ cursor.getString(6) + ", "+ cursor.getString(7) + ", "+ cursor.getString(8)+ ", "+ cursor.getString(9)+ ", "+ cursor.getString(10));
//        }
        return cursor;
    }

    public void deleteReview(String mDatetime) {
        Log.i("WrongHandler", "datetime test:: " + mDatetime);
        String query = "select _id from wrong_answer where datetime = '" + mDatetime + "';";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Log.i("HelloWorld", "Test:: " + cursor.getString(0));
            String deleteQuery = "delete from wrong_answer where _id = '" + cursor.getString(0) + "';";
            database.execSQL(deleteQuery);
//            Log.i("HelloWorld", "Test:: " + cursor.getString(0)+ ", "+ cursor.getString(1) + ", "+ cursor.getString(2) + ", "+ cursor.getString(3) + ", "+ cursor.getString(4) + ", "+ cursor.getString(5) + ", "+ cursor.getString(6) + ", "+ cursor.getString(7) + ", "+ cursor.getString(8)+ ", "+ cursor.getString(9)+ ", "+ cursor.getString(10));
        }
    }
}
