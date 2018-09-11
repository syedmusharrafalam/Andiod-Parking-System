package com.parkingsystem.adminpanel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parkingsystem.R;
import com.parkingsystem.bookinghistorypanel.BookingAdapter;
import com.parkingsystem.userpanel.activities.UserInformation;

import java.util.ArrayList;

/**
 * Created by User on 4/23/2018.
 */

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserInformation> mUsers;

    public UserAdapter(Context context, ArrayList<UserInformation> mUsers) {
        this.context = context;
        this.mUsers = mUsers;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_user_info,parent,false);
        UserAdapter.MyViewHolder viewHolders=new UserAdapter.MyViewHolder(v);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.usernameInf.setText(mUsers.get(position).getName());
        holder.emailInf.setText(mUsers.get(position).getEmail());
        holder.contactInf.setText(mUsers.get(position).getContact());
        Toast.makeText(context,"asdsa"+mUsers.get(0).getName(),Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView usernameInf,contactInf,emailInf;



        public MyViewHolder(View itemView) {
            super(itemView);
            usernameInf=(TextView)itemView.findViewById(R.id.valueProfileUserNameInfs);
            contactInf=(TextView)itemView.findViewById(R.id.valueProfileUserContactInfs);
            emailInf=(TextView)itemView.findViewById(R.id.valueProfileUserEmailInfs);



        }
    }
}
