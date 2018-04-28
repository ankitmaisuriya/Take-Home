package com.ankitmaisuriya.me.adapter;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankitmaisuriya.me.R;
import com.ankitmaisuriya.me.model.RepoInfo;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.MyViewHolder> {

private List<RepoInfo> repoList;
private Context mContext;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title, desc, update, star, fork;

    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.textView1);
        desc = (TextView) view.findViewById(R.id.textView2);
        update = (TextView) view.findViewById(R.id.textView3);
        star = (TextView) view.findViewById(R.id.textView4);
        fork = (TextView) view.findViewById(R.id.textView5);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.custom_toast, (ViewGroup) v.findViewById(R.id.custom_toast_layout));
                BottomSheetDialog dialog = new BottomSheetDialog(mContext);

                TextView update_time = (TextView) view.findViewById(R.id.update);
                TextView stars = (TextView) view.findViewById(R.id.star);
                TextView forks = (TextView) view.findViewById(R.id.fork);

                update_time.setText(update.getText().toString().trim());
                stars.setText(star.getText().toString().trim());
                forks.setText(fork.getText().toString().trim());
                dialog.setContentView(view);

                dialog.show();

            }
        });

    }

}
    public RepoListAdapter(Context mContext, List<RepoInfo> moviesList) {
        this.repoList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RepoInfo object = repoList.get(position);
        holder.title.setText(object.name);
        holder.desc.setText(object.description);
        holder.update.setText(object.updated_at);
        holder.star.setText(String.valueOf(object.stargazers_count));
        holder.fork.setText(String.valueOf(object.forks));

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}