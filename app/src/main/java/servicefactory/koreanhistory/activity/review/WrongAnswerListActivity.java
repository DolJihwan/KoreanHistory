package servicefactory.koreanhistory.activity.review;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.review.WrongAnswerListViewAdapter;
import servicefactory.koreanhistory.contorller.ReviewController;
import servicefactory.koreanhistory.model.Quiz;
import servicefactory.koreanhistory.model.WrongAnswerList;
import servicefactory.koreanhistory.model.WrongAnswerItem;
import servicefactory.koreanhistory.popup.CircularRevealView;
import servicefactory.koreanhistory.xposed.XposedDialog;

/**
 * Created by JihwanHome on 2016-08-18.
 */
public class WrongAnswerListActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private TextView tv_datetime;
    private ArrayList<Quiz> quizList = new ArrayList<>();
    private int[] selectAnswer;
    private int[] correctAnswer;

    private ReviewController wrongAnswerController;

    private CircularRevealView revealView;
    private int backgroundColor;
    int maxX, maxY;
    android.os.Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("WrongAnswerListActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrong_answer_list);

        revealView = (CircularRevealView) findViewById(R.id.reveal);

        ListView listview;
        WrongAnswerListViewAdapter adapter;
        wrongAnswerController = new ReviewController(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        quizList = (ArrayList<Quiz>) getIntent().getSerializableExtra("quizArray");
        selectAnswer = extras.getIntArray("selectArray");
        correctAnswer = extras.getIntArray("correctArray");

        if (selectAnswer != null) {
            wrongAnswerController.insertQuizInfo(quizList, selectAnswer, correctAnswer);
            Log.i("WrongAnswerActivity", "(QuizInfo) " + quizList.toString());
            Log.i("WrongAnswerActivity", "(selAnswer) " + selectAnswer[0] + ", " + selectAnswer[1] + ", " + selectAnswer[2] + ", " + selectAnswer[3] + ", " + selectAnswer[4]);
            Log.i("WrongAnswerActivity", "(corAnswer) " + correctAnswer[0] + ", " + correctAnswer[1] + ", " + correctAnswer[2] + ", " + correctAnswer[3] + ", " + correctAnswer[4]);
        }

        // Adapter 생성
        adapter = new WrongAnswerListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.lv_wrong_answer_list_view);
        listview.setAdapter(adapter);

        tv_datetime = (TextView) findViewById(R.id.tv_wa_datetime);

        ReviewController wrongAnswerController = new ReviewController(getApplicationContext());
        ArrayList<WrongAnswerItem> wrongQuizListArr = wrongAnswerController.selectWrongQuizInfo();

        for (int i = 0; i < wrongQuizListArr.size(); i++) {
            adapter.addItem(wrongQuizListArr.get(i).getDatetime(), wrongQuizListArr.get(i).getCategoryList(), wrongQuizListArr.get(i).getTotalQuizCount(), wrongQuizListArr.get(i).getWrongQuizCount());
        }

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                WrongAnswerList item = (WrongAnswerList) parent.getItemAtPosition(position);

                String waDatetime = item.getWaDatetime();
                String waCategory = item.getWaCategory();
                String waTotalCount = item.getWaTotalCount();
                String waWrongCount = item.getWaCount();

                Bundle args = new Bundle();
                args.putString("datetime", waDatetime);

//                showPowerDialog(args);

                Log.i("WrongAnswerActivity", "(onClick) " + waDatetime + ", " + waCategory + ", " + waTotalCount + ", " + waWrongCount);

                // TODO : use item data.
            }
        });
    }

    private void showPowerDialog(Bundle args) {
        FragmentManager fm = getFragmentManager();
        XposedDialog powerDialog = new XposedDialog();
        powerDialog.setArguments(args);
        powerDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppThemeDialog);
        powerDialog.show(fm, "fragment_power");
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
//        Display mdisp = getWindowManager().getDefaultDisplay();
//        Point mdispSize = new Point();
////        mdisp.getSize(mdispSize);
//        maxX = mdispSize.x;
//        maxY = mdispSize.y;

        final Point p = new Point(maxX / 2, maxY / 2);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                revealView.hide(p.x, p.y, backgroundColor, 0, 330, null);
            }
        }, 300);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0, 0);
            }
        }, 500);
    }
}