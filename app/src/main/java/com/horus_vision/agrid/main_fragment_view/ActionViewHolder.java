package com.horus_vision.agrid.main_fragment_view;

import android.view.View;
import android.widget.Button;

import com.horus_vision.agrid.DAO;
import com.horus_vision.agrid.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActionViewHolder extends RecyclerView.ViewHolder {

    private static String string;
    private DAO.Task task;
    private Button actionButton;

    public ActionViewHolder(@NonNull View itemView) {
        super(itemView);
        actionButton = itemView.findViewById(R.id.action_button);
    }

    public void onBind(DAO.Task task){
        this.task =task;
        actionButton.setText(beautifulName(task.name));
    }

    private String beautifulName(String name){
        name = name.toLowerCase();
        String[] parts = name.split("_");
        name = "";
        for (String part : parts){
            name += toProperCase(part) + " ";
        }
        return name;
    }

    static String toProperCase(String s) {
        string = s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
        return string;
    }
}
