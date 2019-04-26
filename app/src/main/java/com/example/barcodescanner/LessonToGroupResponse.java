package com.example.barcodescanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonToGroupResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("groupResponse")
    @Expose
    private GroupResponse groupResponse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GroupResponse getGroupResponse() {
        return groupResponse;
    }

    public void setGroupResponse(GroupResponse groupResponse) {
        this.groupResponse = groupResponse;
    }

}
