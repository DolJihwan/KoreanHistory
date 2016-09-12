package servicefactory.koreanhistory.adapter.review;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.activity.review.ReviewListActivity;
import servicefactory.koreanhistory.model.WrongAnswerItem;

/**
 * Created by saravana on 6/14/2015.
 */
public class WrongAnswerListAdapter extends RecyclerView.Adapter<WrongAnswerItemHolder> {

    ArrayList<WrongAnswerItem> contents;
    ReviewListActivity test = new ReviewListActivity();
    Context ctx;


    public WrongAnswerListAdapter(ArrayList<WrongAnswerItem> contents, Context ctx) {
        this.ctx = ctx;
        this.contents = contents;
    }

    @Override
    public WrongAnswerItemHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wronganswer_list_item_card_small, parent, false);
        return new WrongAnswerItemHolder(view, new WrongAnswerItemHolder.IMyViewHolderClicks() {
            @Override
            public void onView(View caller) {
                Log.e("getting data", "view" + contents.get((int) caller.getTag()).getDatetime());

                String waDatetime = contents.get((int) caller.getTag()).getDatetime();

                Bundle args = new Bundle();
                args.putString("datetime", waDatetime);

                Log.i("HelloTest" , " datetime:: " + waDatetime);

                Toast.makeText(ctx, contents.get((int) caller.getTag()).getDatetime(), Toast.LENGTH_LONG).show();

            }
        }) {
        };

    }

    @Override
    public void onBindViewHolder(WrongAnswerItemHolder holder, int position) {
        WrongAnswerItem wrongAnswerItem = contents.get(position);
        holder.bindItemInfo(wrongAnswerItem, position);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
