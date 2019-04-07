package com.horus_vision.agrid.main_fragment_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horus_vision.agrid.DAO;
import com.horus_vision.agrid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActionsRecyclerAdapter extends RecyclerView.Adapter<ActionViewHolder> {
    List<String> actions;
    HashMap<String, DAO.Task> actionsObjects;

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view= li.inflate(R.layout.action_button_view, null);
        return new ActionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
        holder.onBind(actionsObjects.get(actions.get(position)));
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public void addActions(List<String> actions, HashMap<String, DAO.Task> actionsObjects) {
        this.actions = new ArrayList<>(actions);
        this.actionsObjects = new HashMap<>(actionsObjects);
        notifyDataSetChanged();
    }
}
