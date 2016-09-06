package servicefactory.koreanhistory.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.adapter.RecyclerViewMaterialAdapter;
import servicefactory.koreanhistory.adapter.WrongAnswerGridAdapter;
import servicefactory.koreanhistory.adapter.WrongAnswerListAdapter;
import servicefactory.koreanhistory.contorller.WrongAnswerController;
import servicefactory.koreanhistory.model.WrongAnswerItem;


public class CategoryListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    ArrayList<WrongAnswerItem> wrongAnswerItemsCards = new ArrayList<>();

    public CategoryListFragment() {
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
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);



        WrongAnswerController wrongAnswerController = new WrongAnswerController(getActivity());
        ArrayList<WrongAnswerItem> wrongQuizListArr = wrongAnswerController.selectWrongCategoryInfo();

        for (int i = 0; i < wrongQuizListArr.size(); i++) {
            WrongAnswerItem wrongAnswerItem = new WrongAnswerItem();
            wrongAnswerItem.setDatetime(wrongQuizListArr.get(i).getDatetime());
            wrongAnswerItem.setCategoryList(wrongQuizListArr.get(i).getCategoryList());
            wrongAnswerItem.setWrongQuizCount(wrongQuizListArr.get(i).getWrongQuizCount());
            wrongAnswerItemsCards.add(wrongAnswerItem);
        }

        mAdapter = new RecyclerViewMaterialAdapter(new WrongAnswerGridAdapter(wrongAnswerItemsCards, getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
