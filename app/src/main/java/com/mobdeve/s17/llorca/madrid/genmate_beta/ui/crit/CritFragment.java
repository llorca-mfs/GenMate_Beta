package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.crit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acbCalculateCritScore) {
            calculateCritScore();
        }
    }

    public void calculateCritScore(){

        binding.textCritScore.setText("");

        double critScore;

        if (binding.etCritRateInput.getText().toString().matches("") || binding.etCritDmgInput.getText().toString().matches("")) {
            Snackbar mySnackbar = Snackbar.make(binding.getRoot(), "Please complete inputs", Snackbar.LENGTH_LONG);
            mySnackbar.show();
            return;
        }

        double critRate = Double.parseDouble(String.valueOf(binding.etCritRateInput.getText()));
        double critDmg = Double.parseDouble(String.valueOf(binding.etCritDmgInput.getText()));
        critScore = ((critRate * critDmg)/100.0) + 100.0;

        binding.textCritScore.setText(String.format("%.2f",critScore) + "%");
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
