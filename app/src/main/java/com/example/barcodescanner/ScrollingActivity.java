package com.example.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.barcodescanner.SecondActivity.AUDIENCE_NUMBER;
import static com.example.barcodescanner.SecondActivity.LESSON_NAME;
import static com.example.barcodescanner.SecondActivity.TEACHER_SURNAME;

public class ScrollingActivity extends AppCompatActivity {
    BottomNavigationItemView bottomNavigationItemView;
    FloatingActionButton floatingActionButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.responseScrolling);

        String teacherSurname = getIntent().getStringExtra(TEACHER_SURNAME);
        System.out.println("Teacher " + teacherSurname);
        String lessonName = getIntent().getStringExtra(LESSON_NAME);
        System.out.println("Lesson " + lessonName);
        String audienceNumber = getIntent().getStringExtra(AUDIENCE_NUMBER);
        System.out.println("Audience " + audienceNumber);
        String dayOfTheWeek = getIntent().getStringExtra("dayOfTheWeek");
        System.out.println("Day " + dayOfTheWeek);
        RequestBody requestBody = new RequestBody(audienceNumber, teacherSurname, dayOfTheWeek, lessonName);
        NetworkService.getInstance().
                getJSONApi()
                .getPostWithValues(requestBody).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                List<Post> body = response.body();
                for (Post post : body) {
                    TimeResponse timeResponse = post.getTimeResponse();
                    TeacherResponse teacherResponse = post.getTeacherResponse();
                    AudienceResponse audienceResponse = post.getAudienceResponse();
                    List<LessonToGroupResponse> lessonToGroupResponses = post.getLessonToGroupResponses();
                    String name = post.getName();
                    textView.append("\n\n");
                    textView.append("\nAudience: "+audienceResponse.getAudienceNumber()+" "+audienceResponse.getAudienceAddress() );
                    textView.append("\n" + timeResponse.getDayOfWeek()+"\t\t\t\t\t");
                    textView.append(name);
                    textView.append("\n"+timeResponse.getStartTime()+"\t\t\t\t\t");
                    textView.append(teacherResponse.getSurname()+" "+teacherResponse.getName()+" "+teacherResponse.getPatronymic());
                    textView.append("\n"+timeResponse.getEndTime()+"\t\t\t\t\t");
                    textView.append("Groups: ");

                    for (LessonToGroupResponse lessonToGroupRespons : lessonToGroupResponses) {
                        textView.append(lessonToGroupRespons.getGroupResponse().getName()+" ");

                    }
                    textView.append("\n\n");
//                    textView.append(post.getName());
//                    System.out.println(post.getTimeResponse().getDayOfWeek());
//                    System.out.println(post.getTeacherResponse().getSurname());
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
