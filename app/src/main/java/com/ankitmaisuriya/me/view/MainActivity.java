package com.ankitmaisuriya.me.view;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ankitmaisuriya.me.R;
import com.ankitmaisuriya.me.adapter.RepoListAdapter;
import com.ankitmaisuriya.me.model.RepoInfo;
import com.ankitmaisuriya.me.model.UserInfo;
import com.ankitmaisuriya.me.presenter.UserInfoPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements UserInfoView {

    @BindView(R.id.btn_search) Button Search;
    @BindView(R.id.input_layout_name) TextInputLayout input_layout_name;
    @BindView(R.id.input_name) EditText input_name;
    @BindView(R.id.imageView) ImageView avatar_image;
    @BindView(R.id.textView) TextView login_name;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.progressbar) ProgressBar progressBar;


    private UserInfoPresenter userPresenter;
    private RepoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userPresenter = new UserInfoPresenter(MainActivity.this);
    }

    @OnClick(R.id.btn_search)
    public void search() {
        progressBar.setVisibility(View.VISIBLE);

        if (input_name.getText().toString().trim().length() > 0) {
            userPresenter.getUserId(input_name.getText().toString().trim());
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            login_name.setVisibility(View.INVISIBLE);
            avatar_image.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.no_data, null);
            BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
            TextView msg = (TextView) view.findViewById(R.id.message);
            msg.setText(R.string.please_enter_userid);
            dialog.setContentView(view);
            dialog.show();
        }
    }

    @Override
    public void userInfoReady(UserInfo userInfo) {

        if(userInfo != null) {
            login_name.setVisibility(View.VISIBLE);
            avatar_image.setVisibility(View.VISIBLE);

            Picasso.with(MainActivity.this)
                    .load(userInfo.avatar_url)
                    .into(avatar_image);
            login_name.setText(userInfo.name);
            userPresenter.getUserRepo(input_name.getText().toString().trim());
        }else{
            progressBar.setVisibility(View.INVISIBLE);
            login_name.setVisibility(View.INVISIBLE);
            avatar_image.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);

            LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.no_data, null);
            BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
            dialog.setContentView(view);
            dialog.show();
        }

    }

    @Override
    public void userRepoReady(List<RepoInfo> repoInfo) {
        recyclerView.setVisibility(View.VISIBLE);
        mAdapter = new RepoListAdapter(MainActivity.this, repoInfo);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
