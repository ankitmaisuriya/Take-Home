package com.ankitmaisuriya.me.presenter;

import com.ankitmaisuriya.me.model.RepoInfo;
import com.ankitmaisuriya.me.model.UserInfo;
import com.ankitmaisuriya.me.rest.APIClient;
import com.ankitmaisuriya.me.rest.APIInterface;
import com.ankitmaisuriya.me.view.UserInfoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoPresenter {

    private APIInterface apiInterface;
    private UserInfoView userInfoView;
    private APIClient client;
   

    public UserInfoPresenter(UserInfoView view) {
        this.userInfoView = view;
        if (this.client == null) {
            this.client = new APIClient();
        }
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void getUserId(String userId) {
        apiInterface.doGetUserInfo(userId).enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        UserInfo data = response.body();

                        if (String.valueOf(response.isSuccessful()).equals("true")) {
                            userInfoView.userInfoReady(data);
                        } else {
                            userInfoView.userInfoReady(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public void getUserRepo(String userId) {
        apiInterface.doGetUserRepo(userId).enqueue(new Callback<List<RepoInfo>>() {
                    @Override
                    public void onResponse(Call<List<RepoInfo>> call, Response<List<RepoInfo>> response) {
                        List<RepoInfo> data = response.body();
                        userInfoView.userRepoReady(data);
                    }

                    @Override
                    public void onFailure(Call<List<RepoInfo>> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
