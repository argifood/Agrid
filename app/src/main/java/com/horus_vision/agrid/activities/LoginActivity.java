package com.horus_vision.agrid.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.horus_vision.agrid.APIConstants;
import com.horus_vision.agrid.Constants;
import com.horus_vision.agrid.DAO;
import com.horus_vision.agrid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {

    private ImageView logoImage;
    private TextView bookITextView;
    private EditText usernameText;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;

    private Button loginButton;

    private DAO myDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initViews();
        afterAnimationView.setVisibility(GONE);
        bookITextView.setVisibility(GONE);
        rootView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorSplashText));
        logoImage.setImageResource(R.drawable.logo);
        startAnimation();
        readDao();
        APIConstants.initializeAPIConstants(myDAO);
    }

    private void readDao() {
        try {
            String json = null;
            try {
                InputStream is = getAssets().open("Farm.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            myDAO = DAO.createFromJSON(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        logoImage = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.logoImageView);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);

        usernameText = findViewById(R.id.emailEditText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this::tryLogIn);
    }

    private void tryLogIn(View v){
        //TEST BUTTON

        //END TEST BUTTON
        //ACTUALFUNTION
        String username = usernameText.getText().toString();
        if( APIConstants.getRoleFromUsername(username) != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.roleExtra, username);
            startActivity(intent);
            this.finish();
        }
        else{
            Toast.makeText(this, "Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = logoImage.animate();
        logoImage.setAlpha(0.0f);
        viewPropertyAnimator.alpha(1.0f).y(50f);
        viewPropertyAnimator.setDuration(1500);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
