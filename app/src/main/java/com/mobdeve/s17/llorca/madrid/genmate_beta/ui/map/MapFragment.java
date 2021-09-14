package com.mobdeve.s17.llorca.madrid.genmate_beta.ui.map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.mobdeve.s17.llorca.madrid.genmate_beta.R;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.FragmentMapBinding;

public class MapFragment extends Fragment implements View.OnClickListener{

    private FragmentMapBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMapBinding.inflate(inflater, container, false);

        binding.btnCc.setOnClickListener(this);
        binding.btnSs.setOnClickListener(this);
        binding.btnWi.setOnClickListener(this);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.pvGenshinMap.setImage(ImageSource.resource(R.drawable.teyvat_wi));
        binding.btnWi.setEnabled(false);
        binding.btnSs.setEnabled(true);
        binding.btnCc.setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_ss:
                binding.pvGenshinMap.setImage(ImageSource.resource(R.drawable.teyvat_ss));
                binding.btnSs.setEnabled(false);
                binding.btnWi.setEnabled(true);
                binding.btnCc.setEnabled(true);
                break;
            case R.id.btn_wi:
                binding.pvGenshinMap.setImage(ImageSource.resource(R.drawable.teyvat_wi));
                binding.btnWi.setEnabled(false);
                binding.btnSs.setEnabled(true);
                binding.btnCc.setEnabled(true);
                break;
            case R.id.btn_cc:
                binding.pvGenshinMap.setImage(ImageSource.resource(R.drawable.teyvat_cc));
                binding.btnCc.setEnabled(false);
                binding.btnWi.setEnabled(true);
                binding.btnSs.setEnabled(true);
                break;
        }
    }
}
