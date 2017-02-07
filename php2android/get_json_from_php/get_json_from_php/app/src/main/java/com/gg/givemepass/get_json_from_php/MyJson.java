package com.gg.givemepass.get_json_from_php;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rick.wu on 2017/2/7.
 */

public class MyJson {
    @SerializedName("id")
    private String myId;
    @SerializedName("data")
    private String myName;

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
}
