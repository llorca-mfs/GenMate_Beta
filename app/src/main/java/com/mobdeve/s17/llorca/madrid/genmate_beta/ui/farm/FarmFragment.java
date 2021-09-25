package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.farm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s17.llorca.madrid.genmate_beta.R;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentFarmBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FarmFragment extends Fragment {
    private FragmentFarmBinding binding;

    private Date currentDateAndTime = Calendar.getInstance().getTime();
    private SimpleDateFormat sdf =  new SimpleDateFormat("EEEE, MMMM dd, yyyy, HH:mm" , Locale.getDefault());
    private String formattedDate = sdf.format(currentDateAndTime);

    private ArrayList<Farmable> farmablesList;
    private FarmAdapter farmAdapter;


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFarmBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.RVFarmables;

        generateFarmables();

        farmAdapter = new FarmAdapter(getActivity().getApplicationContext(), farmablesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(farmAdapter);

        return root;

    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvDateToday.setText("Today is " + this.formattedDate + ". Right now, you can farm for the following:");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void generateFarmables(){
        farmablesList = new ArrayList<>();

        ArrayList<String> DaysAvailable = new ArrayList<>();
        boolean beforeFourOClock = false;

        Date currentDateAndTime= Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String formattedDate = sdf.format(currentDateAndTime);

        try {
            String genshinResetString = "04:00:00";
            String midnightString = "00:00:00";

            SimpleDateFormat currTimeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            String currTimeString = currTimeFormatter.format(currentDateAndTime);

            Date resetTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(genshinResetString);
            Date midnightTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(midnightString);
            Date currTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(currTimeString);

            Calendar resetTimeCalendar = Calendar.getInstance();
            assert resetTime != null;
            resetTimeCalendar.setTime(resetTime);

            Calendar midnightCalendar = Calendar.getInstance();
            assert midnightTime != null;
            midnightCalendar.setTime(midnightTime);

            Calendar currTimeCalendar = Calendar.getInstance();
            currTimeCalendar.setTime(currTime);

            Date tester = currTimeCalendar.getTime();

            if(tester.before(resetTimeCalendar.getTime()) && tester.after(midnightCalendar.getTime())){
                beforeFourOClock = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!beforeFourOClock){
            if ((formattedDate.equals("Monday") || formattedDate.equals("Thursday"))){
                DaysAvailable.add("Monday");
                DaysAvailable.add("Thursday");
                farmablesList.add(new Farmable("Freedom", R.drawable.teachings_of_freedom, DaysAvailable));
                farmablesList.add(new Farmable("Prosperity", R.drawable.teachings_of_prosperity, DaysAvailable));
                farmablesList.add(new Farmable("Transcience", R.drawable.teachings_of_transience, DaysAvailable));
                farmablesList.add(new Farmable("Decarabian", R.drawable.tile_of_decarabians_tower, DaysAvailable));
                farmablesList.add(new Farmable("Guyun", R.drawable.luminous_sands_from_guyun, DaysAvailable));
                farmablesList.add(new Farmable("Distant Sea", R.drawable.coral_branch_of_a_distant_sea, DaysAvailable));
            }
            else if (formattedDate.equals("Tuesday") || formattedDate.equals("Friday")){
                DaysAvailable.add("Tuesday");
                DaysAvailable.add("Friday");
                farmablesList.add(new Farmable("Resistance", R.drawable.teachings_of_resistance, DaysAvailable));
                farmablesList.add(new Farmable("Diligence", R.drawable.teachings_of_diligence, DaysAvailable));
                farmablesList.add(new Farmable("Elegance", R.drawable.teachings_of_elegance, DaysAvailable));
                farmablesList.add(new Farmable("Boreal Wolf", R.drawable.boreal_wolfs_milk_tooth, DaysAvailable));
                farmablesList.add(new Farmable("Mist Veiled Elixir", R.drawable.mist_veiled_lead_elixir, DaysAvailable));
                farmablesList.add(new Farmable("Narukami", R.drawable.narukamis_wisdom, DaysAvailable));
            }
            else if(formattedDate.equals("Wednesday") || formattedDate.equals("Saturday")){
                DaysAvailable.add("Wednesday");
                DaysAvailable.add("Saturday");
                farmablesList.add(new Farmable("Ballad", R.drawable.teachings_of_ballad, DaysAvailable));
                farmablesList.add(new Farmable("Gold", R.drawable.teachings_of_gold, DaysAvailable));
                farmablesList.add(new Farmable("Light", R.drawable.teachings_of_light, DaysAvailable));
                farmablesList.add(new Farmable("Dandelion Gladiator", R.drawable.fetters_of_the_dandelion_gladiator, DaysAvailable));
                farmablesList.add(new Farmable("Aerosiderite", R.drawable.grain_of_aerosiderite, DaysAvailable));
                farmablesList.add(new Farmable("Mask", R.drawable.mask_of_the_wicked_lieutenant, DaysAvailable));
            }
            else if(formattedDate.equals("Sunday")){
                DaysAvailable.add("Sunday");
                farmablesList.add(new Farmable("Freedom", R.drawable.teachings_of_freedom, DaysAvailable));
                farmablesList.add(new Farmable("Prosperity", R.drawable.teachings_of_prosperity, DaysAvailable));
                farmablesList.add(new Farmable("Transcience", R.drawable.teachings_of_transience, DaysAvailable));
                farmablesList.add(new Farmable("Decarabian", R.drawable.tile_of_decarabians_tower, DaysAvailable));
                farmablesList.add(new Farmable("Guyun", R.drawable.luminous_sands_from_guyun, DaysAvailable));
                farmablesList.add(new Farmable("Distant Sea", R.drawable.coral_branch_of_a_distant_sea, DaysAvailable));

                farmablesList.add(new Farmable("Resistance", R.drawable.teachings_of_resistance, DaysAvailable));
                farmablesList.add(new Farmable("Diligence", R.drawable.teachings_of_diligence, DaysAvailable));
                farmablesList.add(new Farmable("Elegance", R.drawable.teachings_of_elegance, DaysAvailable));
                farmablesList.add(new Farmable("Boreal Wolf", R.drawable.boreal_wolfs_milk_tooth, DaysAvailable));
                farmablesList.add(new Farmable("Mist Veiled Elixir", R.drawable.mist_veiled_lead_elixir, DaysAvailable));
                farmablesList.add(new Farmable("Narukami", R.drawable.narukamis_wisdom, DaysAvailable));

                farmablesList.add(new Farmable("Ballad", R.drawable.teachings_of_ballad, DaysAvailable));
                farmablesList.add(new Farmable("Gold", R.drawable.teachings_of_gold, DaysAvailable));
                farmablesList.add(new Farmable("Light", R.drawable.teachings_of_light, DaysAvailable));
                farmablesList.add(new Farmable("Dandelion Gladiator", R.drawable.fetters_of_the_dandelion_gladiator, DaysAvailable));
                farmablesList.add(new Farmable("Aerosiderite", R.drawable.grain_of_aerosiderite, DaysAvailable));
                farmablesList.add(new Farmable("Mask", R.drawable.mask_of_the_wicked_lieutenant, DaysAvailable));
            }
        }
        else{
            if (formattedDate.equals("Monday")){
                DaysAvailable.add("Sunday");
                farmablesList.add(new Farmable("Freedom", R.drawable.teachings_of_freedom, DaysAvailable));
                farmablesList.add(new Farmable("Prosperity", R.drawable.teachings_of_prosperity, DaysAvailable));
                farmablesList.add(new Farmable("Transcience", R.drawable.teachings_of_transience, DaysAvailable));
                farmablesList.add(new Farmable("Decarabian", R.drawable.tile_of_decarabians_tower, DaysAvailable));
                farmablesList.add(new Farmable("Guyun", R.drawable.luminous_sands_from_guyun, DaysAvailable));
                farmablesList.add(new Farmable("Distant Sea", R.drawable.coral_branch_of_a_distant_sea, DaysAvailable));

                farmablesList.add(new Farmable("Resistance", R.drawable.teachings_of_resistance, DaysAvailable));
                farmablesList.add(new Farmable("Diligence", R.drawable.teachings_of_diligence, DaysAvailable));
                farmablesList.add(new Farmable("Elegance", R.drawable.teachings_of_elegance, DaysAvailable));
                farmablesList.add(new Farmable("Boreal Wolf", R.drawable.boreal_wolfs_milk_tooth, DaysAvailable));
                farmablesList.add(new Farmable("Mist Veiled Elixir", R.drawable.mist_veiled_lead_elixir, DaysAvailable));
                farmablesList.add(new Farmable("Narukami", R.drawable.narukamis_wisdom, DaysAvailable));

                farmablesList.add(new Farmable("Ballad", R.drawable.teachings_of_ballad, DaysAvailable));
                farmablesList.add(new Farmable("Gold", R.drawable.teachings_of_gold, DaysAvailable));
                farmablesList.add(new Farmable("Light", R.drawable.teachings_of_light, DaysAvailable));
                farmablesList.add(new Farmable("Dandelion Gladiator", R.drawable.fetters_of_the_dandelion_gladiator, DaysAvailable));
                farmablesList.add(new Farmable("Aerosiderite", R.drawable.grain_of_aerosiderite, DaysAvailable));
                farmablesList.add(new Farmable("Mask", R.drawable.mask_of_the_wicked_lieutenant, DaysAvailable));
            }
            else if(formattedDate.equals("Tuesday") || formattedDate.equals("Friday")){
                DaysAvailable.add("Monday");
                DaysAvailable.add("Thursday");
                farmablesList.add(new Farmable("Freedom", R.drawable.teachings_of_freedom, DaysAvailable));
                farmablesList.add(new Farmable("Prosperity", R.drawable.teachings_of_prosperity, DaysAvailable));
                farmablesList.add(new Farmable("Transcience", R.drawable.teachings_of_transience, DaysAvailable));
                farmablesList.add(new Farmable("Decarabian", R.drawable.tile_of_decarabians_tower, DaysAvailable));
                farmablesList.add(new Farmable("Guyun", R.drawable.luminous_sands_from_guyun, DaysAvailable));
                farmablesList.add(new Farmable("Distant Sea", R.drawable.coral_branch_of_a_distant_sea, DaysAvailable));
            }
            else if(formattedDate.equals("Wednesday") || formattedDate.equals("Saturday")){
                DaysAvailable.add("Tuesday");
                DaysAvailable.add("Friday");
                farmablesList.add(new Farmable("Resistance", R.drawable.teachings_of_resistance, DaysAvailable));
                farmablesList.add(new Farmable("Diligence", R.drawable.teachings_of_diligence, DaysAvailable));
                farmablesList.add(new Farmable("Elegance", R.drawable.teachings_of_elegance, DaysAvailable));
                farmablesList.add(new Farmable("Boreal Wolf", R.drawable.boreal_wolfs_milk_tooth, DaysAvailable));
                farmablesList.add(new Farmable("Mist Veiled Elixir", R.drawable.mist_veiled_lead_elixir, DaysAvailable));
                farmablesList.add(new Farmable("Narukami", R.drawable.narukamis_wisdom, DaysAvailable));
            }
            else if(formattedDate.equals("Thursday") || formattedDate.equals("Sunday")){
                DaysAvailable.add("Wednesday");
                DaysAvailable.add("Saturday");
                farmablesList.add(new Farmable("Ballad", R.drawable.teachings_of_ballad, DaysAvailable));
                farmablesList.add(new Farmable("Gold", R.drawable.teachings_of_gold, DaysAvailable));
                farmablesList.add(new Farmable("Light", R.drawable.teachings_of_light, DaysAvailable));
                farmablesList.add(new Farmable("Dandelion Gladiator", R.drawable.fetters_of_the_dandelion_gladiator, DaysAvailable));
                farmablesList.add(new Farmable("Aerosiderite", R.drawable.grain_of_aerosiderite, DaysAvailable));
                farmablesList.add(new Farmable("Mask", R.drawable.mask_of_the_wicked_lieutenant, DaysAvailable));
            }


        }


    }
}
