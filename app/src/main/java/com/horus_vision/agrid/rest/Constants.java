package com.horus_vision.agrid.rest;

import android.icu.lang.UProperty;

import com.android.volley.toolbox.StringRequest;

public class Constants {

    public static final String ENCRYPT = "encrypt";
    public static final String ENCRYPT_VALUE = "sXOGc4h4s4TTX0L6oPx2JYh74fluyGO7toluG9564";

    public class Endpoints {
        public static final String MESSAGE = "message";
        public static final String REGISTER = "register";
        public static final String LOGIN = "login";
        public static final String UPDATE_USER = "update_user";
        public static final String GET_USER = "get_user";
        public static final String QUESTIONS = "questions";
        public static final String SINGLE_QUESTION = "question";
        public static final String PLAYER_CARDS= "playerCards";
        public static final String LEADERBOARD= "get_leaderboard";
        public static final String PLACES = "places";
        public static final String EVENT = "get_event";

        public static final String PLAYER_ADD = "add_player";
        public static final String PLAYER_REMOVE= "remove_player";
        public static final String COINS_ADD= "add_coins";
        public static final String COINS_REMOVE = "remove_coins";
        public static final String METERS_ADD = "add_meters";
        public static final String XP_ADD = "add_xp";
        public static final String DUEL_WIN_ADD= "add_duel_win";
        public static final String LINEUPS_ADD_COMPLETE = "add_lineups_completed";
        public static final String LINEUP_COMPLETE = "complete_lineup";
        public static final String EVENT_ADD_ATTEND = "add_event_attend";

        // AR Games
        public static final String CONES_GET_RESULTS = "get_cones_results";
        public static final String GOALIE_GET_RESULTS = "get_goalie_results";

        public static final String CONES_ADD_RESULT = "make_result_cones";
        public static final String GOALIE_ADD_RESULT = "make_result_goalie";

        // inbox
        public static final String INBOX_GET_MESSAGES = "get_messages";

        // html
        public static final String GET_PRIVACY = "get_privacy";
        public static final String GET_TERMS = "get_terms";
    }

    // Endpoints parameters
    public class MessageParams {
        public static final String MESSAGE = "message";
    }

    public class RegisterParams {
        public static final String DEVICE_ID = "device_id";
    }

    public class LoginParams {
        // Used for push notification
        public static final String DEVICE_TOKEN = "device_token";
        public static final String DEVICE_TYPE = "device_type";
        public static final String MEMBER_ID = "member_id";
        public static final String ANDROID = "android";
        public static final String FACEBOOK_ID = "facebook_id";
    }

    public class UpdateUserParams {
        public static final String MEMBER_ID = LoginParams.MEMBER_ID;
        public static final String FACEBOOK_ID = LoginParams.FACEBOOK_ID;
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String USER_IMAGE = "userImage";
        public static final String GENDER = "gender";
        public static final String BIRTH_YEAR = "yearOfBirth";
    }

    // Endpoint response parameters
    public class RegisterResponse {
        public static final String MEMBER_ID = LoginParams.MEMBER_ID;
    }

    public class LoginResponse {
        public static final String FIRST_NAME = UpdateUserParams.FIRST_NAME;
        public static final String LAST_NAME = UpdateUserParams.LAST_NAME;
        public static final String USER_IMAGE = UpdateUserParams.USER_IMAGE;
        public static final String GENDER = UpdateUserParams.GENDER;
        public static final String BIRTH_YEAR = UpdateUserParams.BIRTH_YEAR;
    }

    public class GooglePlaces {
        public static final String KEY="AIzaSyCwdIoWbG6-yA-0UoL05VoLvWJBz1PSB9I";
        public static final String REQUEST = "https://maps.googleapis.com/maps/api/place/search/json?location=";
        public static final String REQUEST_AFTER_LOC= "&radius=300&types=food,taxi_stand,bar,atm,cafe,casino,grocery_or_supermarket,subway_station,store,school,restaurant,park,bus_station&sensor=false&key=";
    }
}




