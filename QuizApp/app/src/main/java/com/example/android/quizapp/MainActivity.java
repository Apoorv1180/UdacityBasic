package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int gradeOne, gradeTwo, gradeThree, gradeFour, gradeFive;
    boolean questionOneChecked, questionTwoChecked, questionFourChecked, questionFiveChecked;

    RadioGroup questionOneRadioGroup,
            questionTwoRadioGroup,
            questionFiveRadioGroup;
    CheckBox questionFourCheckedBoxOne,
            questionFourCheckedBoxTwo,
            questionFourCheckedBoxThree,
            questionFourCheckedBoxFour;
    EditText questionThreeEditText;
    int TotalGradesObtained;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Question One
        questionOneRadioGroup = (RadioGroup) findViewById(R.id.questionOneRadioGroup);
        //Question Two
        questionTwoRadioGroup = (RadioGroup) findViewById(R.id.questionTwoRadioGroup);
        //Question Three
        questionThreeEditText = (EditText) findViewById(R.id.questionThreeEditText);
        //Question Four
        questionFourCheckedBoxOne = (CheckBox) findViewById(R.id.questionFourCheckBoxOne);
        questionFourCheckedBoxTwo = (CheckBox) findViewById(R.id.questionFourCheckBoxTwo);
        questionFourCheckedBoxThree = (CheckBox) findViewById(R.id.questionFourCheckBoxThree);
        questionFourCheckedBoxFour = (CheckBox) findViewById(R.id.questionFourCheckBoxFour);
        //Question Five
        questionFiveRadioGroup = (RadioGroup) findViewById(R.id.questionFiveRadioGroup);
    }

    //Calculate gradeOne
    public void calculateQuestionOne(View v) {

        //Checking if the button is checked or not
        questionOneChecked = ((RadioButton) v).isChecked();

        // Checking which option was clicked and assigning the score accordingly
        switch (v.getId()) {
            case R.id.questionOneRadioButtonOne:
                if (questionOneChecked) {
                    gradeOne = 10;
                    break;
                }
            case R.id.questionOneRadioButtonTwo:
                if (questionOneChecked) {
                    gradeOne = 0;
                    break;
                }
            case R.id.questionOneRadioButtonThree:
                if (questionOneChecked) {
                    gradeOne = 0;
                    break;
                }
            case R.id.questionOneRadioButtonFour:
                if (questionOneChecked) {
                    gradeOne = 0;
                    break;
                }
        }

    }

    //Calculate gradeTwo
    public void calculateQuestionTwo(View v) {

        //Checking if the button is checked or not
        questionTwoChecked = ((RadioButton) v).isChecked();

        // Checking which option was clicked and assigning the score accordingly
        switch (v.getId()) {
            case R.id.questionTwoRadioButtonOne:
                if (questionTwoChecked) {
                    gradeTwo = 15;
                    break;
                }
            case R.id.questionTwoRadioButtonTwo:
                if (questionTwoChecked) {
                    gradeTwo = 0;
                    break;
                }
            case R.id.questionTwoRadioButtonThree:
                if (questionTwoChecked) {
                    gradeTwo = 0;
                    break;
                }
            case R.id.questionTwoRadioButtonFour:
                if (questionTwoChecked) {
                    gradeTwo = 0;
                    break;
                }
        }

    }

    //Calculate gradeFive
    public void calculateQuestionFive(View v) {

        //Checking if the button is checked or not
        questionFiveChecked = ((RadioButton) v).isChecked();

        // Checking which option was clicked and assigning the score accordingly
        switch (v.getId()) {
            case R.id.questionFiveRadioButtonOne:
                if (questionFiveChecked) {
                    gradeFive = 0;
                    break;
                }
            case R.id.questionFiveRadioButtonTwo:
                if (questionFiveChecked) {
                    gradeFive = 15;
                    break;
                }
            case R.id.questionFiveRadioButtonThree:
                if (questionFiveChecked) {
                    gradeFive = 0;
                    break;
                }
            case R.id.questionFiveRadioButtonFour:
                if (questionFiveChecked) {
                    gradeFive = 0;
                    break;
                }
        }

    }

    //Checking the checkboxes are checked or not
    public void checkQuestionFourCheckBox(View v) {

        if (questionFourCheckedBoxOne.isChecked() || questionFourCheckedBoxTwo.isChecked() || questionFourCheckedBoxThree.isChecked() || questionFourCheckedBoxFour.isChecked()) {

            questionFourChecked = true;
        }

    }


    //Get total Score
    public void TotalScore(View v) {

        //First Calculating gradeThree and gradeFour

        String answerQuestionThree = questionThreeEditText.getText().toString().trim();

        if (answerQuestionThree.equals("ABC") && questionOneChecked && questionTwoChecked && questionFourChecked && questionFiveChecked) {
            gradeThree = 15;
        } else
            gradeThree = 0;

        gradeFour = 0;
        if (answerQuestionThree.equals("ABC") && questionOneChecked && questionTwoChecked && questionFourChecked && questionFiveChecked) {

            if (questionFourCheckedBoxOne.isChecked()) {
                gradeFour += 15;
            }
            if (questionFourCheckedBoxTwo.isChecked()) {
                gradeFour -= 15;
            }
            if (questionFourCheckedBoxThree.isChecked()) {
                gradeFour += 15;
            }
            if (questionFourCheckedBoxFour.isChecked()) {
                gradeFour += 15;
            }

            //making negative values 0
            if (gradeFour < 0)
                gradeFour = 0;

        }

        if (!answerQuestionThree.equals("") && questionOneChecked && questionTwoChecked && questionFourChecked && questionFiveChecked) {
            //Calculating and toasting the answer on Onclick of View Score Button
            TotalGradesObtained = gradeOne + gradeTwo + gradeThree + gradeFour + gradeFive;

            Toast.makeText(getApplicationContext(), "The total Score is :" + TotalGradesObtained, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please check if all Questions Are Answered!!", Toast.LENGTH_SHORT).show();

        }

    }

    //Resetting all the checked RadioGroups edit text checkboxes and boolean checked values
    public void ResetScore(View v) {
        questionOneRadioGroup.clearCheck();
        questionTwoRadioGroup.clearCheck();
        questionThreeEditText.setText("");
        questionFiveRadioGroup.clearCheck();
        questionFourCheckedBoxOne.setChecked(false);
        questionFourCheckedBoxTwo.setChecked(false);
        questionFourCheckedBoxThree.setChecked(false);
        questionFourCheckedBoxFour.setChecked(false);
        TotalGradesObtained = 0;
        questionFourChecked = false;

    }

}


