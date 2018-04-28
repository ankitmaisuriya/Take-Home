package com.ankitmaisuriya.me.rest;

import com.ankitmaisuriya.me.model.RepoInfo;
import com.ankitmaisuriya.me.model.UserInfo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("{userId}")
    Call<UserInfo> doGetUserInfo(@Path("userId") String userId);

    @GET("{userId}/repos")
    Call<List<RepoInfo>> doGetUserRepo(@Path("userId") String userId);

}
