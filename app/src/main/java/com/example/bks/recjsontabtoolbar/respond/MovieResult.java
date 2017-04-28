package com.example.bks.recjsontabtoolbar.respond;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bks on 4/24/2017.
 */

public class MovieResult {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
