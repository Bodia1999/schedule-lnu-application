package com.example.barcodescanner;

public class RequestBody {
    private String audienceNumber;
    private String teacherSurname;

    public RequestBody(String audienceNumber, String teacherSurname) {
        this.audienceNumber = audienceNumber;
        this.teacherSurname = teacherSurname;
    }
}
