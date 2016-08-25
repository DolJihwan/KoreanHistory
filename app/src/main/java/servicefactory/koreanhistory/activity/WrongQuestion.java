package servicefactory.koreanhistory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Quiz;

public class WrongQuestion extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Quiz> quizArrayList = new ArrayList<>();
    private TextView wrongQuestion;
    private Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wrong_question);

       /* Bundle extras = getIntent().getExtras();
        quizArrayList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
        int[] selectArray = extras.getIntArray("selectArray");
        int[] correctArray = extras.getIntArray("correctArray");*/

        wrongQuestion = (TextView)findViewById(R.id.textView1);
        wrongQuestion.setText("오답문제");

        close = (Button)findViewById(R.id.button1);
        close.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == close){
           this.finish();
        }
    }
}
