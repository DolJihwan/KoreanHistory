package servicefactory.koreanhistory.activity.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.ExpandAdapter;
import servicefactory.koreanhistory.contorller.CategoryController;
import servicefactory.koreanhistory.model.Category;
import servicefactory.koreanhistory.model.CategoryList;

/**
 * Created by JihwanHome on 2016-08-10.
 */
public class QuizListActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_finishSelect, bt_selectAll, bt_selectInit;

    private ExpandAdapter adapter;
    private ExpandableListView categoriesList;
    private ArrayList<CategoryList> categories;
    private ArrayList<Category> selectedCategories;
    private CategoryController categoryController;

    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("QuizListActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        toolbarSetting();

        // initialize this quizListActivity variable.
        quizListActivityInit();
        categoryController = new CategoryController(getApplicationContext());

        mContext = this;
        categoriesList = (ExpandableListView) findViewById(R.id.categories);
        categories = categoryController.getTotalCategoryList();
        adapter = new ExpandAdapter(this, categories, categoriesList);
        categoriesList.setAdapter(adapter);

        categoriesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                CheckedTextView checkbox = (CheckedTextView) v.findViewById(R.id.list_item_text_child);
                checkbox.toggle();

                // find parent view by tag
                View parentView = categoriesList.findViewWithTag(categories.get(groupPosition).groupName);
                if (parentView != null) {
                    TextView sub = (TextView) parentView.findViewById(R.id.list_item_text_subscriptions);

                    if (sub != null) {
                        CategoryList category = categories.get(groupPosition);
                        if (checkbox.isChecked()) {
                            // add child category to parent's selection list
                            category.selection.add(checkbox.getText().toString());

                            // sort list in alphabetical order
                            Collections.sort(category.selection, new CustomComparator());
                        } else {
                            // remove child category from parent's selection list
                            category.selection.remove(checkbox.getText().toString());
                        }

                        // display selection list
                        sub.setText(category.selection.toString());
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == bt_finishSelect) {
            for (int i = 0; i < categories.size(); i++) {
                for (int j = 0; j < categories.get(i).selection.size(); j++) {
                    Category category = new Category(categories.get(i).groupName, categories.get(i).selection.get(j));
                    selectedCategories.add(category);
                }
            }
            Toast.makeText(this, selectedCategories.size() + ": " + selectedCategories.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizListActivity.this, QuizActivity.class);
            intent.putExtra("activityFlag", "quizList");
            intent.putExtra("categoryArr", selectedCategories);
            startActivity(intent);
            finish();
        } else if (v == bt_selectAll) {

            // 이전에 선택된 카테고리 비우기
            for (int i = 0; i < categories.size(); i++) {
                categories.get(i).selection.clear();
            }
            // 카테고리 전체 선택하기
            for (int i = 0; i < categories.size(); i++) {
                for (int j = 0; j < categories.get(i).childNameList.size(); j++) {
                    categories.get(i).selection.add(categories.get(i).childNameList.get(j));
                }
            }
        } else if (v == bt_selectInit) {
            // 이전에 선택된 카테고리 비우기
            for (int i = 0; i < categories.size(); i++) {
                categories.get(i).selection.clear();

            }
        }
        release();
    }

    public void release() {
        adapter.notifyDataSetChanged();
    }

    public class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
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

    private void quizListActivityInit() {
        selectedCategories = new ArrayList<>();

        bt_finishSelect = (Button) findViewById(R.id.bt_finishSelect);
        bt_selectAll = (Button) findViewById(R.id.bt_selectAll);
        bt_selectInit = (Button) findViewById(R.id.bt_selectInit);

        bt_finishSelect.setOnClickListener(this);
        bt_selectAll.setOnClickListener(this);
        bt_selectInit.setOnClickListener(this);
    }
}