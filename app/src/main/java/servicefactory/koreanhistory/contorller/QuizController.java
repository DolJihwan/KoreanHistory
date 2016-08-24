package servicefactory.koreanhistory.contorller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import servicefactory.koreanhistory.handler.QuizHandler;
import servicefactory.koreanhistory.model.Category;
import servicefactory.koreanhistory.model.Quiz;
import servicefactory.koreanhistory.model.Word;
import servicefactory.koreanhistory.persistence.KoreanHistoryFinalVariable;

/**
 * Created by JihwanHome on 2016-08-11.
 */
public class QuizController {
    private QuizHandler quizHandler;
    private WrongAnswerController wrongAnswerController;

    private ArrayList<Category> categoryArrayList;
    private ArrayList<Word> quizArrayList;
    private ArrayList<Quiz> quizFinalList;

    public QuizController(Context context) {
        // db connect(quizHandler).
        quizHandler = QuizHandler.getInstance(context);
        quizHandler.open();

        wrongAnswerController = new WrongAnswerController(context);
    }

    public ArrayList<Quiz> createQuiz(ArrayList<Category> arrayList) {

        /* 1. 시대별 목차 선택(dummy data) */
        categoryArrayList = arrayList;
        Log.i("QuizController", "(createQuiz) category:: " + categoryArrayList.toString());

        /* 2. 선택된 목차별로 퀴즈문제수 할당 */
        categoryArrayList = allocateQuizCount(categoryArrayList);
        Log.i("QuizController", "(createQuiz) allocateQuizCount:: " + categoryArrayList.toString());

        /* 3. 할당된 퀴즈문제수 만큼 arrayList확장 */
        categoryArrayList = expandArrayList(categoryArrayList);
        Log.i("QuizController", "(createQuiz) expandArrayList:: " + categoryArrayList.toString());

        /* 4. 목차별 테마 카테고리 할당 */
        categoryArrayList = createCategoryTheme(categoryArrayList);
        Log.i("QuizController", "(createQuiz) createCategoryTheme:: " + categoryArrayList.toString());

        /* 5. 목차별 퀴즈 카테고리 할당 */
        categoryArrayList = createCategoryQuiz(categoryArrayList);
        Log.i("QuizController", "(createQuiz) createCategoryQuiz:: " + categoryArrayList.toString());

        /* 6. 퀴즈 카테고리별 퀴즈 추출 */
        quizArrayList = extractQuiz(categoryArrayList);
        Log.i("QuizController", "(createQuiz) extractQuiz:: " + quizArrayList.toString());

        /* 7. 퀴즈 세부정보 생성 */
        quizFinalList = extractQuizFinal(quizArrayList);
        Log.i("QuizController", "(createQuiz) extractQuizFinal:: " + quizFinalList.toString());

        return quizFinalList;
    }

    public ArrayList<Quiz> createReviewTotal(String mDatetime) {
        /* 6. 퀴즈 카테고리별 퀴즈 추출 */
        quizArrayList = wrongAnswerController.createReviewTotal(mDatetime);
        Log.i("QuizController", "(createReviewTotal) extractQuiz:: " + quizArrayList.toString());

        /* 7. 퀴즈 세부정보 생성 */
        quizFinalList = extractQuizFinal(quizArrayList);
        Log.i("QuizController", "(createReviewTotal) extractQuizFinal:: " + quizFinalList.toString());
//        Cursor cursor = quizHandler.createReviewTotal(mDatetime);
        return quizFinalList;
    }


    public ArrayList<Quiz> createReviewIncorrect(String mDatetime) {
        /* 6. 퀴즈 카테고리별 퀴즈 추출 */
        quizArrayList = wrongAnswerController.createReviewIncorrect(mDatetime);
        Log.i("QuizController", "(createReviewIncorrect) extractQuiz:: " + quizArrayList.toString());

        /* 7. 퀴즈 세부정보 생성 */
        quizFinalList = extractQuizFinal(quizArrayList);
        Log.i("QuizController", "(createReviewIncorrect) extractQuizFinal:: " + quizFinalList.toString());
//        Cursor cursor = quizHandler.createReviewTotal(mDatetime);
        return quizFinalList;
    }

    /* 선택된 목차에 문제개수를 할당하는 메서드 */
    private ArrayList<Category> allocateQuizCount(ArrayList<Category> arrayList) {

        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setqCount(KoreanHistoryFinalVariable.getQuizNum() / arrayList.size());
        }

