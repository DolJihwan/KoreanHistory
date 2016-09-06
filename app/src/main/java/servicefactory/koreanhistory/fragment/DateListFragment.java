package servicefactory.koreanhistory.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.RecyclerViewMaterialAdapter;
import servicefactory.koreanhistory.adapter.WrongAnswerListAdapter;
import servicefactory.koreanhistory.contorller.WrongAnswerController;
import servicefactory.koreanhistory.model.WrongAnswerItem;


public class DateListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    ArrayList<WrongAnswerItem> cards = new ArrayList<>();

    public DateListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wronganswer_fragment_recyclerview, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        WrongAnswerController wrongAnswerController = new WrongAnswerController(getActivity());
        ArrayList<WrongAnswerItem> wrongQuizListArr = wrongAnswerController.selectWrongQuizInfo();

        for (int i = 0; i < wrongQuizListArr.size(); i++) {
            WrongAnswerItem wrongAnswerItem = new WrongAnswerItem();
            wrongAnswerItem.setDatetime(wrongQuizListArr.get(i).getDatetime());
            wrongAnswerItem.setCategoryList(wrongQuizListArr.get(i).getCategoryList());
            wrongAnswerItem.setWrongQuizCount(wrongQuizListArr.get(i).getWrongQuizCount());
            cards.add(wrongAnswerItem);
        }

        mAdapter = new RecyclerViewMaterialAdapter(new WrongAnswerListAdapter(cards, getActivity()));
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


}
