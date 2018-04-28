package com.ankitmaisuriya.me.view;

import com.ankitmaisuriya.me.model.RepoInfo;
import com.ankitmaisuriya.me.model.UserInfo;
import java.util.List;

public interface UserInfoView {

    void userInfoReady(UserInfo userInfo);

    void userRepoReady(List<RepoInfo> repoInfo);
}
