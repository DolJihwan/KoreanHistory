package servicefactory.koreanhistory.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import servicefactory.koreanhistory.persistence.DatabaseAssetHelper;


/**
 * Created by JihwanHome on 2016-08-06.
 */
public class QuizHandler {
    private DatabaseAssetHelper openHelper;
    private SQLiteDatabase database;
    private static QuizHandler quizInstance;

    // database init.
    private QuizHandler(Context context){
        this.openHelper = new DatabaseAssetHelper(context);
    }

    /**
     * Return a singleton instance of QuizHandler.
     *
     * @param context the Context
     * @return the instance of QuizHandler
     */
    public static QuizHandler getInstance(Context context) {
        if (quizInstance == null) {
            quizInstance = new QuizHandler(context);
        }
        return quizInstance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    // database close.
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public Cursor getTotalCategory(){
        String query = "select category_major, category_minor from quiz group by category_minor order by _id";

        return database.rawQuery(query, null);
    }

    public Cursor createCategoryTheme(String categoryMinor) {
        String query = "select * from quiz where category_minor = '" + categoryMinor + "' order by random() limit 1";

        return database.rawQuery(query, null);
    }

    public Cursor createCategoryQuiz(String categoryTheme){
        String query = "select * from quiz where category_theme = '" + categoryTheme + "' order by random() limit 1";

        return database.rawQuery(query, null);
    }

    public Cursor extractQuiz(String categoryMinor, String categoryTheme, String categoryQuiz) {
        String query = "select * from quiz where category_minor = '" + categoryMinor + "' and category_theme = '" + categoryTheme + "' and category_quiz = '" + categoryQuiz + "' order by random() limit 4";

        return database.rawQuery(query, null);
    }

    public Cursor extractQuizFinal(int quizNo, int iQuizNo1, int iQuizNo2, int iQuizNo3) {
        String query = "select word, contents as cAnswer," +
                "(select contents from quiz where _id = '" + iQuizNo1 + "') as icAnswer1," +
                "(select contents from quiz where _id = '" + iQuizNo2 + "') as icAnswer2," +
                "(select contents from quiz where _id = '" + iQuizNo3 + "') as icAnswer3 " +
                "from quiz " +
                "where _id = '" + quizNo + "'";

        return database.rawQuery(query, null);
    }

    public Cursor createReviewTotal(String mDatetime) {
        String query = "select * from quiz where";
        return database.rawQuery(query, null);
    }
}
