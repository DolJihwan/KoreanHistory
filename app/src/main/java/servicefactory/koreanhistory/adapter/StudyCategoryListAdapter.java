package servicefactory.koreanhistory.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.activity.StudyMinorCategoryListActivity;
import servicefactory.koreanhistory.model.Category;

/**
 * Created by saravana on 6/14/2015.
 */
public class StudyCategoryListAdapter extends RecyclerView.Adapter<StudyCategoryItemHolder> {

    ArrayList<Category> contents;
    Intent intent;
    Context ctx;

    public StudyCategoryListAdapter(ArrayList<Category> contents, Context ctx) {
        this.intent = intent;
        this.ctx = ctx;
        this.contents = contents;
        Log.i("Adapter", "StudyCategoryListAdapter");
    }

    @Override
    public StudyCategoryItemHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        Log.i("Adapter", "onCreateViewHolder");
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item_card_small, parent, false);
        return new StudyCategoryItemHolder(view, new StudyCategoryItemHolder.IMyViewHolderClicks() {
            @Override
            public void onView(View caller) {
                Toast.makeText(ctx, contents.get((int) caller.getTag()).getCategoryMajor(), Toast.LENGTH_SHORT).show();
                Log.i("Adapter", "onView");

                Intent intent = new Intent(ctx, StudyMinorCategoryListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("majorCategory", contents.get((int) caller.getTag()).getCategoryMajor());
                ctx.startActivity(intent);
            }
        }) {
        };
    }

    @Override
    public void onBindViewHolder(StudyCategoryItemHolder holder, int position) {
        Log.i("Adapter", "onBindViewHolder");
        Category categoryItem = contents.get(position);
        holder.bindItemInfo(categoryItem, position);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
