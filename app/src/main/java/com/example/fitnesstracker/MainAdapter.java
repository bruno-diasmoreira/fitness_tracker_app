package com.example.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.model.MainItem;
import com.example.fitnesstracker.model.OnItemClickListener;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    List<MainItem> itemList;
    Context context;

    private OnItemClickListener listener;


    public MainAdapter(Context context, List<MainItem> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MainItem item = itemList.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }


        public void bind(MainItem item) {

            TextView itemTitle = itemView.findViewById(R.id.item_title);
            ImageView itemImg = itemView.findViewById(R.id.item_img);

            LinearLayout container = itemView.findViewById(R.id.btn_item);

            itemTitle.setText(item.getTextStringId());
            itemImg.setImageResource(item.getDrawableId());

            container.setOnClickListener(view -> listener.onClick(item.getId()));

        }


    }


}
