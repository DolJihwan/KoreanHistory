package servicefactory.koreanhistory.persistence;

/**
 * Created by JihwanHome on 2016-08-25.
 */
public class KoreanHistoryFinalVariable {
    private static int quizNum = 5;

    public static int getQuizNum() {
        return quizNum;
    }

    public static void setQuizNum(int quizNum) {
        KoreanHistoryFinalVariable.quizNum = quizNum;
    }
}
