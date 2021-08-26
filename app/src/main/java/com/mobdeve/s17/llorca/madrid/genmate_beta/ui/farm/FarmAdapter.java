package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.farm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s17.llorca.madrid.genmate_beta.R;

import java.util.ArrayList;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.FarmViewHolder> {

    private ArrayList<Farmable> farmableArrayList;
    private Context context;

    public FarmAdapter(Context context, ArrayList<Farmable> farmableArrayList){
        this.farmableArrayList = farmableArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return farmableArrayList.size();
    }

    @Override
    public FarmAdapter.FarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_farmable, parent, false);

        FarmViewHolder farmViewHolder = new FarmViewHolder(view);

        return farmViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmViewHolder holder, int position) {
        holder.tvFarmableItemName.setText(farmableArrayList.get(position).getItemname());
        holder.ivFarmableImage.setImageResource(farmableArrayList.get(position).getImageDrawableId());
    }

    protected class FarmViewHolder extends RecyclerView.ViewHolder{
        ImageView ivFarmableImage;
        TextView tvFarmableItemName;

        public FarmViewHolder(View view){
            super(view);
            ivFarmableImage = view.findViewById(R.id.ivFarmableImage);
            tvFarmableItemName = view.findViewById(R.id.tvFarmableItemName);
        }
    }

}
