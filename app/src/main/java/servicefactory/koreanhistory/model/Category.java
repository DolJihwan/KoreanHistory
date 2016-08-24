package servicefactory.koreanhistory.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by JihwanHome on 2016-08-04.
 */
public class Category implements Serializable {

    public static final String TAG = "Category";

    private final String categoryMajor;
    private final String categoryMinor;
    private String categoryTheme;
    private String categoryQuiz;
    private int qCount;

    public Category(@NonNull String cMajor) {
        categoryMajor = cMajor;
        categoryMinor = null;
        categoryTheme = null;
        categoryQuiz = null;
        qCount = 0;
    }

    public Category(@NonNull String cMajor, String cMinor) {
        categoryMajor = cMajor;
        categoryMinor = cMinor;
        categoryTheme = null;
        categoryQuiz = null;
        qCount = 0;
    }

    public Category(@NonNull String cMajor, String cMinor, String cTheme, String cQuiz) {
        categoryMajor = cMajor;
        categoryMinor = cMinor;
        categoryTheme = cTheme;
        categoryQuiz = cQuiz;
        qCount = 0;
    }

    public Category(String categoryMajor, String categoryMinor, String categoryTheme, String categoryQuiz, int qCount) {
        this.categoryMajor = categoryMajor;
        this.categoryMinor = categoryMinor;
        this.categoryTheme = categoryTheme;
        this.categoryQuiz = categoryQuiz;
        this.qCount = qCount;
    }

    public String getCategoryMajor() {
        return categoryMajor;
    }

    public String getCategoryMinor() {
        return categoryMinor;
    }

    public String getCategoryTheme() {
        return categoryTheme;
    }

    public void setCategoryTheme(String categoryTheme) {
        this.categoryTheme = categoryTheme;
    }

    public String getCategoryQuiz() {
        return categoryQuiz;
    }

    public void setCategoryQuiz(String categoryQuiz) {
        this.categoryQuiz = categoryQuiz;
    }

    public int getqCount() {
        return qCount;
    }

    public void setqCount(int qCount) {
        this.qCount = qCount;
    }

    @Override
    public String toString() {
        return "{" +
                categoryMajor + '\'' +
                categoryMinor + '\'' +
                categoryTheme + '\'' +
                categoryQuiz + '\'' +
                qCount +
                '}';
    }
}
