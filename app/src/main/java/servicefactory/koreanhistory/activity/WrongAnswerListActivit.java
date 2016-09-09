package servicefactory.koreanhistory.activity;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

import eu.chainfire.libsuperuser.Shell;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.WrongAnswerPagerAdapter;
import servicefactory.koreanhistory.contorller.WrongAnswerController;
import servicefactory.koreanhistory.date.DateInfo;
import servicefactory.koreanhistory.model.Quiz;
import servicefactory.koreanhistory.persistence.KoreanHistoryFinalVariable;
import servicefactory.koreanhistory.popup.CircularRevealView;
import servicefactory.koreanhistory.xposed.XposedDialog;


public class WrongAnswerListActivit extends AppCompatActivity implements MaterialTabListener ,DialogInterface.OnDismissListener {

    private static DateInfo dateInfoInstance;
    private MaterialTabHost tabHost;
    private ViewPager pager;
    private android.os.Handler handler;

    private CircularRevealView revealView;
    private int backgroundColor;
    int maxX, maxY;

    private ArrayList<Quiz> quizList = new ArrayList<>();
    private int[] selectAnswer;
    private int[] correctAnswer;

    private WrongAnswerController wrongAnswerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wronganswer_list_main);


        revealView = (CircularRevealView) findViewById(R.id.reveal);
        wrongAnswerController = new WrongAnswerController(getApplicationContext());

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

        // Singleton Object of DateTime created.
        if (dateInfoInstance == null) {
            dateInfoInstance = DateInfo.getDateInfoInstance();
        }

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        pager = (ViewPager) findViewById(R.id.viewpager);

        WrongAnswerPagerAdapter pagerAdapter = new WrongAnswerPagerAdapter(getSupportFragmentManager(), WrongAnswerListActivit.this);
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        String[] tabTitle = {"일 자 별", "시 대 별"};
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(tabTitle[i])
                            .setTabListener(WrongAnswerListActivit.this)
            );
        }
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    public void showPowerDialog(Bundle args) {
        FragmentManager fm = getFragmentManager();
        XposedDialog powerDialog = new XposedDialog();
        powerDialog.setArguments(args);
        powerDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppThemeDialog);
        powerDialog.show(fm, "fragment_power");
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        maxX = mdispSize.x;
        maxY = mdispSize.y;

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
