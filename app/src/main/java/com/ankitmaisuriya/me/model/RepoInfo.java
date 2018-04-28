package com.ankitmaisuriya.me.model;

import com.google.gson.annotations.SerializedName;

public class RepoInfo {

    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("stargazers_count")
    public int stargazers_count;
    @SerializedName("forks")
    public int forks;

}
