package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mobdeve.s17.llorca.madrid.genmate_beta.R;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentFarmBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentResinBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.ui.farm.FarmAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class ResinFragment extends Fragment implements View.OnClickListener{

    private FragmentResinBinding binding;
    private ResinTimeAdapter resinTimeAdapter;
    private ArrayList<ResinTime> resinTimesList;

    private int currentResin;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createNotificationChannel();
        binding = FragmentResinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        changeRecView();

        binding.enbResinAmt.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            changeRecView();
        });
        /*
        this.currentResin = Integer.parseInt(binding.enbResinAmt.getNumber());

        calculateResinTimes();*/

        //binding.acbCalculateResinTimes.setOnClickListener(this);

        binding.rvForResinTimes.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        binding.rvForResinTimes.setAdapter(resinTimeAdapter);

        return root;
    }


    private void changeRecView(){
        this.currentResin = Integer.parseInt(binding.enbResinAmt.getNumber());
        Log.d("TAG","currentResin is "+currentResin);
        calculateResinTimes(this.currentResin);
    }

    private void calculateResinTimes(int resinParam){
        resinTimesList = new ArrayList<>();

        int minuteCounter = 0;

        Calendar currentTimeNow = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, hh:mm a", Locale.getDefault());

        for (int i = resinParam; i <=160; i++){
            if (i % 20 == 0 && i != 0 && i != resinParam){
                resinTimesList.add(new ResinTime(sdf.format(currentTimeNow.getTime()), i, minuteCounter));
            }
            currentTimeNow.add(Calendar.MINUTE, 8);
            minuteCounter += 8;
        }

        resinTimeAdapter = new ResinTimeAdapter(getActivity().getApplicationContext(), resinTimesList);
        binding.rvForResinTimes.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        binding.rvForResinTimes.setAdapter(resinTimeAdapter);

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "GenMateReminderChannel";
            String description = "Channel for GenMate Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel ("notifyGenMateUserResin", name, importance);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
