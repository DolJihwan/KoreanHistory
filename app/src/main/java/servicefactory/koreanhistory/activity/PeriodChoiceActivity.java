package servicefactory.koreanhistory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.contorller.CategoryController;
import servicefactory.koreanhistory.model.CategoryList;

/**
 * Created by JihwanHome on 2016-08-14.
 */
public class PeriodChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton[] bt_category;
    TextView[] tv_category;
    ViewGroup[] layout_period;
    CategoryController categoryController;
    ArrayList<String> majorCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("PeriodChoiceActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        toolbarSetting();

        initPeriodChoiceActivity();
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
        mTitle.setText("학 습");
    }


    private void initPeriodChoiceActivity() {
        categoryController = new CategoryController(getApplicationContext());
        ArrayList<CategoryList> categoryList = categoryController.getTotalCategoryList();
        majorCategory = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++) {
            majorCategory.add(categoryList.get(i).groupName);
        }
        Log.i("PeriodChoiceActivity", majorCategory.toString() + majorCategory.size());

        bt_category = new ImageButton[majorCategory.size()];
        tv_category = new TextView[majorCategory.size()];
        layout_period = new LinearLayout[majorCategory.size()];

        bt_category[0] = (ImageButton) findViewById(R.id.bt_period0);
        bt_category[1] = (ImageButton) findViewById(R.id.bt_period1);
        bt_category[2] = (ImageButton) findViewById(R.id.bt_period2);
        bt_category[3] = (ImageButton) findViewById(R.id.bt_period3);
        bt_category[4] = (ImageButton) findViewById(R.id.bt_period4);
        bt_category[5] = (ImageButton) findViewById(R.id.bt_period5);
        bt_category[6] = (ImageButton) findViewById(R.id.bt_period6);
        bt_category[7] = (ImageButton) findViewById(R.id.bt_period7);

        tv_category[0] = (TextView) findViewById(R.id.tv_period0);
        tv_category[1] = (TextView) findViewById(R.id.tv_period1);
        tv_category[2] = (TextView) findViewById(R.id.tv_period2);
        tv_category[3] = (TextView) findViewById(R.id.tv_period3);
        tv_category[4] = (TextView) findViewById(R.id.tv_period4);
        tv_category[5] = (TextView) findViewById(R.id.tv_period5);
        tv_category[6] = (TextView) findViewById(R.id.tv_period6);
        tv_category[7] = (TextView) findViewById(R.id.tv_period7);

        layout_period[0] = (LinearLayout) findViewById(R.id.layout_period0);
        layout_period[1] = (LinearLayout) findViewById(R.id.layout_period1);
        layout_period[2] = (LinearLayout) findViewById(R.id.layout_period2);
        layout_period[3] = (LinearLayout) findViewById(R.id.layout_period3);
        layout_period[4] = (LinearLayout) findViewById(R.id.layout_period4);
        layout_period[5] = (LinearLayout) findViewById(R.id.layout_period5);
        layout_period[6] = (LinearLayout) findViewById(R.id.layout_period6);
        layout_period[7] = (LinearLayout) findViewById(R.id.layout_period7);

        for (int i = 0; i < bt_category.length; i++) {
//            bt_category[i].setOnClickListener(this);
            tv_category[i].setText(majorCategory.get(i));
            layout_period[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < layout_period.length; i++) {
            if (v == layout_period[i]) {
                Toast.makeText(this, "Hello Layout: " + v.getId(), Toast.LENGTH_SHORT).show();
            }
        }
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
}
