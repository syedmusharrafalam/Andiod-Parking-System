package com.parkingsystem.feedbackUser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parkingsystem.R;
import com.parkingsystem.bookinghistorypanel.BookingAdapter;
import com.parkingsystem.userpanel.activities.UserInformation;

import java.util.ArrayList;

/**
 * Created by User on 4/24/2018.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.MyViewHolder> {
Context context;
    ArrayList<FeedBackInf>mFeedBack;

    public FeedBackAdapter(Context context, ArrayList<FeedBackInf> mFeedBack) {
        this.context = context;
        this.mFeedBack = mFeedBack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_feedback_list,parent,false);
        FeedBackAdapter.MyViewHolder viewHolders=new FeedBackAdapter.MyViewHolder(v);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.labelUser.setText(mFeedBack.get(position).getName()+"Says:- ");
        holder.message.setText(mFeedBack.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return mFeedBack.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
      TextView labelUser,message;


        public MyViewHolder(View itemView) {
            super(itemView);
            labelUser=(TextView)itemView.findViewById(R.id.feedBackLabelName);
            message=(TextView)itemView.findViewById(R.id.feedBackMessages);


        }
    }
}
