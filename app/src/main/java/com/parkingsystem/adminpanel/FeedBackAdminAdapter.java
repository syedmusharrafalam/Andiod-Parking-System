package com.parkingsystem.adminpanel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parkingsystem.R;
import com.parkingsystem.feedbackUser.FeedBackAdapter;
import com.parkingsystem.feedbackUser.FeedBackUid;

import java.util.ArrayList;

/**
 * Created by User on 4/24/2018.
 */

public class FeedBackAdminAdapter extends RecyclerView.Adapter<FeedBackAdminAdapter.MyViewHolder> {
 Context context;
    ArrayList<FeedBackUid>mFeedUid;

    public FeedBackAdminAdapter(Context context, ArrayList<FeedBackUid> mFeedUid) {
        this.context = context;
        this.mFeedUid = mFeedUid;
    }

    @Override
    public FeedBackAdminAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_feedback_list_admin,parent,false);
        FeedBackAdminAdapter.MyViewHolder viewHolders=new FeedBackAdminAdapter.MyViewHolder(v);
        return viewHolders;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.labelUser.setText("User ");
        holder.message.setText(mFeedUid.get(position).getName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=mFeedUid.get(position).getUid();
                String fid=mFeedUid.get(position).getFid();
                Intent intent=new Intent(context,FeedBackReply.class);
                intent.putExtra("uid",uid);
                intent.putExtra("fid",fid);
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mFeedUid.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView labelUser,message;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            labelUser=(TextView)itemView.findViewById(R.id.feedBackLabelNameAd);
            message=(TextView)itemView.findViewById(R.id.feedBackMessagesAd);
            btn=(Button) itemView.findViewById(R.id.viewFeedBackAd);
        }
    }
}
