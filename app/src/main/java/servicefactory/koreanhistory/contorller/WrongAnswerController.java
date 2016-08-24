package servicefactory.koreanhistory.contorller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import servicefactory.koreanhistory.date.DateInfo;
import servicefactory.koreanhistory.handler.WrongAnswerHandler;
import servicefactory.koreanhistory.model.Quiz;
import servicefactory.koreanhistory.model.Word;
import servicefactory.koreanhistory.model.WrongAnswerList_Category;

/**
 * Created by JihwanHome on 2016-08-20.
 */
public class WrongAnswerController {
    private WrongAnswerHandler wrongAnswerHandler;
    private DateInfo dateInfoInstance;

    private ArrayList<Quiz> quizArr = new ArrayList<>();

    public WrongAnswerController(Context context) {
        // db connect(quizHandler).
        wrongAnswerHandler = WrongAnswerHandler.getInstance(context);
        wrongAnswerHandler.open();

        if (dateInfoInstance == null) {
            dateInfoInstance = DateInfo.getDateInfoInstance();
        }
    }

    public void insertQuizInfo(ArrayList<Quiz> quizList, int[] selectAnswer, int[] correctAnswer) {
        Log.i("WrongAnswerController", "(QuizInfo) " + quizList.toString());
        Log.i("WrongAnswerController", "(SelAnswer) " + selectAnswer[0] + ", " + selectAnswer[1] + ", " + selectAnswer[2] + ", " + selectAnswer[3] + ", " + selectAnswer[4]);
        Log.i("WrongAnswerController", "(CorAnswer) " + correctAnswer[0] + ", " + correctAnswer[1] + ", " + correctAnswer[2] + ", " + correctAnswer[3] + ", " + correctAnswer[4]);
        Log.i("WrongAnswerController", "(DateTime) " + dateInfoInstance.dateTime());

        String dateTime = dateInfoInstance.dateTime();

        for (int i = 0; i < quizList.size(); i++) {
            int tmpNum = -1;
            if (selectAnswer[i] == correctAnswer[i]) {
                tmpNum = 1;
            } else {
                tmpNum = 0;
            }
            wrongAnswerHandler.insertWrongQuizInfo(dateTime, quizList.get(i).getCategory_major(), quizList.get(i).getCategory_minor(), quizList.get(i).getCategory_theme(), quizList.get(i).getCategory_quiz(), quizList.get(i).getQuizNo1(), quizList.get(i).getQuizNo2(), quizList.get(i).getQuizNo3(), quizList.get(i).getQuizNo4(), tmpNum);
            Log.i("WrongAnswerController", "(wrong_yn) " + dateTime + ", " + quizList.get(i).getCategory_major() + ", " + quizList.get(i).getCategory_minor() + ", " + quizList.get(i).getCategory_theme() + ", " + quizList.get(i).getCategory_quiz() + ", " + quizList.get(i).getQuizNo1() + ", " + quizList.get(i).getQuizNo2() + ", " + quizList.get(i).getQuizNo3() + ", " + quizList.get(i).getQuizNo4() + ", " + tmpNum);
        }
    }

    public ArrayList<WrongAnswerList_Category> selectWrongQuizInfo() {
        ArrayList<WrongAnswerList_Category> wrongAnswerList_category = new ArrayList();

        Cursor dateList = wrongAnswerHandler.selectDateList();
        while (dateList.moveToNext()) {
            String categoryString = "";
            String categoryCnt = wrongAnswerHandler.selectCategoryCount(dateList.getString(1));
            String totalQuizCnt = wrongAnswerHandler.totalQuizCount(dateList.getString(1));
            String wrongQuizCnt = wrongAnswerHandler.wrongQuizCount(dateList.getString(1));
            if (categoryCnt.equals("1")) {
                categoryString = "[" + dateList.getString(2) + "]";
                WrongAnswerList_Category wrongQuiz = new WrongAnswerList_Category(dateList.getString(1), categoryString, "총 문제수: " + totalQuizCnt, "오답수: " + wrongQuizCnt);
                wrongAnswerList_category.add(wrongQuiz);
            } else {
                categoryString = "[" + dateList.getString(2) + " 외 " + +(Integer.parseInt(categoryCnt) - 1) + "개]";
                WrongAnswerList_Category wrongQuiz = new WrongAnswerList_Category(dateList.getString(1), categoryString, "총 문제수: " + totalQuizCnt, "오답수: " + wrongQuizCnt);
                wrongAnswerList_category.add(wrongQuiz);
            }
        }
        return wrongAnswerList_category;
    }

    public ArrayList<Word> createReviewTotal(String mDatetime) {
        ArrayList<Word> wordArrayList = new ArrayList<>();
        Cursor cursor = wrongAnswerHandler.createReviewTotal(mDatetime);

        int mCnt = 0;
        while (cursor.moveToNext()) {
            int wordNum = mCnt++;
            String category_major = cursor.getString(2);
            String category_minor = cursor.getString(3);
            String category_theme = cursor.getString(4);
            String category_quiz = cursor.getString(5);
            int wordNo1 = Integer.parseInt(cursor.getString(6));
            int wordNo2 = Integer.parseInt(cursor.getString(7));
            int wordNo3 = Integer.parseInt(cursor.getString(8));
            int wordNo4 = Integer.parseInt(cursor.getString(9));

            Word word = new Word(wordNum, category_major, category_minor, category_theme, category_quiz, wordNo1, wordNo2, wordNo3, wordNo4);
            wordArrayList.add(word);
            Log.i("WrongAnswerController", "(createReviewTotal) " + cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4) + ", " + cursor.getString(5) + ", " + cursor.getString(6) + ", " + cursor.getString(7) + ", " + cursor.getString(8) + ", " + cursor.getString(9) + ", " + cursor.getString(10));
        }
        return wordArrayList;
    }

    public ArrayList<Word> createReviewIncorrect(String mDatetime) {
        ArrayList<Word> wordArrayList = new ArrayList<>();
        Cursor cursor = wrongAnswerHandler.createReviewIncorrect(mDatetime);

        int mCnt = 0;
        while (cursor.moveToNext()) {
            int wordNum = mCnt++;
            String category_major = cursor.getString(2);
            String category_minor = cursor.getString(3);
            String category_theme = cursor.getString(4);
            String category_quiz = cursor.getString(5);
            int wordNo1 = Integer.parseInt(cursor.getString(6));
            int wordNo2 = Integer.parseInt(cursor.getString(7));
            int wordNo3 = Integer.parseInt(cursor.getString(8));
            int wordNo4 = Integer.parseInt(cursor.getString(9));

            Word word = new Word(wordNum, category_major, category_minor, category_theme, category_quiz, wordNo1, wordNo2, wordNo3, wordNo4);
            wordArrayList.add(word);
            Log.i("WrongAnswerController", "(createReviewTotal) " + cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4) + ", " + cursor.getString(5) + ", " + cursor.getString(6) + ", " + cursor.getString(7) + ", " + cursor.getString(8) + ", " + cursor.getString(9) + ", " + cursor.getString(10));
        }
        return wordArrayList;
    }
}
