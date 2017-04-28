package com.example.bks.recjsontabtoolbar.respond;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bks on 4/24/2017.
 */

public class Generics {
    @SerializedName("name")
    private String genericsname;

    @SerializedName("id")
    private Integer genericsid;

    public String getGenericsname() {
        return genericsname;
    }

    public void setGenericsname(String genericsname) {
        this.genericsname = genericsname;
    }

    public Integer getGenericsid() {
        return genericsid;
    }

    public void setGenericsid(Integer genericsid) {
        this.genericsid = genericsid;
    }
}
