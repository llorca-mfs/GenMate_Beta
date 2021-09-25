package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.crit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mobdeve.s17.llorca.madrid.genmate_beta.R;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentCritBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.ui.resin.ResinTimeAdapter;

public class CritFragment extends Fragment implements View.OnClickListener{
    private FragmentCritBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCritBinding.inflate(inflater, container, false);

        binding.acbCalculateCritScore.setOnClickListener(this);
        binding.tvCrittitle.setText("Please enter your Crit Rate and Crit Damage to calculate your Crit Ratio");
        binding.textCritScore.setText("");

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acbCalculateCritScore) {
            if (binding.etCritRateInput.getText().toString().matches("") || binding.etCritDmgInput.getText().toString().matches("")) {
                Snackbar mySnackbar = Snackbar.make(v, "Please fill up all the text fields ", BaseTransientBottomBar.LENGTH_LONG);
                mySnackbar.show();
            }

            else{
                double critRate = Double.parseDouble(String.valueOf(binding.etCritRateInput.getText()));
                double critDmg = Double.parseDouble(String.valueOf(binding.etCritDmgInput.getText()));
                double critScore = ((critRate * critDmg)/100.0) + 100.0;

                binding.textCritScore.setText(String.format("%.2f",critScore) + "%");

                binding.textCritScore.setVisibility(View.VISIBLE);
                binding.etCritRateInput.setPadding(getDpAsPixels(20), getDpAsPixels(136), getDpAsPixels(20), getDpAsPixels(12));
                binding.tvCrittitle.setText("The Crit Ratio for "+critRate+"% Crit Rate and "+critDmg+"% Crit Damage is");
            }
        }
    }

    private int getDpAsPixels(int dp){
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
