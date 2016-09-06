package servicefactory.koreanhistory.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import eu.chainfire.libsuperuser.Shell;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.WrongAnswerPagerAdapter;
import servicefactory.koreanhistory.date.DateInfo;
import servicefactory.koreanhistory.persistence.KoreanHistoryFinalVariable;


public class WrongAnswerListActivit extends AppCompatActivity implements MaterialTabListener {

    private static DateInfo dateInfoInstance;
    private MaterialTabHost tabHost;
    private ViewPager pager;
    private android.os.Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wronganswer_list_main);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
