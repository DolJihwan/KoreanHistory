package servicefactory.koreanhistory.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.contorller.CategoryController;
import servicefactory.koreanhistory.contorller.QuizController;
import servicefactory.koreanhistory.model.Category;
import servicefactory.koreanhistory.model.Quiz;

/**
 * Created by JihwanHome on 2016-08-06.
 */
public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuizActivity";

    private TextView tv_question;
    private Button bt_prev, bt_next, bt_finish, bt_quiz;
    private RadioButton rbt_answer1, rbt_answer2, rbt_answer3, rbt_answer4;
    private RadioGroup rg_answer;

    private QuizController quizController;
    private CategoryController categoryController;

    // 정답과 선택 답안 index 저장 배열
    private int correctAnswer[], selectAnswer[] = null;
    // 현재 풀고있는 퀴즈 인덱스
    private int quizIndex = 0;
    // 정오답 판단 flag.
    private boolean finishFlag;

    // 문제세부정보를 가지고 있는 최종 배열
    private ArrayList<Quiz> quizFinalArr = new ArrayList<>();
    private ArrayList<Quiz> randQuizArr = new ArrayList<>();
    private ArrayList<Category> selectedCategoryArrList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("QuizActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        toolbarSetting();

        // 0. 퀴즈 액티비티 초기화 설정
        quizActivityInit();

        /* 1. 퀴즈 추출 범위(목차) 파악하기 */
        Intent intent = getIntent();
        String activityFlag = intent.getStringExtra("activityFlag");

        if (activityFlag.equals("quizList")) {
            selectedCategoryArrList = (ArrayList<Category>) getIntent().getSerializableExtra("categoryArr");
            if (selectedCategoryArrList.size() == 0) {
                selectedCategoryArrList = categoryController.getTotalCategory();
            }
            Log.i("QuizActivity", "(selected Quiz Category) " + selectedCategoryArrList.toString());

        /* 2. 선택된 퀴즈목차에 해당하는 문제정보 추출하기 */
            quizFinalArr = quizController.createQuiz(selectedCategoryArrList);
            Log.i("QuizActivity", "(Extracted FinalQuiz) quizList: " + quizFinalArr.toString());
        }else if(activityFlag.equals("entire")){
            String mDatetime = intent.getStringExtra("datetime");
            quizFinalArr = quizController.createReviewTotal(mDatetime);
            Log.i("QuizActivity", "(Extracted FinalQuiz) review: " + quizFinalArr.toString());
        }else if(activityFlag.equals("review")){
            String mDatetime = intent.getStringExtra("datetime");
            quizFinalArr = quizController.createReviewIncorrect(mDatetime);
            Log.i("QuizActivity", "(Extracted FinalQuiz) review: " + quizFinalArr.toString());
        }

        /* 정답 인덱스 생성*/
        correctAnswer = createCorrectAnswer();
//        Log.i("QuizActivity", "(CorrectAnswer) " + "{" + correctAnswer[0] + ", " + correctAnswer[1] + ", " + correctAnswer[2] + ", " + correctAnswer[3] + ", " + correctAnswer[4] + "}");

        /* 정답 인덱스에 정답(1번인덱스)을 가져다 놓기 */
        ArrayList<Quiz> tmpQuizArray = new ArrayList<>();
        for (int i = 0; i < quizFinalArr.size(); i++) {
            Quiz tmpQuiz = quizFinalArr.get(i);
            tmpQuizArray.add(tmpQuiz);
        }
        randQuizArr = tmpQuizArray;
        randQuizArr = exchangeQuizOrder(quizIndex, randQuizArr, tmpQuizArray);
        Log.i("QuizActivity", "(Extracted randomQuiz) " + randQuizArr.toString());
        Log.i("QuizActivity", "(Extracted quizFinalArr) " + quizFinalArr.toString());

        /* 선택된 정답 selectedAnswer[]에 넣기 */
        selectAnswer = new int[this.randQuizArr.size()];
        Arrays.fill(selectAnswer, -1);

        /* 화면에 퀴즈내용 보여주기 */
        displayQuiz(quizIndex, randQuizArr, finishFlag);

        return;
    }

    private void toolbarSetting() {
        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("퀴 즈");
    }

    public ArrayList<Quiz> exchangeQuizOrder(int quizIndex, ArrayList<Quiz> returnArr, ArrayList<Quiz> quizDetailArr) {
        int tmpNo = correctAnswer[quizIndex];

        for (int i = 0; i < quizDetailArr.size(); i++) {
            if (correctAnswer[i] == 0) {
            } else if (correctAnswer[i] == 1) {
                String firstQuizAns = quizDetailArr.get(i).getQuizAnswer1();
                String secondQuizAns = quizDetailArr.get(i).getQuizAnswer2();
                String thirdQuizAns = quizDetailArr.get(i).getQuizAnswer3();
                String fourthQuizAns = quizDetailArr.get(i).getQuizAnswer4();
                returnArr.get(i).setQuizAnswer1(secondQuizAns);
                returnArr.get(i).setQuizAnswer2(firstQuizAns);
                returnArr.get(i).setQuizAnswer3(thirdQuizAns);
                returnArr.get(i).setQuizAnswer4(fourthQuizAns);
            } else if (correctAnswer[i] == 2) {
                String firstQuizAns = quizDetailArr.get(i).getQuizAnswer1();
                String secondQuizAns = quizDetailArr.get(i).getQuizAnswer2();
                String thirdQuizAns = quizDetailArr.get(i).getQuizAnswer3();
                String fourthQuizAns = quizDetailArr.get(i).getQuizAnswer4();
                returnArr.get(i).setQuizAnswer1(thirdQuizAns);
                returnArr.get(i).setQuizAnswer2(secondQuizAns);
                returnArr.get(i).setQuizAnswer3(firstQuizAns);
                returnArr.get(i).setQuizAnswer4(fourthQuizAns);
            } else if (correctAnswer[i] == 3) {
                String firstQuizAns = quizDetailArr.get(i).getQuizAnswer1();
                String secondQuizAns = quizDetailArr.get(i).getQuizAnswer2();
                String thirdQuizAns = quizDetailArr.get(i).getQuizAnswer3();
                String fourthQuizAns = quizDetailArr.get(i).getQuizAnswer4();
                returnArr.get(i).setQuizAnswer1(fourthQuizAns);
                returnArr.get(i).setQuizAnswer2(secondQuizAns);
                returnArr.get(i).setQuizAnswer3(thirdQuizAns);
                returnArr.get(i).setQuizAnswer4(firstQuizAns);
            }
        }
        return returnArr;
    }

    private int[] createCorrectAnswer() {
        Random rand = new Random();
        int ansIndex;

        correctAnswer = new int[this.quizFinalArr.size()];
        Arrays.fill(correctAnswer, -1);

        for (int i = 0; i < quizFinalArr.size(); i++) {
            ansIndex = 0;
            ansIndex = rand.nextInt(4);
            Arrays.fill(correctAnswer, i, i + 1, ansIndex);
        }
        return correctAnswer;
    }

    private void quizActivityInit() {
        quizController = new QuizController(getApplicationContext());
        categoryController = new CategoryController(getApplicationContext());

        // 버튼 초기화
        bt_prev = (Button) findViewById(R.id.bt_prev);
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_finish = (Button) findViewById(R.id.bt_finish);
        bt_quiz = (Button) findViewById(R.id.bt_quiz_hint);

        // 라디오 버튼 초기화
        tv_question = (TextView) findViewById(R.id.tv_question);
        rbt_answer1 = (RadioButton) findViewById(R.id.rbt_answer1);
        rbt_answer2 = (RadioButton) findViewById(R.id.rbt_answer2);
        rbt_answer3 = (RadioButton) findViewById(R.id.rbt_answer3);
        rbt_answer4 = (RadioButton) findViewById(R.id.rbt_answer4);
        rg_answer = (RadioGroup) findViewById(R.id.rg_answers);

        bt_prev.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_finish.setOnClickListener(this);
        bt_quiz.setOnClickListener(this);
        rbt_answer1.setOnClickListener(this);
        rbt_answer2.setOnClickListener(this);
        rbt_answer3.setOnClickListener(this);
        rbt_answer4.setOnClickListener(this);

        finishFlag = false;
    }

    private void displayQuiz(int index, ArrayList<Quiz> quizArray, boolean flag) {
        // first index & last index
        if (index == (quizArray.size() - 1))
            bt_next.setEnabled(false);
        if (index < (quizArray.size() - 1))
            bt_next.setEnabled(true);
        if (index == 0)
            bt_prev.setEnabled(false);
        if (index > 0)
            bt_prev.setEnabled(true);

        // selectAnswer에 저장된 번호를 현재 퀴즈에 체크.
        if (selectAnswer[index] == 0)
            rg_answer.check(R.id.rbt_answer1);
        if (selectAnswer[index] == 1)
            rg_answer.check(R.id.rbt_answer2);
        if (selectAnswer[index] == 2)
            rg_answer.check(R.id.rbt_answer3);
        if (selectAnswer[index] == 3)
            rg_answer.check(R.id.rbt_answer4);
//        Log.d("QuizActivity", "(selectedAnswer) " + "{" + selectAnswer[0] + ", " + selectAnswer[1] + ", " + selectAnswer[2] + ", " + selectAnswer[3] + ", " + selectAnswer[4] + "}");

        // 화면에 문제 뿌려주기
        for (int i = 0; i < quizArray.size(); i++) {
            tv_question.setText(quizArray.get(quizIndex).getQuizQuestion() + "과 가장 관련이 있는 " + quizArray.get(quizIndex).getCategory_quiz() + "는(은)?");
            rbt_answer1.setText(quizArray.get(quizIndex).getQuizAnswer1());
            rbt_answer2.setText(quizArray.get(quizIndex).getQuizAnswer2());
            rbt_answer3.setText(quizArray.get(quizIndex).getQuizAnswer3());
            rbt_answer4.setText(quizArray.get(quizIndex).getQuizAnswer4());

            rbt_answer1.setTextColor(Color.BLACK);
            rbt_answer2.setTextColor(Color.BLACK);
            rbt_answer3.setTextColor(Color.BLACK);
            rbt_answer4.setTextColor(Color.BLACK);
        }

        /* 정답확인 */
        if (flag) {
            reviewAnswer(index);
        }
    }

    private void reviewAnswer(int index) {
        Log.i("QuizActivity", "(CorrectAnswer) " + "{" + correctAnswer[0] + ", " + correctAnswer[1] + ", " + correctAnswer[2] + ", " + correctAnswer[3] + ", " + correctAnswer[4] + "}");
        Log.d("QuizActivity", "(SelectedAnswer) " + "{" + selectAnswer[0] + ", " + selectAnswer[1] + ", " + selectAnswer[2] + ", " + selectAnswer[3] + ", " + selectAnswer[4] + "}");
        Log.d("QuizActivity", "(ReviewAnswer) " + selectAnswer[quizIndex] + ":" + correctAnswer[quizIndex]);
        if (selectAnswer[index] != correctAnswer[index]) {
            if (selectAnswer[index] == 0)
                rbt_answer1.setTextColor(Color.RED);
            if (selectAnswer[index] == 1)
                rbt_answer2.setTextColor(Color.RED);
            if (selectAnswer[index] == 2)
                rbt_answer3.setTextColor(Color.RED);
            if (selectAnswer[index] == 3)
                rbt_answer4.setTextColor(Color.RED);
        }
        if (correctAnswer[index] == 0)
            rbt_answer1.setTextColor(Color.GREEN);
        if (correctAnswer[index] == 1)
            rbt_answer2.setTextColor(Color.GREEN);
        if (correctAnswer[index] == 2)
            rbt_answer3.setTextColor(Color.GREEN);
        if (correctAnswer[index] == 3)
            rbt_answer4.setTextColor(Color.GREEN);

        rbt_answer1.setEnabled(false);
        rbt_answer2.setEnabled(false);
        rbt_answer3.setEnabled(false);
        rbt_answer4.setEnabled(false);
    }

    @Override
    public void onClick(View v) {

        setAnswer();

        if (v == bt_prev) {
            rg_answer.check(-1);
            quizIndex--;
            displayQuiz(quizIndex, randQuizArr, finishFlag);
        } else if (v == bt_next) {
            rg_answer.check(-1);
            quizIndex++;
            displayQuiz(quizIndex, randQuizArr, finishFlag);
        } else if (v == bt_finish) {
            Intent intent = new Intent(getApplication(), QuizResultActivity.class);
            intent.putExtra("quizArray", quizFinalArr);
            intent.putExtra("selectArray", selectAnswer);
            intent.putExtra("correctArray", correctAnswer);
            startActivity(intent);
            finish();
        } else if (v == bt_quiz) {

        }
    }

    private void setAnswer() {
        if (rbt_answer1.isChecked())
            selectAnswer[quizIndex] = 0;
        if (rbt_answer2.isChecked())
            selectAnswer[quizIndex] = 1;
        if (rbt_answer3.isChecked())
            selectAnswer[quizIndex] = 2;
        if (rbt_answer4.isChecked())
            selectAnswer[quizIndex] = 3;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

