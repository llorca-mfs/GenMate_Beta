package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mobdeve.s17.llorca.madrid.genmate_beta.R;
import com.mobdeve.s17.llorca.madrid.genmate_beta.ReminderBroadcast;

import java.util.ArrayList;

public class ResinTimeAdapter extends RecyclerView.Adapter<ResinTimeAdapter.ResinTimeViewHolder> {

    private ArrayList<ResinTime> resinTimesArrayList;
    private Context context;

    public ResinTimeAdapter(Context context, ArrayList<ResinTime> resinTimesArrayList){
        this.resinTimesArrayList = resinTimesArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return resinTimesArrayList.size();
    }

    @Override
    public ResinTimeAdapter.ResinTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resin_entry, parent, false);

        ResinTimeViewHolder resinTimeViewHolder = new ResinTimeViewHolder(view);

        return resinTimeViewHolder;
    }

    @Override
    public void onBindViewHolder(ResinTimeViewHolder holder, int position) {
        holder.tvTimeToGetResinAmount.setText(resinTimesArrayList.get(position).getTimeToGetResinAmount());
        holder.tvResinAmount.setText(Integer.toString(resinTimesArrayList.get(position).getFutureResinAmount()));
        holder.acbResinReminder.setOnClickListener( v -> {
                    Snackbar mySnackbar = Snackbar.make(v, "Reminder Set,  we will remind you when you reach " + Integer.toString(resinTimesArrayList.get(position).getFutureResinAmount()) + " resin", BaseTransientBottomBar.LENGTH_LONG);
                    mySnackbar.show();

                    holder.acbResinReminder.setEnabled(false);

                    Intent intent = new Intent(context, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();

//                    long durationOfGoalResinInMillis = 1000;
                    long durationOfGoalResinInMillis = 1000 * 60 * resinTimesArrayList.get(position).getMinsTilResinAmount();

                    alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + durationOfGoalResinInMillis, pendingIntent);
                }
        );
    }

    protected class ResinTimeViewHolder extends RecyclerView.ViewHolder{
        TextView tvTimeToGetResinAmount;
        TextView tvResinAmount;

        AppCompatButton acbResinReminder;

        public ResinTimeViewHolder(View view){
            super(view);
            tvTimeToGetResinAmount = view.findViewById(R.id.tvTimeToGetResinAmount);
            tvResinAmount = view.findViewById(R.id.tvResinAmount);
            acbResinReminder = view.findViewById(R.id.acbResinReminder);
        }
    }

}
