package com.example.barcodescanner.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AudienceResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("audienceNumber")
    @Expose
    private String audienceNumber;
    @SerializedName("audienceAddress")
    @Expose
    private String audienceAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAudienceNumber() {
        return audienceNumber;
    }

    public void setAudienceNumber(String audienceNumber) {
        this.audienceNumber = audienceNumber;
    }

    public String getAudienceAddress() {
        return audienceAddress;
    }

    public void setAudienceAddress(String audienceAddress) {
        this.audienceAddress = audienceAddress;
    }
}
