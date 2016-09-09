package servicefactory.koreanhistory.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.contorller.StudyController;

/**
 * Created by leejonghyeog on 2016. 8. 24..
 */
public class FragmentAdapter extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private Bundle args;
    private FragmentAdapter fragmentAdapter;
    private Bundle bundle = new Bundle();
    private static final String TAG1 = "fragmentAdapter";
    private TextView tvLabel;
    private TextView tvTheme;
    private TextView tvTitle;
    private TextView tvWord;
    private TextView tvContents;
    private StudyController studyController;
    // newInstance constructor for creating fragment with arguments
    public FragmentAdapter newInstance(int page, String title, Context context) {
        Log.i("parameter test", "page : "+page+"title : " + title);
        this.title =title;
        fragmentAdapter =new FragmentAdapter();
        try {

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        bundle = new Bundle();
        bundle.putString("title",title);

        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainlayout, fragmentAdapter); // Activity 레이아웃의 View ID
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
*/
        fragmentAdapter.setArguments(bundle);
        return fragmentAdapter;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String tmp ="";

        String title = getArguments().getString("title");
        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        studyController = new StudyController(getContext());
        LinearLayout test = (LinearLayout) view.findViewById(R.id.mainlayout);
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollinfragment);
        LinearLayout inner = (LinearLayout)view.findViewById(R.id.innerlayout);
        tvLabel = new TextView(view.getContext());
        Log.i("getargument :", getArguments().getString("title")+getActivity().getIntent().getStringExtra("title"));
        tvLabel.setText(studyController.getSelectedMajorCategory(getArguments().getString("title")).getCategoryMajor());
        tvLabel.setTextSize(30);
        tvLabel.setPadding(5,5,5,5);
        inner.addView(tvLabel);

        tvLabel = new TextView(view.getContext());
        Log.i("getargument :", getArguments().getString("title")+getActivity().getIntent().getStringExtra("title"));
        tvLabel.setText(getArguments().getString("title"));
        tvLabel.setTextSize(23);
        tvLabel.setPadding(3,3,3,3);
        inner.addView(tvLabel);

        Log.i("size",studyController.getSelectedCategoryTheme(title).size()+"");
        for(int i=0; i<studyController.getSelectedCategoryTheme(title).size();i++){
            tvTheme = new TextView(view.getContext());
            Log.i("theme",studyController.getSelectedCategoryTheme(title).get(i));
            tvTheme.setText("* "+studyController.getSelectedCategoryTheme(title).get(i));
            tvTheme.setTextSize(18);

            inner.addView(tvTheme);
            tmp=studyController.getSelectedCategoryTheme(title).get(i);
            for(int j=0; j<studyController.getSelectedCategoryTitle(tmp).size(); j++){
                tvTitle = new TextView(view.getContext());
                tvTitle.setText(" - "+studyController.getSelectedCategoryTitle(tmp).get(j));

                inner.addView(tvTitle);

                tmp=studyController.getSelectedCategoryTitle(tmp).get(j);
               for(int k=0; k<studyController.getSelectedCategoryWord(tmp).size(); k++){
                    tvWord = new TextView(view.getContext());
                    tvWord.setText("    ~ "+studyController.getSelectedCategoryWord(tmp).get(k));

                   inner.addView(tvWord);

                   tmp=studyController.getSelectedCategoryWord(tmp).get(k);
                   for(int l=0; l<studyController.getSelectedCategoryContents(tmp).size();l++){
                       tvContents = new TextView(view.getContext());
                       tvContents.setText("     | "+studyController.getSelectedCategoryContents(tmp).get(l).getCategoryQuiz()+" : "+studyController.getSelectedCategoryContents(tmp).get(l).getContents());

                       inner.addView(tvContents);

                   }
               }
            }
        }
        return view;

    }
    
}
