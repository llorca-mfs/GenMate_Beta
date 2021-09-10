package com.mobdeve.s17.llorca.madrid.genmate_beta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s17.llorca.madrid.genmate_beta.model.QrFriend;

import java.util.ArrayList;

public class QrListAdapter extends RecyclerView.Adapter<QrListAdapter.QrListViewHolder>{

    private ArrayList<QrFriend> qrFriendArrayList;
    private Context context;

    public QrListAdapter(Context context, ArrayList<QrFriend> qrFriendArrayList){
        this.qrFriendArrayList = qrFriendArrayList;
        this.context = context;
    }

    @Override
    public QrListAdapter.QrListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qr_list, parent, false);
        QrListViewHolder qrListViewHolder = new QrListViewHolder(view);

        return qrListViewHolder;
    }

    @Override
    public void onBindViewHolder(QrListAdapter.QrListViewHolder holder, int position) {
        holder.tv_ign_list.setText(qrFriendArrayList.get(position).getIgn());
        holder.tv_uid_list.setText(qrFriendArrayList.get(position).getUid());
    }

    @Override
    public int getItemCount() {
        return qrFriendArrayList.size();
    }

    public void addQrFriends(ArrayList<QrFriend> qrFriendArrayList){
        this.qrFriendArrayList.clear();
        this.qrFriendArrayList.addAll(qrFriendArrayList);
        notifyDataSetChanged();
    }

    public void addQrFriend(QrFriend qrFriend){
        qrFriendArrayList.add(0,qrFriend);
        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void removeQrFriend(int position){
        qrFriendArrayList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    protected class QrListViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ign_list;
        TextView tv_uid_list;
        public QrListViewHolder(View view){
            super(view);
            tv_ign_list = view.findViewById(R.id.tvIgnList);
            tv_uid_list = view.findViewById(R.id.tvUidList);
        }
    }
}
