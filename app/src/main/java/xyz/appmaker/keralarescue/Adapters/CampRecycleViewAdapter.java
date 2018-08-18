package xyz.appmaker.keralarescue.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.appmaker.keralarescue.Interfaces.RecycleItemClickListener;
import xyz.appmaker.keralarescue.R;
import xyz.appmaker.keralarescue.Room.Camp.CampNames;

public class CampRecycleViewAdapter extends RecyclerView.Adapter<CampRecycleViewAdapter.ViewHolder> {
private List<CampNames> mDataset;
    RecycleItemClickListener listener;


// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public static class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public View view;
    public TextView tvName;
    public ViewHolder(View v) {
        super(v);
        view = v;
        tvName = view.findViewById(R.id.row_item);
    }
}

    // Provide a suitable constructor (depends on the kind of dataset)
    public CampRecycleViewAdapter(List<CampNames> myDataset,  RecycleItemClickListener listener) {
        this.mDataset = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CampRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_camp, parent, false);

        final ViewHolder vh = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e("TAG","onClick Recycle view "+vh.getPosition());
                listener.onItemClick(v, vh.getPosition());
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // holder.mTextView.setText(mDataset[position]);
        holder.tvName.setText(mDataset.get(position).getName());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


