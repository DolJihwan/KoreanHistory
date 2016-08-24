package servicefactory.koreanhistory.model;

import java.io.Serializable;

/**
 * Created by JihwanHome on 2016-08-06.
 */
public class Quiz implements Serializable {

    public static final String TAG = "Quiz";

    private final int quizNum;
    private final String category_major;
    private final String category_minor;
    private final String category_theme;
    private final String category_quiz;
    private final int quizNo1;
    private final int quizNo2;
    private final int quizNo3;
    private final int quizNo4;
    private final String quizQuestion;
    private String quizAnswer1;
    private String quizAnswer2;
    private String quizAnswer3;
    private String quizAnswer4;

    public Quiz(int quizNum, String category_major, String category_minor, String category_theme, String category_quiz, int quizNo1, int quizNo2, int quizNo3, int quizNo4, String quizQuestion, String quizAnswer1, String quizAnswer2, String quizAnswer3, String quizAnswer4) {
        this.quizNum = quizNum;
        this.category_major = category_major;
        this.category_minor = category_minor;
        this.category_theme = category_theme;
        this.category_quiz = category_quiz;
        this.quizNo1 = quizNo1;
        this.quizNo2 = quizNo2;
        this.quizNo3 = quizNo3;
        this.quizNo4 = quizNo4;
        this.quizQuestion = quizQuestion;
        this.quizAnswer1 = quizAnswer1;
        this.quizAnswer2 = quizAnswer2;
        this.quizAnswer3 = quizAnswer3;
        this.quizAnswer4 = quizAnswer4;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getQuizNum() {
        return quizNum;
    }

    public String getCategory_major() {
        return category_major;
    }

    public String getCategory_minor() {
        return category_minor;
    }

    public String getCategory_theme() {
        return category_theme;
    }

    public String getCategory_quiz() {
        return category_quiz;
    }

    public int getQuizNo1() {
        return quizNo1;
    }

    public int getQuizNo2() {
        return quizNo2;
    }

    public int getQuizNo3() {
        return quizNo3;
    }

    public int getQuizNo4() {
        return quizNo4;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public String getQuizAnswer1() {
        return quizAnswer1;
    }

    public void setQuizAnswer1(String quizAnswer1) {
        this.quizAnswer1 = quizAnswer1;
    }

    public String getQuizAnswer2() {
        return quizAnswer2;
    }

    public void setQuizAnswer2(String quizAnswer2) {
        this.quizAnswer2 = quizAnswer2;
    }

    public String getQuizAnswer3() {
        return quizAnswer3;
    }

    public void setQuizAnswer3(String quizAnswer3) {
        this.quizAnswer3 = quizAnswer3;
    }

    public String getQuizAnswer4() {
        return quizAnswer4;
    }

    public void setQuizAnswer4(String quizAnswer4) {
        this.quizAnswer4 = quizAnswer4;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                quizNum +
                ", " + category_major + '\'' +
                ", " + category_minor + '\'' +
                ", " + category_theme + '\'' +
                ", " + category_quiz + '\'' +
                ", " + quizNo1 +
                ", " + quizNo2 +
                ", " + quizNo3 +
                ", " + quizNo4 +
                ", " + quizQuestion + '\'' +
                ", " + quizAnswer1 + '\'' +
                ", " + quizAnswer2 + '\'' +
                ", " + quizAnswer3 + '\'' +
                ", " + quizAnswer4 + '\'' +
                '}';
    }
}