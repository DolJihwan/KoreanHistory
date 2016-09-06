package servicefactory.koreanhistory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.model.WrongAnswerItem;


/**
 * Created by saravana on 6/14/2015.
 */
public class WrongAnswerItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_dateTime, tv_category, tv_wrongCount;
    public IMyViewHolderClicks mListener;
    View view;
    WrongAnswerItem wrongAnswerItem;

    public WrongAnswerItemHolder(View itemView, IMyViewHolderClicks listener) {
        super(itemView);
        mListener = listener;
        view = itemView;

        tv_dateTime = (TextView) itemView.findViewById(R.id.tv_datetime);
        tv_category = (TextView) itemView.findViewById(R.id.tv_category);
        tv_wrongCount = (TextView) itemView.findViewById(R.id.tv_wrongCount);
        itemView.setOnClickListener(this);
    }

    public void bindItemInfo(WrongAnswerItem wrongAnswerItem, int pos) {
        this.wrongAnswerItem = wrongAnswerItem;
        view.setTag(pos);
        tv_dateTime.setText(wrongAnswerItem.getDatetime());
        tv_category.setText(wrongAnswerItem.getCategoryList());
        tv_wrongCount.setText(wrongAnswerItem.getWrongQuizCount());
    }

    @Override
    public void onClick(View view) {
        mListener.onView(view);
    }

    public static interface IMyViewHolderClicks {
        public void onView(View caller);
    }

}