        Random rand = new Random();
        if (arrayList.size() == 0) {

        } else {
            for (int i = 0; i < (KoreanHistoryFinalVariable.getQuizNum() % arrayList.size()); i++) {
                int index = rand.nextInt(arrayList.size());
                int tmpN = arrayList.get(index).getqCount();
                arrayList.get(index).setqCount(tmpN + 1);
            }
        }
        return arrayList;
    }

    /* 할당된 문제개수만큼 선택된 목차를 확장하는 메서드 */
    private ArrayList<Category> expandArrayList(ArrayList<Category> arrayList) {
        ArrayList<Category> tmpCategoryArrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.get(i).getqCount(); j++) {
                Category tmpCategory = new Category(arrayList.get(i).getCategoryMajor(), arrayList.get(i).getCategoryMinor(), arrayList.get(i).getCategoryTheme(), arrayList.get(i).getCategoryQuiz(), arrayList.get(i).getqCount());
                tmpCategoryArrayList.add(tmpCategory);
            }
        }
        arrayList = tmpCategoryArrayList;

        return arrayList;
    }

    /* 테마 카테고리 생성 메서드 */
    private ArrayList<Category> createCategoryTheme(ArrayList<Category> arrayList) {
        ArrayList<Category> tmpCategoryArrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            Cursor cursor = quizHandler.createCategoryTheme(arrayList.get(i).getCategoryMinor());
            cursor.moveToFirst();
            arrayList.get(i).setCategoryTheme(cursor.getString(3));
        }

        return arrayList;
    }

    /* 퀴즈 카테고리 생성 메서드 */
    private ArrayList<Category> createCategoryQuiz(ArrayList<Category> arrayList) {

        for (int i = 0; i < arrayList.size(); i++) {
            Cursor cursor = quizHandler.createCategoryQuiz(arrayList.get(i).getCategoryTheme());
            cursor.moveToFirst();
            arrayList.get(i).setCategoryQuiz(cursor.getString(5));
        }
        return arrayList;
    }

    /* 퀴즈 추출 메서드 */
    private ArrayList<Word> extractQuiz(ArrayList<Category> arrayList) {
        ArrayList<Word> tmpWordArrList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            String categoryMinor = arrayList.get(i).getCategoryMinor();
            String categoryTheme = arrayList.get(i).getCategoryTheme();
            String categoryQuiz = arrayList.get(i).getCategoryQuiz();

            Cursor cursor = quizHandler.extractQuiz(categoryMinor, categoryTheme, categoryQuiz);

            Word wordTest = new Word(i, arrayList.get(i).getCategoryMajor(), arrayList.get(i).getCategoryMinor(), arrayList.get(i).getCategoryTheme(), arrayList.get(i).getCategoryQuiz());

            int mCnt = 0;
            while (cursor.moveToNext()) {
                switch (mCnt) {
                    case 0:
                        wordTest.setWordNo1(Integer.parseInt(cursor.getString(0)));
                        break;
                    case 1:
                        wordTest.setWordNo2(Integer.parseInt(cursor.getString(0)));
                        break;
                    case 2:
                        wordTest.setWordNo3(Integer.parseInt(cursor.getString(0)));
                        break;
                    case 3:
                        wordTest.setWordNo4(Integer.parseInt(cursor.getString(0)));
                        break;
                }
                mCnt++;
//                Log.i("cursorTest:: ", cursor.getString(0) + ": " + cursor.getString(5) + ": " + cursor.getString(6));
            }
            tmpWordArrList.add(wordTest);
        }

        return tmpWordArrList;
    }

    /*  상세 퀴즈 정보를 가진 전체 퀴즈 리스트   */
    public ArrayList<Quiz> extractQuizFinal(ArrayList<Word> arrayList) {
        ArrayList<Word> quizWordInfo = arrayList;
        ArrayList<Quiz> quizFinalList = new ArrayList<>();

        for (int i = 0; i < quizWordInfo.size(); i++) {
            String quizMajor = quizWordInfo.get(i).getCategory_major();
            String quizMinor = quizWordInfo.get(i).getCategory_minor();
            String quizTheme = quizWordInfo.get(i).getCategory_theme();
            String quizType = quizWordInfo.get(i).getCategory_quiz();
            int quizNo = quizWordInfo.get(i).getWordNo1();
            int iQuizNo1 = quizWordInfo.get(i).getWordNo2();
            int iQuizNo2 = quizWordInfo.get(i).getWordNo3();
            int iQuizNo3 = quizWordInfo.get(i).getWordNo4();

            Cursor cursor = quizHandler.extractQuizFinal(quizNo, iQuizNo1, iQuizNo2, iQuizNo3);

            while (cursor.moveToNext()) {
                Quiz quizAns = new Quiz(i, quizMajor, quizMinor, quizTheme, quizType, quizNo, iQuizNo1, iQuizNo2, iQuizNo3, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                quizFinalList.add(quizAns);
            }
        }
        return quizFinalList;
    }

}
