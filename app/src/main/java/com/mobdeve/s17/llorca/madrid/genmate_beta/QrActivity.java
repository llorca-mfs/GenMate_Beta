package com.mobdeve.s17.llorca.madrid.genmate_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mobdeve.s17.llorca.madrid.genmate_beta.dao.QrListDAO;
import com.mobdeve.s17.llorca.madrid.genmate_beta.dao.QrListDAOSQLImpl;
import com.mobdeve.s17.llorca.madrid.genmate_beta.databinding.ActivityQrBinding;
import com.mobdeve.s17.llorca.madrid.genmate_beta.model.QrFriend;

import java.util.ArrayList;

public class QrActivity extends AppCompatActivity {

    private ArrayList<QrFriend> qrFriendArrayList;
    private ActivityQrBinding binding;
    private QrListAdapter qrListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        binding = ActivityQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //populateList();
        init();

        /*
        QrListDAO qrListDAO = new QrListDAOSQLImpl(getApplicationContext());
        qrListAdapter = new QrListAdapter(getApplicationContext(),qrListDAO.getQrFriends());
        binding.rvUid.setAdapter(qrListAdapter);
        */
        binding.btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(QrActivity.this, QrCameraActivity.class);
            startActivity(intent);
        });
    }

    private void init(){
        QrListDAO qrListDAO = new QrListDAOSQLImpl(getApplicationContext());
        qrListAdapter = new QrListAdapter(getApplicationContext(),qrListDAO.getQrFriends());
        binding.rvUid.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvUid.setAdapter(qrListAdapter);
    }

    private void populateList(){
        /*
        qrListItemArrayList = new ArrayList<>();
        qrListItemArrayList.add(new QrListItem("soraamamiya","12345678"));
        qrListItemArrayList.add(new QrListItem("rietakahashi","24681012"));
        qrListItemArrayList.add(new QrListItem("aikayano","42069696"));
        qrListItemArrayList.add(new QrListItem("junfukushima","12629511"));
        */

        QrListDAO qrListDAO = new QrListDAOSQLImpl(getApplicationContext());

        QrFriend qrFriend = new QrFriend(000, "kikorino", "123456789");
        qrListDAO.addQrFriend(qrFriend);
    }
}
