package servicefactory.koreanhistory.adapter.study;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.Category;
import servicefactory.koreanhistory.model.WrongAnswerItem;


/**
 * Created by saravana on 6/14/2015.
 */
public class StudyCategoryItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_major;
    public IMyViewHolderClicks mListener;
    View view;
    Category categoryItem;

    public StudyCategoryItemHolder(View itemView, IMyViewHolderClicks listener) {
        super(itemView);
        mListener = listener;
        view = itemView;

        tv_major = (TextView) itemView.findViewById(R.id.tv_major);
        itemView.setOnClickListener(this);
        Log.i("Holder", "StudyCategoryItemHolder");
    }

    public void bindItemInfo(Category categoryItem, int pos) {
        Log.i("Holder", "bindItemInfo");
        this.categoryItem = categoryItem;
        view.setTag(pos);
        tv_major.setText(categoryItem.getCategoryMajor());
    }

    @Override
    public void onClick(View view) {
        Log.i("Holder", "onClick");
        mListener.onView(view);
    }

    public static interface IMyViewHolderClicks {
        public void onView(View caller);
    }

}
