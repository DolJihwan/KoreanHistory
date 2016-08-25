package servicefactory.koreanhistory.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Quiz;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RippleView rv_study, rv_quiz, rv_review, rv_preview;
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

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.i("OnTouch", "Touch Info:: " + view.getId() + ", " + view.getTag() + ", ");
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                Log.i("onTouch", "Action_down");
                // 사용자가 액티비티 영역을 눌렀을 때 처리할 루틴을 정의한다.
                return true; // 이 이벤트에 대한 처리를 완료하였다.
            } else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                Log.i("onTouch", "Action_move");
                // 사용자가 액티비티 영역에서 움직였을 때 처리할 루틴을 정의한다.
                return true;
            } else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                Log.i("onTouch", "Action_up");
                // 사용자가 액티비티 영역에서 눌렀던 것을 떼었을 때 처리할 루틴을 정의한다.
                return true;
            }
            // 누르고, 움직이고, 떼는 행위 외의 이벤트는 이 핸들러에서 처리하지 않았음을 알린다.
            return false;
        }
    };

    private void MainActivityInit() {
        rv_study = (RippleView) findViewById(R.id.rv_study);
        rv_study.setOnClickListener(this);
        rv_quiz = (RippleView) findViewById(R.id.rv_quiz);
        rv_quiz.setOnClickListener(this);
        rv_review = (RippleView) findViewById(R.id.rv_review);
        rv_review.setOnClickListener(this);
        rv_preview = (RippleView) findViewById(R.id.rv_preview);
        rv_preview.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rv_study) {
            intent = new Intent(MainActivity.this, PeriodChoiceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.rv_quiz) {
            intent = new Intent(MainActivity.this, QuizListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.rv_review) {
//            WrongAnswerController wrongAnswerController = new WrongAnswerController(getApplicationContext());
//            wrongAnswerController.selectWrongQuizInfo();

            ArrayList<Quiz> quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
            intent = new Intent(MainActivity.this, WrongAnswerListActivity.class);
            intent.putExtra("quizArray", quizArrayList);
            intent.putExtra("selectArray", -1);
            intent.putExtra("correctArray", -1);
            startActivity(intent);
        }else if( v.getId() == R.id.rv_preview){
            Toast.makeText(this, "업데이트 예정입니다.", Toast.LENGTH_SHORT).show();
        }
    }
}