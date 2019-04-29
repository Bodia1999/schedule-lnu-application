package com.example.barcodescanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodescanner.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

public class SecondActivity extends AppCompatActivity {
    public static final String TEACHER_SURNAME = "teacherSurname";
    public static final String AUDIENCE_NUMBER = "audienceNumber";
    public static final String LESSON_NAME = "lessonName";
    private TextView mTextMessage;
    private EditText enterData;
    private BottomNavigationItemView scannerButton;
    private RadioGroup radioGroupDays;
    private RadioGroup radioGroupInfo;
    private RadioButton radioButtonForDays;
    private RadioButton radioButtonForInfo;
    private Button buttonSearch;
    private BottomNavigationItemView homeButton;
    private Intent intent;
    HttpClient httpClient;
    HttpResponse httpResponse;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.scan:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.about:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intent = new Intent(SecondActivity.this, ScrollingActivity.class);

        buttonSearch = (Button) findViewById(R.id.search);
        enterData = (EditText) findViewById(R.id.enterData);
        radioGroupDays = (RadioGroup) findViewById(R.id.radioGroupDays);
        radioGroupInfo = (RadioGroup) findViewById(R.id.radioGroupInfo);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputData = enterData.getText().toString();
//                System.out.println(inputData);
                int checkedRadioButtonId = radioGroupDays.getCheckedRadioButtonId();
                radioButtonForDays = (RadioButton) findViewById(checkedRadioButtonId);
                String daysFromRadioButton = radioButtonForDays.getText().toString();
                System.out.println("Day second " + daysFromRadioButton);

                int checkedRadioButtonId1 = radioGroupInfo.getCheckedRadioButtonId();
                radioButtonForInfo = (RadioButton) findViewById(checkedRadioButtonId1);
                String infoFromRadioButton = radioButtonForInfo.getText().toString();
//                System.out.println(infoFromRadioButton);
                intent.putExtra(TEACHER_SURNAME, "");
                intent.putExtra(LESSON_NAME, "");
                intent.putExtra(AUDIENCE_NUMBER, "");
                intent.putExtra("dayOfTheWeek", "");
                settingDataToNewIntent(inputData, daysFromRadioButton, infoFromRadioButton);
//                intent = new Intent(SecondActivity.this,Response.class);
//                startActivity(intent);

//savedInstanceState.putSerializable();
            }
        });

        scannerButton = (BottomNavigationItemView) findViewById(R.id.scan);
        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


    }

    private void settingDataToNewIntent(String inputData, String daysFromRadioButton, String infoFromRadioButton) {
//        Intent intent1 = new Intent();


        System.out.println("Input data " + inputData);
        if (!infoFromRadioButton.equals("None") && inputData.trim().length()==0) {
            Toast.makeText(getApplicationContext(), "Enter data in field", Toast.LENGTH_LONG).show();
            return;
        }
        if (inputData.trim().length() > 0 && infoFromRadioButton.equals("None")){
            Toast.makeText(getApplicationContext(), "Choose by what parameter you want to seek", Toast.LENGTH_LONG).show();
            return;
        }
            if (!daysFromRadioButton.equals("None")) {
                intent.putExtra("dayOfTheWeek", daysFromRadioButton);
            } else {
                intent.putExtra("daysOfTheWeek", " ");
            }

        switch (infoFromRadioButton) {
            case "Teacher surname":
                intent.putExtra(TEACHER_SURNAME, inputData);
                break;
            case "Number of audience":
                intent.putExtra(AUDIENCE_NUMBER, inputData);
                break;
            case "Name of lesson":
                intent.putExtra(LESSON_NAME, inputData);
                break;

        }
        startActivity(intent);
    }
}
