package com.mobdeve.s17.llorca.madrid.genmate_beta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QrListAdapter extends RecyclerView.Adapter<QrListAdapter.QrListViewHolder>{

    private ArrayList<QrListItem> qrListItemArrayList;
    private Context context;

    public QrListAdapter(Context context, ArrayList<QrListItem> qrListItemArrayList){
        this.qrListItemArrayList = qrListItemArrayList;
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
        holder.tv_ign_list.setText(qrListItemArrayList.get(position).getIgn());
        holder.tv_uid_list.setText(qrListItemArrayList.get(position).getUid());
    }

    @Override
    public int getItemCount() {
        return qrListItemArrayList.size();
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
