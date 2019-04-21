package com.example.tempatfutsal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListFutsalAdapter extends RecyclerView.Adapter<ListFutsalAdapter.CategoryViewHolder> {



    public ListFutsalAdapter(Context context) {
        this.context = context;
    }

    private Context context;

    public ArrayList<Futsal> getListFutsal() {
        return listFutsal;
    }

    public void setListFutsal(ArrayList<Futsal> listFutsal) {
        this.listFutsal = listFutsal;
    }

    private ArrayList<Futsal> listFutsal;
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_futsal, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {
        categoryViewHolder.tvName.setText(getListFutsal().get(position).getName());
        categoryViewHolder.tvRemarks.setText(getListFutsal().get(position).getRemarks());
        Glide.with(context)
                .load(getListFutsal().get(position).getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(categoryViewHolder.imgPhoto);

    }




    @Override
    public int getItemCount() {
        return getListFutsal().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvRemarks;
        ImageView imgPhoto;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}

