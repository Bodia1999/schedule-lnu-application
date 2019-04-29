package com.example.barcodescanner.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodescanner.response.AudienceResponse;
import com.example.barcodescanner.response.LessonToGroupResponse;
import com.example.barcodescanner.http.connector.NetworkService;
import com.example.barcodescanner.response.Post;
import com.example.barcodescanner.R;
import com.example.barcodescanner.request.RequestBody;
import com.example.barcodescanner.response.TeacherResponse;
import com.example.barcodescanner.response.TimeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.barcodescanner.activity.SecondActivity.AUDIENCE_NUMBER;
import static com.example.barcodescanner.activity.SecondActivity.LESSON_NAME;
import static com.example.barcodescanner.activity.SecondActivity.TEACHER_SURNAME;

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
                if (body.size() == 0) {
                    Toast.makeText(getApplicationContext(), "There are no answers for your parameters", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ScrollingActivity.this, SecondActivity.class);
                    startActivity(intent);
                }

                for (Post post : body) {
                    TimeResponse timeResponse = post.getTimeResponse();
                    TeacherResponse teacherResponse = post.getTeacherResponse();
                    AudienceResponse audienceResponse = post.getAudienceResponse();
                    List<LessonToGroupResponse> lessonToGroupResponses = post.getLessonToGroupResponses();
                    String name = post.getName();
//                    textView.append("\n\n");
                    SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder("\nLesson:  " + name);
                    spannableStringBuilder2.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.append(spannableStringBuilder2);

                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("\nAudience:  " + audienceResponse.getAudienceNumber() + " " + audienceResponse.getAudienceAddress());
                    spannableStringBuilder.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.append(spannableStringBuilder);
//                    textView.append("\nAudience:  " + audienceResponse.getAudienceNumber() + " " + audienceResponse.getAudienceAddress());
                    SpannableStringBuilder spannableStringBuilder1 = new SpannableStringBuilder("\nTime:  " + timeResponse.getDayOfWeek() + " " + timeResponse.getStartTime() + "-" + timeResponse.getEndTime());
                    spannableStringBuilder1.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.append(spannableStringBuilder1);
//                    textView.append("\nTime:  "+timeResponse.getDayOfWeek()+" "+timeResponse.getStartTime()+"-"+timeResponse.getEndTime());
//                    textView.append("\nLesson:  "+name);


                    SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder("\nTeacher:  " + teacherResponse.getSurname() + " " + teacherResponse.getName() + " " + teacherResponse.getPatronymic());
                    spannableStringBuilder3.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.append(spannableStringBuilder3);

//                    textView.append("\nTeacher:  "+teacherResponse.getSurname()+" "+teacherResponse.getName()+" "+teacherResponse.getPatronymic());

//                    textView.append("\n" + timeResponse.getDayOfWeek() + "\t\t\t\t\t");
//                    textView.append(name);
//                    textView.append("\n" + timeResponse.getStartTime() + "\t\t\t\t\t");
//                    textView.append(teacherResponse.getSurname() + " " + teacherResponse.getName() + " " + teacherResponse.getPatronymic());
//                    textView.append("\n" + timeResponse.getEndTime() + "\t\t\t\t\t");
                    SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder("\nGroups:  ");
                    spannableStringBuilder4.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.append(spannableStringBuilder4);
//                    textView.append("\nGroups:  ");
                    int counter = 0;
                    for (LessonToGroupResponse lessonToGroupRespons : lessonToGroupResponses) {
                        textView.append(lessonToGroupRespons.getGroupResponse().getName() + " ");
                        if (counter+1 < lessonToGroupResponses.size()) {
                            textView.append(", ");
                            System.out.println(counter);
                            counter++;
                        }

                    }
                    textView.append("\n");
//                    textView.append(post.getName());
//                    System.out.println(post.getTimeResponse().getDayOfWeek());
//                    System.out.println(post.getTeacherResponse().getSurname());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something goes wrong", Toast.LENGTH_LONG).show();
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
