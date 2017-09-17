package com.example.android.footballscorecounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Initialising the score for both teams
    int scoreA = 0;
    int scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    // Text view to display score of Team A
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    // Add function to add six to team A
    public void addSixForTeamA(View v) {
        scoreA = scoreA + 6;
        displayForTeamA(scoreA);
    }

    // Add function to add three to team A
    public void addThreeForTeamA(View v) {
        scoreA = scoreA + 3;
        displayForTeamA(scoreA);
    }


    // Add function to add two to team A
    public void addTwoForTeamA(View v) {
        scoreA = scoreA + 2;
        displayForTeamA(scoreA);
    }

    // Add function to add one to team A
    public void addOneForTeamA(View v) {
        scoreA = scoreA + 1;
        displayForTeamA(scoreA);
    }

    // Text view to display score of Team B
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    // Add function to add six to team B
    public void addSixForTeamB(View v) {
        scoreB = scoreB + 6;
        displayForTeamB(scoreB);
    }

    // Add function to add three to team B
    public void addThreeForTeamB(View v) {
        scoreB = scoreB + 3;
        displayForTeamB(scoreB);
    }

    // Add function to add two to team B
    public void addTwoForTeamB(View v) {
        scoreB = scoreB + 2;
        displayForTeamB(scoreB);
    }

    // Add function to add one to team B
    public void addOneForTeamB(View v) {
        scoreB = scoreB + 1;
        displayForTeamB(scoreB);
    }

    //RESET SCORE
    public void resetGame(View v) {
        scoreB = 0;
        scoreA = 0;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }


}
