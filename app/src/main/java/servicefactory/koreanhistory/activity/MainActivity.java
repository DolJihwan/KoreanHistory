package servicefactory.koreanhistory.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Quiz;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_db, bt_quiz, bt_rank;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("한 국 사");

        MainActivityInit();
    }

    private void MainActivityInit() {
        bt_db = (Button) findViewById(R.id.bt_db);
        bt_db.setOnClickListener(this);

        bt_quiz = (Button) findViewById(R.id.bt_quiz);
        bt_quiz.setOnClickListener(this);

        bt_rank = (Button) findViewById(R.id.bt_rank);
        bt_rank.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_db) {
            intent = new Intent(MainActivity.this, PeriodChoiceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_quiz) {
            intent = new Intent(MainActivity.this, QuizListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_rank) {
//            WrongAnswerController wrongAnswerController = new WrongAnswerController(getApplicationContext());
//            wrongAnswerController.selectWrongQuizInfo();

            ArrayList<Quiz> quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
            intent = new Intent(MainActivity.this, WrongAnswerListActivity.class);
            intent.putExtra("quizArray", quizArrayList);
            intent.putExtra("selectArray", "");
            intent.putExtra("correctArray", "");
            startActivity(intent);
        }
    }
}