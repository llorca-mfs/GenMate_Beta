package com.mobdeve.s17.llorca.madrid.genmate_beta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    public static final String TAG = "sharedPrefs";
    public static final String IGN = "ign";
    public static final String UID = "uid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUpdate.setOnClickListener(v -> {
            if(binding.etIgn.getText().toString().equals("") || binding.etUid.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Please fill up all the fields!", Toast.LENGTH_LONG).show();
            }
            else{
                saveData();
                binding.etIgn.setText("");
                binding.etUid.setText("");
                Toast.makeText(getApplicationContext(),"Successfully updated your Genshin Impact account info!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveData(){
        SharedPreferences sp = getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(IGN, binding.etIgn.getText().toString());
        editor.putString(UID, binding.etUid.getText().toString());

        editor.apply();
    }
}
