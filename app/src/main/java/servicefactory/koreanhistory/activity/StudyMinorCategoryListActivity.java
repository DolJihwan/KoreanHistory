package servicefactory.koreanhistory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.MyPagerAdapter;
import servicefactory.koreanhistory.contorller.StudyController;
import servicefactory.koreanhistory.model.Category;


/**
 * Created by leejonghyeog on 2016. 8. 18..
 */
public class StudyMinorCategoryListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "StudyMinorCategoryListActivity";
    private FragmentPagerAdapter adapterViewPager;
    private ViewPager vpPager;
    private StudyController studycontroller = null;
    private Intent intent;
    private NavigationView navigationView;
    private ImageView hambergerButton;
    private DrawerLayout drawer;
    private SubMenu tmpSubMenu=null;
    private ArrayList<ArrayList<Category>> totalCategoryList;
    private MenuItem tmpMenuItem = null;
    private Category tmpCategory;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbarSetting();
        intent = getIntent();
        try{
            ChoiceMinorCategoryActivityInit(intent.getStringExtra("majorCategory"),intent.getIntExtra("position", 0));
        }catch (NullPointerException e){
            Log.i("choiceMinor", intent.getStringExtra("majorCategory"));
            ChoiceMinorCategoryActivityInit(intent.getStringExtra("majorCategory"));
          }

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

    public void makeDrawerList(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Log.i("makeDrawerList","test");
      totalCategoryList = studycontroller.getTotalCategoryList();
        for(int i=0; i<totalCategoryList.size(); i++){
            tmpSubMenu = navigationView.getMenu().addSubMenu(totalCategoryList.get(i).get(0).getCategoryMajor());
            Log.i("submenutest",totalCategoryList.get(i).get(0).getCategoryMajor());
            for(int j=0; j<totalCategoryList.get(i).size(); j++){
                tmpMenuItem = tmpSubMenu.add(totalCategoryList.get(i).get(j).getCategoryMinor());
                tmpMenuItem.setIcon(R.drawable.ic_menu_camera);
                tmpMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getApplicationContext(),item.getTitle(), Toast.LENGTH_SHORT);
                        Log.i("string test", item.getTitle()+"");
                        choiceMinorCategoryWithDrawer(item.getTitle().toString());
                        vpPager.invalidate();

                        return false;
                    }
                });
            }
        }
        navigationView.setNavigationItemSelectedListener(this);

    }
    private void choiceMinorCategoryWithDrawer(String minorCategory){

        Log.i("start test", "start!!");
        tmpCategory = studycontroller.getSelectedMajorCategory(minorCategory);

        Log.i("Major1 : ", getMinorCategoryLength(tmpCategory.getCategoryMajor())+"");
        Log.i("Minor1 : ", studycontroller.getMinorCategory(tmpCategory.getCategoryMajor()).toString());
        Intent refresh = new Intent(this, StudyMinorCategoryListActivity.class);
        finish();
        refresh.putExtra("majorCategory",tmpCategory.getCategoryMajor());
        for(int i=0; i<studycontroller.getMinorCategory(tmpCategory.getCategoryMajor()).size(); i++){
            if(minorCategory.equals(studycontroller.getMinorCategory(tmpCategory.getCategoryMajor()).get(i).getCategoryMinor())) {
                refresh.putExtra("position", i);
            }
        }
        startActivity(refresh);


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("click test", item.getItemId()+","+android.R.id.home);
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void ChoiceMinorCategoryActivityInit(String majorCategory) {
        studycontroller = new StudyController(getApplicationContext());
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), getMinorCategoryLength(majorCategory), studycontroller.getMinorCategory(majorCategory),getApplicationContext());
        vpPager.setAdapter(adapterViewPager);
        /*vpPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_BUTTON_PRESS)
            }
        });*/
        //Get access drawer View
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //오른쪽 햄버거 버튼 초기화
        hambergerButton = (ImageView)findViewById(R.id.hambergerbutton);
        hambergerButton.setOnClickListener(this);

        makeDrawerList();
    }
    private void ChoiceMinorCategoryActivityInit(String majorCategory, int i) {
        studycontroller = new StudyController(getApplicationContext());
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), getMinorCategoryLength(majorCategory), studycontroller.getMinorCategory(majorCategory),getApplicationContext());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(i);
        //Get access drawer View
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //오른쪽 햄버거 버튼 초기화
        hambergerButton = (ImageView)findViewById(R.id.hambergerbutton);
        hambergerButton.setOnClickListener(this);

        makeDrawerList();
    }

    private int getMinorCategoryLength(String majorCategory) {
        ArrayList<Category> categoryLength = studycontroller.getMinorCategory(majorCategory);
        return categoryLength.size();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.hambergerbutton){
            drawer.openDrawer(GravityCompat.END);
        }
    }
}
