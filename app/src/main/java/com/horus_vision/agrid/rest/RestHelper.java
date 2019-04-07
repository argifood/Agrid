package com.horus_vision.agrid.rest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.horus_vision.agrid.rest.Constants;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestHelper {
    // Android is device type 2
    static final String ANDROID_DEVICE_TYPE = "2";
    static final String ANDROID_DEVICE_TOKEN = "00000000000000000000000";
    static final String ANDROID_ANDROID = "1";
}

//    // Registratioin
//    public interface OnRegisterListener
//    {
//        public void onRegister(String memberID);
//    }
//
//    // Login
//    public interface OnLoginListener
//    {
//        public void onLogin(User me, long loginTimestamp, String currentVersion);
//    }
//
//    // places
//    public interface OnPlacesResultListener
//    {
//        public void onPlacesResult(JSONArray results);
//    }
//
//    // schalke places
//    public interface OnSchalkePlacesResultListener
//    {
//        public void onPlacesResult(List<Place> places);
//    }
//
//    // questions
//    public interface OnQuestionsResultListener
//    {
//        public void onQuestionsResult(List<Question> questions);
//    }
//
//    // playerCards
//    public interface OnPlayerCardsReceivedListener
//    {
//        public void onPlayerCardsReceived(List<PlayerCard> playerCards);
//    }
//
//    // event
//    public interface OnEventReceivedListener
//    {
//        public void onEventReceived(Event event);
//    }
//
//    public class UserScore extends User{
//        public int score = -1;//generic for [duels, lineups, distance, cones, goalie]
//    }
//
//    // leaderboard
//    public interface OnLeaderboardReceivedListener
//    {
//        public void onLeaderboardReceived(List<UserScore> experience,
//                                          List<UserScore> duels,
//                                          List<UserScore> lineups,
//                                          List<UserScore> distance );
//    }
//
//    // Cones results
//    public interface OnConesResultsReceivedListener
//    {
//        public void onConesResultsReceived(List<UserScore> results);
//    }
//
//    // Goalie results
//    public interface OnGoalieResultsReceivedListener
//    {
//        public void onGoalieResulstsReceived(List<UserScore> results);
//    }
//
//    // Get user
//    public interface OnUserInfoReceivedListener
//    {
//        public void onUserInfoReceived(User user);
//    }
//
//    // inbox
//    public interface OnMessagesReceivedListener
//    {
//        void onMessagesReceived(List<String> messages);
//    }
//
//    // html
//    public interface OnHtmlResponseListener
//    {
//        void onContentReceived(String content);
//    }
//
//    public class Question {
//        public String question;
//        public String question_de;
//        public int correctAnswer;
//        public int id;
//        public int numAnswers;
//        public List<Answer> answers = new ArrayList<>();
//    }
//    public class Answer {
//        public String text;
//        public String text_de;
//        public String image;
//        public Answer(String text_, String text_de_,String image_) {
//            text = text_; image = image_; text_de = text_de_;
//        }
//    }
//
//    private LoginParams loginParams = new LoginParams();
//    public class LoginParams
//    {
//        public String deviceToken;
//        public String deviceType;
//        public String memberId;
//        public String android;
//        public String facebookID;
//    }
//
//    private UpdateUserParams userParams = new UpdateUserParams();
//    public class UpdateUserParams {
//        public String firstName;
//        public String lastName;
//        public String memberId;
//        public String userImage;
//        public String facebookID;
//        public String gender;
//        public String birthYear;
//    }
//
//    private interface OnResponseListener {
//        public void onResponse(JSONObject response);
//    }
//
//    // Constants
//    final static String TAG = "RESTHelper";
//    final static String BASE_URL = "http://schalke-api.forwardgame.net/";
//
//    // Members
//    String url;
//    RequestQueue requestQueue;
//
//    // Dummy test List
//    HashMap<String, String> attributes;
//
//    // Context related members
//    Activity parentActivity;
//    SharedPreferences storedValues;
//    AppSettings app;
//
//    public RestHelper(Activity activity) {
//        parentActivity = activity;
//
//        requestQueue = Volley.newRequestQueue(activity);
//        attributes = new HashMap<>();
//
//        // Get preferences
//        storedValues = AppSettings.getRESTPreferences();
//        app = AppSettings.getInstance();
//
//        if(app.hasUserRegistered())
//            createLoginParams(AppSettings.getMemberID());
//    }
//
//    public void sendMessage(String message)
//    {
//        Map<String,String> params = new HashMap<>();
//        params.put(Constants.MessageParams.MESSAGE, message);
//
//        sendPOST(Constants.Endpoints.MESSAGE, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//        });
//    }
//
//    public void registerUser(String deviceID, final OnRegisterListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.RegisterParams.DEVICE_ID, deviceID);
//
//        // add response parser
//        sendPOST(Constants.Endpoints.REGISTER, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    String memberID = response.getString(Constants.RegisterResponse.MEMBER_ID);
//                    createLoginParams(memberID);
//                    if(listener != null)
//                        listener.onRegister(memberID);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    private void createLoginParams(String memberID) {
//        loginParams.memberId = memberID;
//        loginParams.android = ANDROID_ANDROID;
//        loginParams.deviceToken = ANDROID_DEVICE_TOKEN;
//        loginParams.deviceType = ANDROID_DEVICE_TYPE;
//        loginParams.facebookID = "";
//    }
//
//    public LoginParams getLoginParams() {
//        return loginParams;
//    }
//
//    public UpdateUserParams getUserParams() {
//        return userParams;
//    }
//
//    public void loginUser(LoginParams loginParams, final OnLoginListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.DEVICE_TOKEN, loginParams.deviceToken);
//        params.put(Constants.LoginParams.DEVICE_TYPE, loginParams.deviceType);
//        params.put(Constants.LoginParams.ANDROID, loginParams.android);
//        params.put(Constants.LoginParams.FACEBOOK_ID, loginParams.facebookID);
//        params.put(Constants.LoginParams.MEMBER_ID, loginParams.memberId);
//
//        // add response parser
//        sendPOST(Constants.Endpoints.LOGIN, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    User me = new User();
//                    me.fName = response.getString(Constants.LoginResponse.FIRST_NAME);
//                    me.lName = response.getString(Constants.LoginResponse.LAST_NAME);
//                    String imgURL = response.getString(Constants.LoginResponse.USER_IMAGE);
//                    if(!imgURL.equals("")) me.setImage(imgURL);
//                    me.gender = response.getString(Constants.LoginResponse.GENDER);
//                    me.birthYear= response.getString(Constants.LoginResponse.BIRTH_YEAR);
//                    me.duelsWon = response.getInt("duels_won");
//                    me.distanceTravelled = response.getInt("distance_travelled");
//                    //me.level = response.getInt("experience_level");
//                    me.lineupsCompleted = response.getInt("lineups_completed");
//                    me.xp = response.getInt("experience_points");
//                    me.eventsAttended = response.getInt("event_attend");
//                    me.coins = response.getInt("ball_points");
//                    me.numUnreadMessages = response.getInt("numberOfUnreadMessages");
//
//                    String version = response.getString("android");
//
//                    long timestamp = response.getLong("timestamp");
//
//                    createUserParams(me.fName, me.lName, imgURL, me.gender, me.birthYear);
//                    if(listener != null)
//                        listener.onLogin(me, timestamp, version);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    public void getEvent(final OnEventReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Endpoints.EVENT, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    Event event = Event.createFromJSON(response.getJSONObject("event"));
//
//                    //List<Question> questions = parseQuestions(questionsObj);
//                    if(listener != null)
//                        listener.onEventReceived(event);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    public void getQuestions(final OnQuestionsResultListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Constants.Endpoints.QUESTIONS, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    JSONArray questionsObj = response.getJSONArray("questions");
//
//                    List<Question> questions = parseQuestions(questionsObj);
//                    if(listener != null)
//                        listener.onQuestionsResult(questions);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    public void getSingleQuestion(final OnQuestionsResultListener listener, boolean hard)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        if(!hard)
//            params.put("type", "easy");
//        else
//            params.put("type", "hard");
//        // add response parser
//        sendPOST(Constants.Endpoints.SINGLE_QUESTION, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    JSONArray questionsObj = response.getJSONArray("questions");
//
//                    List<Question> questions = parseQuestions(questionsObj);
//                    if(listener != null)
//                        listener.onQuestionsResult(questions);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    public void getPlayerCards(final OnPlayerCardsReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Constants.Endpoints.PLAYER_CARDS, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    JSONArray players = response.getJSONArray("players");
//                    ArrayList<PlayerCard> playerCards = new ArrayList<>();
//                    for(int i = 0; i < players.length(); ++i) {
//                        playerCards.add(PlayerCard.createFromJSON(players.getJSONObject(i)));
//                    }
//
//                    if(listener != null)
//                        listener.onPlayerCardsReceived(playerCards);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    public void getHTML(final OnHtmlResponseListener listener, String endpoint)
//    {
//        String key = "";
//        if(endpoint.equals(Endpoints.GET_TERMS))
//            key = "terms";
//        else if(endpoint.equals(Endpoints.GET_PRIVACY))
//            key = "privacy";
//        else {
//            if(listener!=null)
//                listener.onContentReceived("Invalid Endpoint!");
//            return;
//        }
//        final String finKey = key;
//
//        String language = "en";
//        if(app.getLocale().getCountry().equals("DE"))
//            language="de";
//
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put("language", language);
//
//        // add response parser
//        sendPOST(endpoint, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    String content = response.getString(finKey);
//
//                    //List<Question> questions = parseQuestions(questionsObj);
//                    if(listener != null)
//                        listener.onContentReceived(content);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    public void getLeaderboard(final OnLeaderboardReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Constants.Endpoints.LEADERBOARD, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try
//                {
//                    JSONObject leaderboard = response.getJSONObject("leaderboard");
//                    JSONArray experience = leaderboard.getJSONArray("experience");
//                    JSONArray distance = leaderboard.getJSONArray("distance");
//                    JSONArray duels = leaderboard.getJSONArray("duels");
//                    JSONArray lineups = leaderboard.getJSONArray("lineups");
//                    List<UserScore> xpList = new ArrayList<>(),
//                            distanceList = new ArrayList<>(),
//                            duelsList = new ArrayList<>(),
//                            lineupsList = new ArrayList<>();
//
//                    // xp
//                    for(int i = 0; i < experience.length(); i++)
//                    {
//                        UserScore score = new UserScore();
//                        JSONObject user = experience.getJSONObject(i);
//                        score.fName = user.getString("fName");
//                        score.lName = user.getString("lName");
//                        score.xp = Integer.valueOf(user.getString("experience"));
//                        score.memberID = user.getString("member_id");
//                        score.setImage(user.getString("image"));
//                        xpList.add(score);
//                    }
//                    // duels
//                    for(int i = 0; i < duels.length(); i++)
//                    {
//                        UserScore score = new UserScore();
//                        JSONObject user = duels.getJSONObject(i);
//                        score.fName = user.getString("fName");
//                        score.lName = user.getString("lName");
//                        score.xp = Integer.valueOf(user.getString("experience"));
//                        score.memberID = user.getString("member_id");
//                        score.setImage(user.getString("image"));
//                        score.score = Integer.valueOf(user.getString("duels"));
//                        duelsList.add(score);
//                    }
//                    // distance
//                    for(int i = 0; i < distance.length(); i++)
//                    {
//                        UserScore score = new UserScore();
//                        JSONObject user = distance.getJSONObject(i);
//                        score.fName = user.getString("fName");
//                        score.lName = user.getString("lName");
//                        score.xp = Integer.valueOf(user.getString("experience"));
//                        score.memberID = user.getString("member_id");
//                        score.setImage(user.getString("image"));
//                        score.score = Integer.valueOf(user.getString("distance"));
//                        distanceList.add(score);
//                    }
//                    // lineups
//                    for(int i = 0; i < lineups.length(); i++)
//                    {
//                        UserScore score = new UserScore();
//                        JSONObject user = lineups.getJSONObject(i);
//                        score.fName = user.getString("fName");
//                        score.lName = user.getString("lName");
//                        score.xp = Integer.valueOf(user.getString("experience"));
//                        score.memberID = user.getString("member_id");
//                        score.setImage(user.getString("image"));
//                        score.score = Integer.valueOf(user.getString("lineups"));
//                        lineupsList.add(score);
//                    }
//
//                    if(listener != null)
//                        listener.onLeaderboardReceived(xpList, duelsList, lineupsList, distanceList);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//    private List<Question> parseQuestions(JSONArray questionsObj) {
//        List<Question> ret = new ArrayList<>(questionsObj.length());
//        try {
//            for (int i = 0; i < questionsObj.length(); i++) {
//                JSONObject question = questionsObj.getJSONObject(i);
//
//                // Create Question
//                ret.add(i, parseQuestionObj(question));
//            }
//        } catch(JSONException e) {
//            Log.d(TAG, e.getMessage());
//        }
//        return ret;
//    }
//
//    public Question parseQuestionObj(JSONObject question)
//    {
//        Question q = new Question();
//        try {
//            // Create Question
//            q.question_de = question.getString("question");
//            q.question = question.getString("question_en");
//            q.correctAnswer = Integer.valueOf(question.getString("correctAnswer"));
//            JSONArray answers = question.getJSONArray("answers");
//            q.numAnswers = answers.length();
//
//            // ID test
//            q.id = Integer.valueOf(question.getString("id"));
//            for (int j = 0; j < answers.length(); j++) {
//                JSONObject ansObj = answers.getJSONObject(j);
//                q.answers.add(j, new Answer(ansObj.getString("text_en"), ansObj.getString("text"), ansObj.getString("image")));
//            }
//        } catch(JSONException e) {
//            Log.d(TAG, e.getMessage());
//        }
//        return q;
//    }
//
//    public JSONObject createQuestionOBJ(Question q)
//    {
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("id", String.valueOf(q.id));
//            obj.put("question", q.question_de);
//            obj.put("question_en", q.question);
//            obj.put("correctAnswer", String.valueOf(q.correctAnswer));
//            // JSON Array of answers
//            JSONArray answers = new JSONArray();
//            for(int i = 0; i < q.numAnswers; i++)
//            {
//                JSONObject answer = new JSONObject();
//                answer.put("text",q.answers.get(i).text_de);
//                answer.put("text_en",q.answers.get(i).text);
//                answer.put("image", q.answers.get(i).image);
//                answers.put(answer);
//            }
//            obj.put("answers",answers);
//        } catch(JSONException e) {
//            Log.d(TAG, e.getMessage());
//        }
//        return obj;
//    }
//
//    private void createUserParams(String firsName, String lastName, String userImage, String gender, String birthYear) {
//        userParams.birthYear = birthYear;
//        userParams.userImage = userImage;
//        userParams.facebookID = "";
//        userParams.firstName = firsName;
//        userParams.lastName = lastName;
//        userParams.gender = gender;
//    }
//
//    public void getPlaces(Location location, final OnPlacesResultListener listener) {
//        String finalURL = Constants.GooglePlaces.REQUEST
//                +String.valueOf(location.getLatitude())+","
//                +String.valueOf(location.getLongitude())
//                +Constants.GooglePlaces.REQUEST_AFTER_LOC
//                +Constants.GooglePlaces.KEY;
//
//        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
//        // that expects a JSON Array Response.
//        // To fully understand this, I'd recommend readng the office docs: https://developer.android.com/training/volley/index.html
//        StringRequest arrReq = new StringRequest(Request.Method.GET, finalURL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Check the length of our response (to see if the user has any repos)
//                        if (response.length() > 0) {
//                            try {
//                                // For each repo, add a new line to our repo list.
//                                JSONObject jsonObj = new JSONObject(response);
//                                JSONArray places = jsonObj.getJSONArray("results");
//                                listener.onPlacesResult(places);
//                                //String lastUpdated = jsonObj.get("updated_at").toString(); // PROPERTY
//
//                                // This is wrong, many objects with same attribute incoming
//                                //updateAttribute("name", repoName);
//                            } catch (JSONException e) {
//                                // If there is an error then output this to the logs.
//                                Log.e(TAG, "Invalid JSON Object.");
//                            }
//
//                        }
//
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // If there a HTTP error then add a note to our repo list.
//                        Log.d(TAG, "Error while calling REST API");
//                        Log.e("Volley", error.toString());
//                    }
//                }
//        );
//        // Add the request we just defined to our request queue.
//        // The request queue will automatically handle the request as soon as it can.
//        requestQueue.add(arrReq);
//
//    }
//
//    public void getSchalkePlaces(final OnSchalkePlacesResultListener listener) {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Constants.Endpoints.PLACES, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray places = response.getJSONArray("places");
//                    // Create places objects
//                    List<Place> ret = new ArrayList<>();
//                    for(int i =0; i< places.length();i++)
//                        ret.add(Place.createFromJSON(places.getJSONObject(i)));
//
//                    if(listener != null)
//                        listener.onPlacesResult(ret);
//
//                } catch (JSONException e) {
//                    // If there is an error then output this to the logs.
//                    Log.e(TAG, "Invalid JSON Object.");
//                }
//            }
//        });
//    }
//
//
//    private void sendPOST(final String path, final Map<String, String> params, final OnResponseListener listener) {
//
//        // First, we insert the endpoint into the basepath url.
//        this.url = this.BASE_URL + path;
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        if (response.length() > 0) {
//                            //listener.onResponse(response);
//                            try {
//                                JSONObject json = new JSONObject(response);
//                                listener.onResponse(json);
//                            } catch (JSONException e) {
//                                Log.e(TAG, e.getMessage());
//                            }
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        //Log.d("Error.Response", error.getMessage());
//                        ((MainActivity) parentActivity).onConnectionError();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params_ = params;
//                //params.put(key, value);
//
//                return params;
//            }
//        };
//        requestQueue.add(postRequest);
//    }
//
//
//    ////////////////////////////////////////////////////////////////////////////////
//    // No response endpoints
//    public void addCoins(int amount)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("amountOfCoins", String.valueOf(amount));
//
//        // add response parser
//        sendPOST(Endpoints.COINS_ADD, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//    //remove
//    public void removeCoins(int amount)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("amountOfCoins", String.valueOf(amount));
//
//        // add response parser
//        sendPOST(Endpoints.COINS_REMOVE, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Player card
//    public void addPlayerCard(String cardID)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("playerCardId", cardID);
//
//        // add response parser
//        sendPOST(Endpoints.PLAYER_ADD, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    public void removePlayerCard(String cardID)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("playerCardId", cardID);
//
//        // add response parser
//        sendPOST(Endpoints.PLAYER_REMOVE, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // XP
//    public void addXP(int amount)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("amountOfPoints", String.valueOf(amount));
//
//        // add response parser
//        sendPOST(Endpoints.XP_ADD, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // meters
//    public void addMeters(int amount)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("amountOfMeters", String.valueOf(amount));
//
//        // add response parser
//        sendPOST(Endpoints.METERS_ADD, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Duels
//    public void addDuelsWon(int amount)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("amountOfDuelsWon", String.valueOf(amount));
//
//        // add response parser
//        sendPOST(Endpoints.DUEL_WIN_ADD, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Duels
//    public void addEventAttend()
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//
//        // add response parser
//        sendPOST(Endpoints.EVENT_ADD_ATTEND, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Add number of complete lineups
//    public void addLineupsComplete(int amount)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("amountOfLineups", String.valueOf(amount));
//
//        // add response parser
//        sendPOST(Endpoints.LINEUPS_ADD_COMPLETE, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Lineup complete
//    public void onLineupComplete(String eventID)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("event_id",eventID);
//
//        // add response parser
//        sendPOST(Endpoints.LINEUP_COMPLETE, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Goalie result
//    public void addGoalieGameResult(int score)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("result",String.valueOf(score));
//
//        // add response parser
//        sendPOST(Endpoints.GOALIE_ADD_RESULT, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    // Cones result
//    public void addConesGameResult(int score)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, AppSettings.getMemberID());
//        params.put("result",String.valueOf(score));
//
//        // add response parser
//        sendPOST(Endpoints.CONES_ADD_RESULT, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        });
//    }
//
//    ////////////////////////////////////////////////////////////////////////
//    // GET Goalie/Cones results
//    public void getConesResults(final OnConesResultsReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Endpoints.CONES_GET_RESULTS, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    List<UserScore> ret = new ArrayList<>();
//                    JSONObject result = response.getJSONObject("conesResults");
//                    JSONArray users = result.getJSONArray("users");
//                    for(int i = 0; i < users.length(); i++) {
//                        JSONObject score = users.getJSONObject(i);
//                        UserScore u = new UserScore();
//                        u.memberID = score.getString("member_id");
//                        u.fullName = score.getString("user_name");
//                        String imgURL = score.getString("image");
//                        if(!imgURL.equals("")) u.setImage(imgURL);
//                        u.score = score.getInt("result");
//                        ret.add(u);
//                    }
//                    if(listener!=null)
//                        listener.onConesResultsReceived(ret);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void getGoalieResults(final OnGoalieResultsReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//
//        // add response parser
//        sendPOST(Endpoints.GOALIE_GET_RESULTS, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    List<UserScore> ret = new ArrayList<>();
//                    JSONObject result = response.getJSONObject("goalieResults");
//                    JSONArray users = result.getJSONArray("users");
//                    for(int i = 0; i < users.length(); i++) {
//                        JSONObject score = users.getJSONObject(i);
//                        UserScore u = new UserScore();
//                        u.memberID = score.getString("member_id");
//                        u.fullName = score.getString("user_name");
//                        String imgURL = score.getString("image");
//                        if(!imgURL.equals("")) u.setImage(imgURL);
//                        u.score = score.getInt("result");
//                        ret.add(u);
//                    }
//                    if(listener!=null)
//                        listener.onGoalieResulstsReceived(ret);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    // Get user info
//    public void getUser(String memberID, final OnUserInfoReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, memberID);
//
//        // add response parser
//        sendPOST(Endpoints.GET_USER, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    User u = new User();
//                    u.fName = response.getString(Constants.LoginResponse.FIRST_NAME);
//                    u.lName = response.getString(Constants.LoginResponse.LAST_NAME);
//                    u.gender = response.getString(Constants.LoginResponse.GENDER);
//                    u.birthYear= response.getString(Constants.LoginResponse.BIRTH_YEAR);
//                    u.duelsWon = response.getInt("duels_won");
//                    u.distanceTravelled = response.getInt("distance_travelled");
//                    //u.level = response.getInt("experience_level");
//                    u.lineupsCompleted = response.getInt("lineups_completed");
//                    u.xp = response.getInt("experience_points");
//                    u.eventsAttended = response.getInt("event_attend");
//                    u.coins = response.getInt("ball_points");
//                    String imgURL = response.getString("userImage");
//                    if(!imgURL.equals("")) u.setImage(imgURL);
//                    if(listener!=null)
//                        listener.onUserInfoReceived(u);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void getInboxMessages(final String memberID, final OnMessagesReceivedListener listener)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put(Constants.ENCRYPT, Constants.ENCRYPT_VALUE);
//        params.put(Constants.LoginParams.MEMBER_ID, memberID);
//
//        // add response parser
//        sendPOST(Endpoints.INBOX_GET_MESSAGES, params, new OnResponseListener() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray msgs = response.getJSONArray("messages");
//                    ArrayList<String> messages = new ArrayList<>();
//                    for(int i = 0; i < msgs.length(); i++)
//                        messages.add(msgs.getJSONObject(i).getString("message"));
//
//                    if(listener!=null)
//                        listener.onMessagesReceived(messages);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//
//}
