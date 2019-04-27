package com.example.barcodescanner;

public class RequestBody {
    private String audienceNumber;
    private String teacherSurname;
    private String dayOfTheWeek;
    private String lessonName;


    public RequestBody(String audienceNumber, String teacherSurname, String dayOfTheWeek, String lessonName) {
        this.audienceNumber = audienceNumber;
        this.teacherSurname = teacherSurname;
        this.dayOfTheWeek = dayOfTheWeek;
        this.lessonName = lessonName;
    }
}
