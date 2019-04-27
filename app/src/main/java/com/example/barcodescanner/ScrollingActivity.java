package com.example.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.barcodescanner.SecondActivity.AUDIENCE_NUMBER;
import static com.example.barcodescanner.SecondActivity.LESSON_NAME;
import static com.example.barcodescanner.SecondActivity.TEACHER_SURNAME;

public class ScrollingActivity extends AppCompatActivity {
    BottomNavigationItemView bottomNavigationItemView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String teacherSurname = getIntent().getStringExtra(TEACHER_SURNAME);
        System.out.println("Teacher "+teacherSurname);
        String lessonName = getIntent().getStringExtra(LESSON_NAME);
        System.out.println("Lesson "+lessonName);
        String audienceNumber = getIntent().getStringExtra(AUDIENCE_NUMBER);
        System.out.println("Audience "+audienceNumber);
        String dayOfTheWeek = getIntent().getStringExtra("dayOfTheWeek");
        System.out.println("Day "+dayOfTheWeek);
        RequestBody requestBody = new RequestBody(audienceNumber,teacherSurname,dayOfTheWeek,lessonName);
        NetworkService.getInstance().
                getJSONApi()
                .getPostWithValues(requestBody).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                List<Post> body = response.body();
                for (Post post : body) {
//                    textView = (TextView) findViewById(R.id.name);
//                    TextView teacherName = (TextView) findViewById(R.id.teacherName);
//                    TextView startTime = (TextView) findViewById(R.id.startTime);
//                    TextView endTime = (TextView) findViewById(R.id.endTime);

//                    textView.setText(post.getName());
//                    teacherName.setText(post.getTeacherResponse().getSurname() + " " + post.getTeacherResponse().getName() + " " + post.getTeacherResponse().getPatronymic());
//                    startTime.setText(post.getTimeResponse().getStartTime());
//                    endTime.setText(post.getTimeResponse().getEndTime());
                    System.out.println(post.getName());
                    System.out.println(post.getTimeResponse().getDayOfWeek());
                    System.out.println(post.getTeacherResponse().getSurname());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonInResponseScrolling);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollingActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
