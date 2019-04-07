package com.horus_vision.agrid;

import android.util.Log;

import java.util.HashMap;

public class APIConstants {


    public static DAO getmDAO() {
        return mDAO;
    }

    private static DAO mDAO;

    private final static HashMap<String, DAO.Role> userRoles = new HashMap<>();

    public static void initializeAPIConstants(DAO dao) {
        mDAO = dao;
        for (DAO.Role role : mDAO.roles.values()) {
            Log.d("Roles", role.camelCaseName);
            userRoles.put("u" + role.camelCaseName, role);
        }
    }

    public static DAO.Role getRoleFromUsername(String username){
        return userRoles.containsKey(username)? userRoles.get(username) : null;
    }
}
