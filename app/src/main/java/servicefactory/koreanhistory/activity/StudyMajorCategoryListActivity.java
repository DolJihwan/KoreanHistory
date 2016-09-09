package servicefactory.koreanhistory.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.StudyCategoryListAdapter;
import servicefactory.koreanhistory.adapter.RecyclerViewMaterialAdapter;
import servicefactory.koreanhistory.contorller.StudyController;
import servicefactory.koreanhistory.model.Category;

/**
 * Created by leejonghyeog on 2016. 8. 17..
 */
public class StudyMajorCategoryListActivity extends AppCompatActivity {

    private static final String TAG = "StudyMajorCategoryListActivity";
    private StudyController studycontroller = null;
    private Intent intent;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    ArrayList<Category> cards = new ArrayList<>();

    public StudyMajorCategoryListActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_list_recyclerview);

        toolbarSetting();



//        makeMajorCategoryList();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

//        WrongAnswerController wrongAnswerController = new WrongAnswerController(getApplicationContext());
//        ArrayList<WrongAnswerItem> wrongQuizListArr = wrongAnswerController.selectWrongQuizInfo();

        studycontroller = new StudyController(getApplicationContext());
        ArrayList<Category> categoryList = studycontroller.getMajorCategory();

        for (int i = 0; i < categoryList.size(); i++) {
            Category categoryItem = new Category(categoryList.get(i).getCategoryMajor());
            cards.add(categoryItem);
        }

        Log.i("Activity", "start");
        mAdapter = new RecyclerViewMaterialAdapter(new StudyCategoryListAdapter(cards, getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
        Log.i("Activity", "end");
    }

    private void toolbarSetting() {
        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("학 습");
    }

    private void choiceMajorCategoryActivityInit() {
    }

    private void makeMajorCategoryList() {
        ArrayList<Category> majorList = null;
        if (studycontroller.getMajorCategory() != null) {
            majorList = studycontroller.getMajorCategory();
        }
        for (int i = 0; i < majorList.size(); i++) {
            touchHistoryIcon(makeIcon(majorList.get(i).getCategoryMajor(), getApplicationContext()), majorList.get(i).getCategoryMajor());
        }
    }

    private void touchHistoryIcon(Button button, final String majorCategory) {
        button.setOnClickListener(new Button.OnClickListener() {
            @Override

            public void onClick(View view) {
                Toast.makeText(StudyMajorCategoryListActivity.this, majorCategory, Toast.LENGTH_SHORT).show();
                intent = new Intent(StudyMajorCategoryListActivity.this, StudyMinorCategoryListActivity.class);
                intent.putExtra("majorCategory", majorCategory);
                startActivity(intent);
            }
        });
    }

    private Button makeIcon(String subject, Context context) {
        Button button = new Button(context);
        button.setText(subject);
//        categoryList.addView(button);
        return button;
    }
}
