package servicefactory.koreanhistory.model;

/**
 * Created by JihwanHome on 2016-08-06.
 */
public class Word {

    public static final String TAG = "Word";

    private final int wordNum;
    private final String category_major;
    private final String category_minor;
    private final String category_theme;
    private final String category_quiz;
    private int wordNo1;
    private int wordNo2;
    private int wordNo3;
    private int wordNo4;

    public Word(int wordNum, String category_major, String category_minor, String category_theme, String category_quiz) {
        this.wordNum = wordNum;
        this.category_major = category_major;
        this.category_minor = category_minor;
        this.category_theme = category_theme;
        this.category_quiz = category_quiz;
    }

    public Word(int wordNum, String category_major, String category_minor, String category_theme, String category_quiz, int wordNo1, int wordNo2, int wordNo3, int wordNo4) {
        this.wordNum = wordNum;
        this.category_major = category_major;
        this.category_minor = category_minor;
        this.category_theme = category_theme;
        this.category_quiz = category_quiz;
        this.wordNo1 = wordNo1;
        this.wordNo2 = wordNo2;
        this.wordNo3 = wordNo3;
        this.wordNo4 = wordNo4;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getWordNum() {
        return wordNum;
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

    public int getWordNo1() {
        return wordNo1;
    }

    public void setWordNo1(int wordNo1) {
        this.wordNo1 = wordNo1;
    }

    public int getWordNo2() {
        return wordNo2;
    }

    public void setWordNo2(int wordNo2) {
        this.wordNo2 = wordNo2;
    }

    public int getWordNo3() {
        return wordNo3;
    }

    public void setWordNo3(int wordNo3) {
        this.wordNo3 = wordNo3;
    }

    public int getWordNo4() {
        return wordNo4;
    }

    public void setWordNo4(int wordNo4) {
        this.wordNo4 = wordNo4;
    }

    @Override
    public String toString() {
        return "Word{" +
                wordNum +
                ", " + category_major + '\'' +
                ", " + category_minor + '\'' +
                ", " + category_theme + '\'' +
                ", " + category_quiz + '\'' +
                ", " + wordNo1 +
                ", " + wordNo2 +
                ", " + wordNo3 +
                ", " + wordNo4 +
                '}';
    }
}
