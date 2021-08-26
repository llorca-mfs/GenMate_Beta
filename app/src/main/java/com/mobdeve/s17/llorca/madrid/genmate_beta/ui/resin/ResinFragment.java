package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mobdeve.s17.llorca.madrid.genmate_beta.R;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentFarmBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentResinBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.ui.farm.FarmAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ResinFragment extends Fragment implements View.OnClickListener{

    private FragmentResinBinding binding;
    private ResinTimeAdapter resinTimeAdapter;
    private ArrayList<ResinTime> resinTimesList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createNotificationChannel();

        binding = FragmentResinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.rvForResinTimes;

        binding.acbCalculateResinTimes.setOnClickListener(this);

        resinTimeAdapter = new ResinTimeAdapter(getActivity().getApplicationContext(), resinTimesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(resinTimeAdapter);

        return root;

    }

    /*
    * @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_resin);
        createNotificationChannel();
        binding = FragmentResinBinding.inflate(getLayoutInflater());
        View mainView = binding.getRoot();
        setContentView(mainView);

        binding.acbCalculateResinTimes.setOnClickListener(this);
        resinTimeAdapter = new ResinTimeAdapter(getApplicationContext(), resinTimesList);
        binding.rvForResinTimes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvForResinTimes.setAdapter(resinTimeAdapter);
    }
    * */


    private void calculateResinTimes(){
        resinTimesList = new ArrayList<>();
        String curAmountResin = binding.etCurResinInput.getText().toString();

        if (curAmountResin.matches("") || Integer.parseInt(curAmountResin) < 0 || Integer.parseInt(curAmountResin)>=160) {
            Toast.makeText(this.getActivity(), "You did not enter a valid resin Amount", Toast.LENGTH_LONG).show();
            resinTimeAdapter = new ResinTimeAdapter(getActivity().getApplicationContext(), resinTimesList);
            binding.rvForResinTimes.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            binding.rvForResinTimes.setAdapter(resinTimeAdapter);
            return;
        }

        int parsedCurAmountResin = Integer.parseInt(binding.etCurResinInput.getText().toString());
        int minuteCounter = 0;

        Calendar currentTimeNow = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());

        for (int i = parsedCurAmountResin; i <=160; i++){
            if (i % 20 == 0 && i != 0 && i != parsedCurAmountResin){
                resinTimesList.add(new ResinTime(sdf.format(currentTimeNow.getTime()), i, minuteCounter));

                resinTimeAdapter = new ResinTimeAdapter(getActivity().getApplicationContext(), resinTimesList);
                binding.rvForResinTimes.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                binding.rvForResinTimes.setAdapter(resinTimeAdapter);
            }
            currentTimeNow.add(Calendar.MINUTE, 8);
            minuteCounter += 8;
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acbCalculateResinTimes) {
            calculateResinTimes();
        }
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "GenMateReminderChannel";
            String description = "Channel for GenMate Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel ("notifyUserResin", name, importance);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}
