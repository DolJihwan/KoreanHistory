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

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Quiz;


public class QuizResultActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton q1, q2, q3, q4, q5;
    private Button bt_reQuiz, bt_saveWrongAnswer;

    private ArrayList<Quiz> quizArrayList = new ArrayList<>();
    int[] selectArray;
    int[] correctArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);

        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_reQuiz = (Button) findViewById(R.id.bt_reQuiz);
        bt_saveWrongAnswer = (Button) findViewById(R.id.bt_nextToWrongAnswer);

        bt_saveWrongAnswer.setOnClickListener(this);

        // Display icon in the toolbar
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("결 과 확 인");

        q1 = (ImageButton)findViewById(R.id.imageButton);
        q2 = (ImageButton)findViewById(R.id.imageButton2);
        q3 = (ImageButton)findViewById(R.id.imageButton3);
        q4 = (ImageButton)findViewById(R.id.imageButton4);
        q5 = (ImageButton)findViewById(R.id.imageButton5);

        Bundle extras = getIntent().getExtras();
        quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
        selectArray = extras.getIntArray("selectArray");
        correctArray = extras.getIntArray("correctArray");

        Log.i("QuizResultActivity", "(quizArray) " + quizArrayList.toString());

        if (selectArray[0] == correctArray[0]) {
            q1.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct));
        } else {
            q1.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong));
        }

        if (selectArray[1] == correctArray[1]) {
            q2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct));
        } else {
            q2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong));
        }


        if (selectArray[2] == correctArray[2]) {
            q3.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct));
        } else {
            q3.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong));
        }

        if (selectArray[3] == correctArray[3]) {
            q4.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct));
        } else {
            q4.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong));
        }

        if (selectArray[4] == correctArray[4]) {
            q5.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct));
        } else {
            q5.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong));
        }
    }


    @Override
    public void onClick(View v) {
        if(v == bt_saveWrongAnswer){
            Intent intent = new Intent(QuizResultActivity.this, WrongAnswerListActivity.class);
            intent.putExtra("quizArray", quizArrayList);
            intent.putExtra("selectArray", selectArray);
            intent.putExtra("correctArray", correctArray);
            startActivity(intent);
            finish();
        }
    }
}

