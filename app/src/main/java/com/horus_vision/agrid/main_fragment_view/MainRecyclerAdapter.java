package com.horus_vision.agrid.main_fragment_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horus_vision.agrid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainCardView> {

    List<MainCardView> items = new ArrayList<>();

    @NonNull
    @Override
    public MainCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view= li.inflate(R.layout.main_card_view, null);

        return new MainCardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCardView holder, int position) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(){
        //add item to list
        notifyDataSetChanged();
    }
}
