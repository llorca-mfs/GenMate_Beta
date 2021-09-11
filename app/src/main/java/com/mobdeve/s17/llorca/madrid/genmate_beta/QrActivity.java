package com.mobdeve.s17.llorca.madrid.genmate_beta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.zxing.WriterException;
import com.mobdeve.s17.llorca.madrid.genmate_beta.dao.QrListDAO;
import com.mobdeve.s17.llorca.madrid.genmate_beta.dao.QrListDAOSQLImpl;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.ActivityQrBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.model.QrFriend;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrActivity extends AppCompatActivity {

    private ActivityQrBinding binding;
    private QrListAdapter qrListAdapter;

    public static final String TAG = "sharedPrefs";
    public static final String IGN = "ign";
    public static final String UID = "uid";

    private String ign_set;
    private String uid_set;
    private String qr_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        binding = ActivityQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(QrActivity.this, QrCameraActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        loadData();
        updateViews();
    }

    private void init(){
        QrListDAO qrListDAO = new QrListDAOSQLImpl(getApplicationContext());
        qrListAdapter = new QrListAdapter(getApplicationContext(),qrListDAO.getQrFriends());

        if(qrListDAO.getQrFriends().size() == 0){
            binding.tvNoFriends.setVisibility(View.VISIBLE);
            binding.rvUid.setVisibility(View.GONE);
        }
        else{
            binding.tvNoFriends.setVisibility(View.GONE);
            binding.rvUid.setVisibility(View.VISIBLE);
        }

        binding.rvUid.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvUid.setAdapter(qrListAdapter);
    }

    public void loadData(){
        SharedPreferences sp = getSharedPreferences(TAG, MODE_PRIVATE);
        uid_set = sp.getString(UID, "");
        ign_set = sp.getString(IGN, "");

        qr_data = "GMmbdv/"+ign_set+"/"+uid_set;
    }

    public void updateViews(){
        binding.tvIgn.setText("IGN: "+ ign_set);
        binding.tvUid.setText("UID: "+ uid_set);

        QRGEncoder qrgEncoder = new QRGEncoder(qr_data,null, QRGContents.Type.TEXT, 512);

        Bitmap qrCodeBmp = qrgEncoder.getBitmap();
        binding.ivQr.setImageBitmap(qrCodeBmp);
    }
}
