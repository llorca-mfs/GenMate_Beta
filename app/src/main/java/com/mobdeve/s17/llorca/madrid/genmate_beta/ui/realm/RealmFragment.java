package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.realm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.fragment.app.Fragment;

import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentRealmBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentResinBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin.ResinTime;
import com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin.ResinTimeAdapter;

import java.util.ArrayList;

public class RealmFragment extends Fragment implements View.OnClickListener{

    private FragmentRealmBinding binding;
//    private ResinTimeAdapter resinTimeAdapter;
//    private ArrayList<ResinTime> resinTimesList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createNotificationChannel();
        binding = FragmentRealmBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        binding.acbCalculateResinTimes.setOnClickListener(this::onClick);
//
//        binding.rvForResinTimes.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//        binding.rvForResinTimes.setAdapter(resinTimeAdapter);

        return root;
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "GenMateReminderChannel";
            String description = "Channel for GenMate Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel ("notifyGenMateUserRealmCoin", name, importance);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
