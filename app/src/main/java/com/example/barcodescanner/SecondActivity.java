package com.example.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class SecondActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private BottomNavigationItemView scannerButton;
    private RadioGroup radioGroupDays;
    private RadioGroup radioGroupInfo;
    private Button buttonSearch;
    private BottomNavigationItemView homeButton;
    private Intent intent;
    HttpClient httpClient;
    HttpResponse httpResponse;

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.scan:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.about:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        Button button = (Button) findViewById(R.id.search);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HttpParams httpParams = new BasicHttpParams() ;
//                int timeoutSocket = 20000;
//                int timeoutConnection = 20000;
//
//                HttpConnectionParams.setConnectionTimeout(httpParams,timeoutSocket);
//                HttpConnectionParams.setSoTimeout(httpParams,timeoutConnection);
//
//                httpClient = new DefaultHttpClient(httpParams);
//
//                try{
//                    new PostJson().execute();
//
//                }catch (Exception e){
//                    Log.i("myAppTag", "Error in on create ... "+ e.toString());
//                }
//
//            }
//        });
//        scannerButton = (BottomNavigationItemView) findViewById(R.id.scan);
//        homeButton = (BottomNavigationItemView) findViewById(R.id.navigation_home);
//        scannerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                scannerButton.setDrawingCacheBackgroundColor(0xFF008577);
//                intent = new Intent(SecondActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

//    public void addListenerOnButton() {
//        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupDays);
//        Button searchButton = (Button) findViewById(R.id.search);
//
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
//                RadioButton radioButton = (RadioButton) findViewById(checkedRadioButtonId);
//                intent.putExtra("days", radioButton.getText());
//            }
//        });
//
//    }


    }
}
