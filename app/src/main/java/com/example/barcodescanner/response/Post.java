package com.example.barcodescanner.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timeResponse")
    @Expose
    private TimeResponse timeResponse;
    @SerializedName("teacherResponse")
    @Expose
    private TeacherResponse teacherResponse;
    @SerializedName("audienceResponse")
    @Expose
    private AudienceResponse audienceResponse;
    @SerializedName("lessonToGroupResponses")
    @Expose
    private List<LessonToGroupResponse> lessonToGroupResponses = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeResponse getTimeResponse() {
        return timeResponse;
    }

    public void setTimeResponse(TimeResponse timeResponse) {
        this.timeResponse = timeResponse;
    }

    public TeacherResponse getTeacherResponse() {
        return teacherResponse;
    }

    public void setTeacherResponse(TeacherResponse teacherResponse) {
        this.teacherResponse = teacherResponse;
    }

    public AudienceResponse getAudienceResponse() {
        return audienceResponse;
    }

    public void setAudienceResponse(AudienceResponse audienceResponse) {
        this.audienceResponse = audienceResponse;
    }

    public List<LessonToGroupResponse> getLessonToGroupResponses() {
        return lessonToGroupResponses;
    }

    public void setLessonToGroupResponses(List<LessonToGroupResponse> lessonToGroupResponses) {
        this.lessonToGroupResponses = lessonToGroupResponses;
    }
}
