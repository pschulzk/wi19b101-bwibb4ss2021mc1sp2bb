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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> implements View.OnClickListener {

    private List<MagicCard> mItems;
    private Context mContext;
    private ListItemClickListener mlistItemClickListener;
    private ItemViewHolder itemViewHolder;

    public ListAdapter(List<MagicCard> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        this.itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    interface ListItemClickListener {
        void onListItemClick(MagicCard item);
    }

    public void setOnListItemClickListener(ListItemClickListener listItemClickListener) {
        mlistItemClickListener = listItemClickListener;
    }

    public void swapData(List<MagicCard> p) {
        mItems = p;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (mlistItemClickListener != null) {
            int clickIndex = itemViewHolder.getAdapterPosition();
            MagicCard item = mItems.get(clickIndex);
            mlistItemClickListener.onListItemClick(item);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvProp;
        private final LinearLayout llItem;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvProp = itemView.findViewById(R.id.tv_type);
            llItem = itemView.findViewById(R.id.ll_layout);
        }

        public void bind(int position) {
            tvName.setText(mItems.get(position).getName());
            tvProp.setText(mItems.get(position).getRarity());
            llItem.setBackgroundResource(mItems.get(position).getColor());
        }
    }
}
