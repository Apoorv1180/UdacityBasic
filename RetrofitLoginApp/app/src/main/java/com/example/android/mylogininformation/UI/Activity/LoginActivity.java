package com.example.android.mylogininformation.UI.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.mylogininformation.Models.Data;
import com.example.android.mylogininformation.Models.Post;
import com.example.android.mylogininformation.Network.APIService;
import com.example.android.mylogininformation.Network.ApiUtils;
import com.example.android.mylogininformation.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    ProgressDialog progress;
    private EditText userEmail, userPassword;
    private APIService mAPIService;
    private String strUserEmail, strUserPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showProgressDialog();
        // Get SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Set UI
        Button submitButton = findViewById(R.id.bt_submit);
        userEmail = findViewById(R.id.ed_username);
        userPassword = findViewById(R.id.ed_password);
        mAPIService = ApiUtils.getAPIService();

        // get value from existing preference
        strUserEmail = sharedPreferences.getString("username", null);
        strUserPassword = sharedPreferences.getString("password", null);

        if (!TextUtils.isEmpty(strUserEmail) && !TextUtils.isEmpty(strUserPassword)) {
            sendPost(strUserEmail, strUserPassword);
        } else {
            progress.dismiss();
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isNetworkConnected()) {
                    strUserEmail = userEmail.getText().toString();
                    strUserPassword = userPassword.getText().toString();
                    if (!TextUtils.isEmpty(strUserEmail) && !TextUtils.isEmpty(strUserPassword)) {
                        showProgressDialog();
                        sendPost(strUserEmail, strUserPassword);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_SHORT).show();
            }

        });


    }

    //Handle the redirection after the response
    private void handleRedirection(Data data) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("username", data.getUsername());
        intent.putExtra("bio", data.getBio());
        intent.putExtra("email", data.getEmail());
        intent.putExtra("image_path", data.getPic());
        startActivity(intent);

    }

    //Checking Network Connectivity
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void sendPost(final String email, final String pass) {
        mAPIService.savePost(email, pass).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                if (response.isSuccessful()) {
                    Log.i("API RESPONSE", "Data submitted to API." + response.body().getData());
                    Data data = response.body().getData();
                    // put value in preference on button click
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", email);
                    editor.putString("password", pass);
                    editor.commit();
                    progress.dismiss();
                    handleRedirection(data);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("API RESPONSE", "Unable to submit Data to API.");
                progress.dismiss();
            }
        });

    }
}
