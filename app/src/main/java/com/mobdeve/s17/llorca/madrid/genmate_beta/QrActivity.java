package com.mobdeve.s17.llorca.madrid.genmate_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.ActivityQrBinding;

import java.util.ArrayList;

public class QrActivity extends AppCompatActivity {

    private ArrayList<QrListItem> qrListItemArrayList;
    private ActivityQrBinding binding;
    private QrListAdapter qrListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        binding = ActivityQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        populateList();

        qrListAdapter = new QrListAdapter(getApplicationContext(),qrListItemArrayList);
        binding.rvUid.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvUid.setAdapter(qrListAdapter);

        binding.btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(QrActivity.this, QrCameraActivity.class);
            startActivity(intent);
        });
    }

    private void populateList(){
        qrListItemArrayList = new ArrayList<>();
        qrListItemArrayList.add(new QrListItem("soraamamiya","12345678"));
        qrListItemArrayList.add(new QrListItem("rietakahashi","24681012"));
        qrListItemArrayList.add(new QrListItem("aikayano","42069696"));
        qrListItemArrayList.add(new QrListItem("junfukushima","12629511"));
    }
}
