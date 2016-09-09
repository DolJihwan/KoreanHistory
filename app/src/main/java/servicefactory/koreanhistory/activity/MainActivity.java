package servicefactory.koreanhistory.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.util.ArrayList;

import eu.chainfire.libsuperuser.Shell;
import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Quiz;
import servicefactory.koreanhistory.persistence.KoreanHistoryFinalVariable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private RippleView rv_study, rv_quiz, rv_review, rv_preview;
    private ImageView iv_study, iv_quiz, iv_review, iv_preview;
    private Intent intent;
    android.os.Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        MainActivityInit();
    }

    private void MainActivityInit() {
        rv_study = (RippleView) findViewById(R.id.rv_study);
        iv_study = (ImageView) findViewById(R.id.iv_study);
        rv_study.setOnClickListener(this);
        rv_study.setOnTouchListener(this);

        rv_quiz = (RippleView) findViewById(R.id.rv_quiz);
        iv_quiz = (ImageView) findViewById(R.id.iv_quiz);
        rv_quiz.setOnClickListener(this);
        rv_quiz.setOnTouchListener(this);

        rv_review = (RippleView) findViewById(R.id.rv_review);
        iv_review = (ImageView) findViewById(R.id.iv_review);
        rv_review.setOnClickListener(this);
        rv_review.setOnTouchListener(this);

        rv_preview = (RippleView) findViewById(R.id.rv_preview);
        iv_preview = (ImageView) findViewById(R.id.iv_preview);
        rv_preview.setOnClickListener(this);
        rv_preview.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.rv_study:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("Main", "Touch:: DOWN");
                    // 누르고 있을때 이미지 변경
                    iv_study.setImageResource(R.drawable.main_study_on);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.i("Main", "Touch:: MOVE");
                    if (!view.isPressed()) {
                        view.setPressed(false);
                        Log.i("Main", "Touch:: 밖");
                        iv_study.setImageResource(R.drawable.main_study_off);
                    } else {
                        Log.i("Main", "Touch:: 안");
                        view.setFocusable(true);
                    }
                }
                break;
            case R.id.rv_quiz:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    iv_quiz.setImageResource(R.drawable.main_quiz_on);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (!view.isPressed()) {
                        view.setFocusable(false);
                        iv_quiz.setImageResource(R.drawable.main_quiz_off);
                    }
                }
                break;
            case R.id.rv_review:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    iv_review.setImageResource(R.drawable.main_review_on);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (!view.isPressed()) {
                        view.setFocusable(false);
                        iv_review.setImageResource(R.drawable.main_review_off);
                    }
                }
                break;
            case R.id.rv_preview:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    iv_preview.setImageResource(R.drawable.main_preview_on);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (!view.isPressed()) {
                        view.setFocusable(false);
                        iv_preview.setImageResource(R.drawable.main_preview_off);
                    }
                }
                break;

        }

        return false;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rv_study) {
            Log.i("Main", "Touch:: 클릭");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(MainActivity.this, StudyMajorCategoryListActivity.class);
                    startActivity(intent);
                }
            }, 500);
            // 화면에 손을 뗐을 때 1초 뒤 이미지 원상복귀.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_study.setImageResource(R.drawable.main_study_off);
                }
            }, 1000);
        } else if (v.getId() == R.id.rv_quiz) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(MainActivity.this, QuizListActivity.class);
                    startActivity(intent);
                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_quiz.setImageResource(R.drawable.main_quiz_off);
                }
            }, 1000);
        } else if (v.getId() == R.id.rv_review) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Quiz> quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
                    intent = new Intent(MainActivity.this, WrongAnswerListActivity.class);
                    intent.putExtra("quizArray", quizArrayList);
                    startActivity(intent);
                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_review.setImageResource(R.drawable.main_review_off);
                }
            }, 1000);
        } else if (v.getId() == R.id.rv_preview) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Quiz> quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
                    intent = new Intent(MainActivity.this, WrongAnswerListActivit.class);
                    intent.putExtra("quizArray", quizArrayList);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "업데이트 예정입니다.", Toast.LENGTH_SHORT).show();
                }
            }, 300);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_preview.setImageResource(R.drawable.main_preview_off);
                }
            }, 500);
        }
    }

    private static void setThreadPrio(int prio) {
        android.os.Process.setThreadPriority(prio);
    }

    private static class BackgroundThread extends Thread {
        private Object sCmd;

        private BackgroundThread(Object cmd) {
            this.sCmd = cmd;
        }

        @Override
        public void run() {
            super.run();
            setThreadPrio(KoreanHistoryFinalVariable.BG_PRIO);

            if (sCmd == null)
                return;

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (sCmd instanceof String)
                        Shell.SU.run((String) sCmd);
                    else if (sCmd instanceof String[])
                        Shell.SU.run((String[]) sCmd);
                }
            }, KoreanHistoryFinalVariable.RUNNABLE_DELAY_MS);
        }
    }
}