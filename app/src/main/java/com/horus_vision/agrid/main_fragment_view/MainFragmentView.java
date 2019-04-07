package com.horus_vision.agrid.main_fragment_view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.horus_vision.agrid.APIConstants;
import com.horus_vision.agrid.DAO;
import com.horus_vision.agrid.DaoHelper;
import com.horus_vision.agrid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragmentView extends Fragment {

    private String category = "Main Fragment View";

    private View fragmentView = null;

    protected TextView titleView;

    protected RecyclerView recyclerView, actionsRecyclerView;
    protected MainRecyclerAdapter mainRecyclerAdapter;
    protected ActionsRecyclerAdapter actionsRecyclerAdapter;

    protected Spinner selectionSpinner;

    protected List<String> selections = new ArrayList<>();
    protected HashMap<String, DAO.Asset> selectionsObjects =  new HashMap<>();

    protected List<String> actions = new ArrayList<>();
    protected HashMap<String, DAO.Task> actionsObjects = new HashMap<>();

    private DaoHelper.UISkeleton uiSkeleton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.main_fragment_view, null);
        initViews();
        return fragmentView;
    }

    private void initViews(){
        titleView = fragmentView.findViewById(R.id.fragmentTitle);
        recyclerView = fragmentView.findViewById(R.id.mainRecyclerView);
        actionsRecyclerView = fragmentView.findViewById(R.id.actionsRecyclerView);
        selectionSpinner = fragmentView.findViewById(R.id.selectionSpinner);
    }

    public void setCategory(String title) {
        category = title;
        if (fragmentView != null) {
            titleView.setText(category);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    private void setupUI() {
        titleView.setText(category);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                RecyclerView.VERTICAL);
        LinearLayoutManager mainLinearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        LinearLayoutManager actionsLinearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(mainLinearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        actionsRecyclerView.setLayoutManager(actionsLinearLayoutManager);
        actionsRecyclerView.addItemDecoration(dividerItemDecoration);

        mainRecyclerAdapter = new MainRecyclerAdapter();
        recyclerView.setAdapter(mainRecyclerAdapter);


        setupSelectionSpinner();

    }


    private void setupSelectionSpinner(){
        if(uiSkeleton.uiAssetsPerCategory.containsKey(category)) {
            selections.clear();
            selectionsObjects.clear();
            for (DAO.Asset asset : uiSkeleton.uiAssetsPerCategory.get(category)) {
                selections.add(asset.camelCaseName);
                selectionsObjects.put(asset.camelCaseName, asset);
            }
        }

        ArrayAdapter<String> selectionAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, selections);
        selectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selectionSpinner.setAdapter(selectionAdapter);

        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO select entity to interact
                setupActionsRecycler(selectionsObjects.get(selections.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

    }

    private void setupActionsRecycler(DAO.Asset asset){
        actions.clear();
        actionsObjects.clear();
        if(uiSkeleton.uiTaskPerAsset.containsKey(asset)) {
            Log.d("MainFragmnent","Should Have tasks");
            for (DAO.Task task : uiSkeleton.uiTaskPerAsset.get(asset)) {
                DAO.Role catRole = null;
                for(DAO.Role role: APIConstants.getmDAO().roles.values()){
                    if(role.camelCaseName.equals(category)){
                        catRole = role;
                        break;
                    }
                }
                if(catRole != null) {
                    boolean relativeTask= false;
                    for (String performerID : task.performersID) {
                        if (performerID.equals(catRole.id)) {
                            relativeTask =true;
                        }
                    }
                    if(relativeTask){
                        Log.d("MainFragmnent",task.camelCaseName);
                        actions.add(task.camelCaseName);
                        actionsObjects.put(task.camelCaseName,task);
                    }
                }
            }
            actionsRecyclerAdapter = new ActionsRecyclerAdapter();
            actionsRecyclerAdapter.addActions(actions, actionsObjects);
            actionsRecyclerView.setAdapter(actionsRecyclerAdapter);

        }
        else{
            Log.d("MainFragmnent","Empty");
            //Empty recycler
        }
    }


    public void setUiSkeleton(DaoHelper.UISkeleton uiSkeleton) {
        this.uiSkeleton = uiSkeleton;
    }
}
