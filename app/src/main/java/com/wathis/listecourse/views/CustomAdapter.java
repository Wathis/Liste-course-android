package com.wathis.listecourse.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wathis.listecourse.R;
import com.wathis.listecourse.models.Memo;

import java.util.List;

/**
 * Created by mathisdelaunay on 18/01/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<Memo> memos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            titleTextView = v.findViewById(R.id.textView);
            descriptionTextView = v.findViewById(R.id.textViewDescription);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }
        public TextView getDescriptionTextView() {return descriptionTextView;}
    }

    public CustomAdapter(List<Memo> memos) {
        this.memos = memos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(this.memos.get(position).getTitre());
        viewHolder.getDescriptionTextView().setText(this.memos.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return this.memos.size();
    }
}
