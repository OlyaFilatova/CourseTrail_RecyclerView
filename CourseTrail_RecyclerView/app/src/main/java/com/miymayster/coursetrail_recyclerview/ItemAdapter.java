package com.miymayster.coursetrail_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Olga on 02.08.2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    ArrayList<ItemInfo> mItems;
    ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClick(int itemIndex);
    }

    public void swapArray(ArrayList<ItemInfo> items){
        mItems.clear();
        if(items != null) {
            mItems.addAll(items);
        }
        this.notifyDataSetChanged();
    }

    public ItemAdapter(ArrayList<ItemInfo> items, ItemClickListener itemClickListener){
        mItems = new ArrayList<>();
        mItems.addAll(items);
        mItemClickListener = itemClickListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if(mItems != null && position < mItems.size()){
            ItemInfo info = mItems.get(position);
            holder.bind(info.getImageResId(), info.getHeader(), info.getInfo());
        }
    }

    @Override
    public int getItemCount() {
        if(mItems == null) {
            return 0;
        }else{
            return mItems.size();
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView itemImage;
        TextView itemHeader;
        TextView itemInfo;
        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemHeader = (TextView) itemView.findViewById(R.id.item_header);
            itemInfo = (TextView) itemView.findViewById(R.id.item_info);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
        }
        public void bind(int resId, String header, String text){
            if(resId != -1){
                itemImage.setImageResource(resId);
            }else{
                itemImage.setImageResource(R.drawable.no_image_available);
            }
            itemHeader.setText(header);
            itemInfo.setText(text);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClick(position);
        }
    }
}
