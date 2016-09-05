package servicefactory.koreanhistory.persistence;

/**
 * Created by JihwanHome on 2016-08-25.
 */
public class KoreanHistoryFinalVariable {
    private static int quizNum = 5;
    public static final int BG_PRIO = android.os.Process.THREAD_PRIORITY_BACKGROUND;
    public static final int RUNNABLE_DELAY_MS = 1000;

    public static int getQuizNum() {
        return quizNum;
    }

    public static void setQuizNum(int quizNum) {
        KoreanHistoryFinalVariable.quizNum = quizNum;
    }
}
