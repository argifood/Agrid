package com.horus_vision.agrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoHelper {

    public static class UISkeleton{

        public List<String> uiCategories = new ArrayList<>();

        public Map<String, List<DAO.Asset>> uiAssetsPerCategory = new HashMap<>();

        public Map<DAO.Asset, List<DAO.Task>> uiTaskPerAsset = new HashMap<>();

        UISkeleton(){

        }

    }


    public static UISkeleton getUISkeletonFromRole(DAO mDao, DAO.Role role){
        UISkeleton uiSkeleton = new UISkeleton();

        uiSkeleton.uiCategories = role.associatedRoles;
        for(String id : role.assetPerRoles.keySet()){
            uiSkeleton.uiAssetsPerCategory.put(mDao.roles.get(id).camelCaseName, role.assetPerRoles.get(id));
        }

        for(DAO.Asset e : mDao.assets.values()){
            uiSkeleton.uiTaskPerAsset.put(e, e.assetAssociatedTasks);
        }
        return uiSkeleton;

    }

}
