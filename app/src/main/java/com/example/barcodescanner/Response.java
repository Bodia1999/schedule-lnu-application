package com.example.barcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Response extends AppCompatActivity {
    final static String URL = "https://yourschedulelnu.herokuapp.com/lesson/findByFilter";
    HttpClient httpClient;
    TextView textView;
    HttpResponse httpResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        RequestBody requestBody = new RequestBody("1","kr");
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

    }

//

//    public class postJson extends AsyncTask<String, Integer, String>{
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try{
//                HttpPost httpPost = new HttpPost(URL);
//                List<NameValuePair> nameValuePairs = new ArrayList<>();
//                nameValuePairs.add(new BasicNameValuePair("audienceNumber", "88"));
//                httpPost.setHeader("Accept","application/json");
//                httpPost.setHeader("Content-type","application/json; charset-UTF-8");
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//
//            return null;
//        }
//    }
}
