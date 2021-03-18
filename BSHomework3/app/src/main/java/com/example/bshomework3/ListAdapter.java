package com.example.bshomework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    private List<Pokemon> mItems;
    private Context mContext;

    public ListAdapter(List<Pokemon> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvType;
        private LinearLayout llItem;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvType = itemView.findViewById(R.id.tv_type);
            llItem = itemView.findViewById(R.id.ll_layout);
        }

        public void bind(int position) {
            tvName.setText(mItems.get(position).getName());
            tvName.setText(mItems.get(position).getType());
            llItem.setBackgroundResource(mItems.get(position).getColor());
        }
    }
}
