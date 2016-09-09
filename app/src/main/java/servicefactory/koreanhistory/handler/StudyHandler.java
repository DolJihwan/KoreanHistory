package servicefactory.koreanhistory.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import servicefactory.koreanhistory.persistence.DatabaseAssetHelper;
import servicefactory.koreanhistory.persistence.DatabaseOpenHelper;


/**
 * Created by leejonghyeog on 2016. 8. 17..
 */
public class StudyHandler {
    //    private DatabaseOpenHelper openHelper;
    private DatabaseAssetHelper openHelper;
    private SQLiteDatabase database;
    private static StudyHandler studyInstance;

    private StudyHandler(Context context) {
        this.openHelper = new DatabaseAssetHelper(context);
    }

    public static StudyHandler getInstance(Context context) {
        if (studyInstance == null) {
            studyInstance = new StudyHandler(context);
        }
        return studyInstance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    // database close.
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public Cursor getMajorCategory() {
        Log.i("HelloWorld", "Test hello - handler");

        String query = "select distinct category_major from quiz";
        return database.rawQuery(query, null);
    }

    public Cursor getMinorCategory(String majorCategory) {
        String query = "select distinct category_minor from quiz where category_major='" + majorCategory + "'";
        return database.rawQuery(query, null);
    }

    public Cursor getSelectedMajorCategory(String minorCategory) {
        String query = "select distinct category_major from quiz where category_minor='" + minorCategory + "'";
        return database.rawQuery(query, null);
    }

    public Cursor getSelectedCategoryTheme(String minorCategory) {
        String query = "select distinct category_theme from quiz where category_minor='" + minorCategory + "'";
        return database.rawQuery(query, null);
    }

    public Cursor getSelectedCategoryTitle(String categoryTheme) {
        String query = "select distinct category_title from quiz where category_theme='" + categoryTheme + "'";
        return database.rawQuery(query, null);
    }

    public Cursor getSelectedCategoryWord(String categoryTitle) {
        String query = "select distinct word from quiz where category_title='" + categoryTitle + "'";
        return database.rawQuery(query, null);
    }

    public Cursor getSelectedCategoryContents(String word) {
        String query = "select category_quiz, contents from quiz where word='" + word + "'";
        return database.rawQuery(query, null);
    }
}
