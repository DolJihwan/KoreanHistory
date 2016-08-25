package servicefactory.koreanhistory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Quiz;
import servicefactory.koreanhistory.persistence.KoreanHistoryFinalVariable;


public class QuizResultActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[] qButton = new ImageButton[10];
    private Button bt_reQuiz, bt_saveWrongAnswer, previous, next;
    private ViewFlipper flipper;

    private ArrayList<Quiz> quizArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);

        previous = (Button) findViewById(R.id.previous);
        previous.setText("이전5문제");
        next = (Button) findViewById(R.id.next);
        next.setText("다음5문제");
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        flipper.setDisplayedChild(0);

        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_reQuiz = (Button) findViewById(R.id.bt_reQuiz);
        bt_saveWrongAnswer = (Button) findViewById(R.id.bt_nextToWrongAnswer);

        bt_reQuiz.setOnClickListener(this);
        bt_saveWrongAnswer.setOnClickListener(this);

        // Display icon in the toolbar
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("결 과 확 인");

        qButton[0] = (ImageButton) findViewById(R.id.imageButton);
        qButton[1] = (ImageButton) findViewById(R.id.imageButton2);
        qButton[2] = (ImageButton) findViewById(R.id.imageButton3);
        qButton[3] = (ImageButton) findViewById(R.id.imageButton4);
        qButton[4] = (ImageButton) findViewById(R.id.imageButton5);
        qButton[5] = (ImageButton) findViewById(R.id.imageButton6);
        qButton[6] = (ImageButton) findViewById(R.id.imageButton7);
        qButton[7] = (ImageButton) findViewById(R.id.imageButton8);
        qButton[8] = (ImageButton) findViewById(R.id.imageButton9);
        qButton[9] = (ImageButton) findViewById(R.id.imageButton10);

        qButton[0].setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
        int[] selectArray = extras.getIntArray("selectArray");
        int[] correctArray = extras.getIntArray("correctArray");

        Log.i("QuizResultActivity", "(quizArray) " + quizArrayList.toString());

        int i;
        for (i = 0; i < KoreanHistoryFinalVariable.getQuizNum(); i++) {
            if (selectArray[i] == correctArray[i]) {
                qButton[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct));

            } else {
                qButton[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong));
            }
        }
    }


    @Override
    public void onClick(View v) {

        if (v == bt_saveWrongAnswer) {
            Intent intent = new Intent(QuizResultActivity.this, WrongAnswerListActivity.class);
            startActivity(intent);
        }

        if (v == bt_reQuiz) {
            Intent intent = new Intent(QuizResultActivity.this, QuizListActivity.class);
            startActivity(intent);
        }

        if (v == next && flipper.getDisplayedChild() == 0) {
            flipper.showNext();
            next.setEnabled(false);
            previous.setEnabled(true);
        } else if (v == previous && flipper.getDisplayedChild() == 1) {
            flipper.showPrevious();
            previous.setEnabled(false);
            next.setEnabled(true);
        }

        if (v == qButton[0]) {
            Intent intent = new Intent(getApplication(), WrongQuestion.class);
//            intent.putExtra("quizArray", quizArrayList);
            startActivity(intent);

        }

    }

}