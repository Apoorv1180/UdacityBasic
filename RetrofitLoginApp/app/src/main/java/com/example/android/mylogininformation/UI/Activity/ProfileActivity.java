package com.example.android.mylogininformation.UI.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.mylogininformation.R;

import java.net.MalformedURLException;
import java.net.URL;


public class ProfileActivity extends AppCompatActivity {

    private String userName, userBio, userImage, userEmail;
    private TextView textUserName, textUserBio, textUserEmail;
    private ImageView imageView;
    private Button viewMyAppsButton;
    private int backCounter = 0;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Fetching shared preference in order to maintain session
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent intent = getIntent();
        userName = intent.getExtras().getString("username");
        userBio = intent.getExtras().getString("bio");
        userImage = intent.getExtras().getString("image_path");
        userEmail = intent.getExtras().getString("email");

        textUserName = (TextView) findViewById(R.id.userName);
        textUserBio = (TextView) findViewById(R.id.userBio);
        textUserEmail = (TextView) findViewById(R.id.userEmail);
        imageView = (ImageView) findViewById(R.id.profileImage);
        textUserName.setText(userName);
        textUserBio.setText(userBio);
        textUserEmail.setText(userEmail);
        viewMyAppsButton = (Button) findViewById(R.id.buttonViewAllApps);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        // Using Glide to upload Image to the image view
        try {
            URL image_url = new URL(userImage);
            Glide.with(this).load(image_url).apply(options).into(imageView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // View all the installed Apps on the button click
        viewMyAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ViewInstalledAppsActivity.class);
                startActivity(intent);
            }
        });


    }

    //Providing Double Back Pressed Exit Functionality
    @Override
    public void onBackPressed() {
        backCounter++;
        if (backCounter > 1) {
            this.finish();
        } else {
            Toast.makeText(getApplicationContext(), "Press back to exit", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backCounter = 0;
            }
        }, 2000);
    }

    //LOGOUT Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Method to logout of the session and return to the Login Activity
    private void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", null);
        editor.putString("password", null);
        editor.commit();

        // Launching the login activity
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
