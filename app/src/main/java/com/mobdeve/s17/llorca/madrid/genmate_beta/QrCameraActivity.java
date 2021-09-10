package com.mobdeve.s17.llorca.madrid.genmate_beta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;
import com.mobdeve.s17.llorca.madrid.genmate_beta.dao.QrListDAO;
import com.mobdeve.s17.llorca.madrid.genmate_beta.dao.QrListDAOSQLImpl;
import com.mobdeve.s17.llorca.madrid.genmate_beta.model.QrFriend;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCameraActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView ScannerView;
    //private static int cameraInfo = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

        int currApi = Build.VERSION.SDK_INT;

        if(currApi >= Build.VERSION_CODES.M){
            /*
            if(checkPermission()){
                Toast.makeText(getApplicationContext(),"Permission Granted", Toast.LENGTH_LONG).show();
            }
            else{
                requestPermission();
            }*/
            if(!checkPermission()){
                requestPermission();
            }
        }
    }

    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(QrCameraActivity.this, new String[] {Manifest.permission.CAMERA},REQUEST_CAMERA);
    }


    /*
    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResult){
        switch (requestCode){
            case REQUEST_CAMERA:
                if (grantResult.length > 0){
                    boolean cameraAccept = grantResult[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccept){
                        Toast.makeText(getApplicationContext(),"Permission Granted by User", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Permission Denied by User", Toast.LENGTH_LONG).show();
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                            showMessageOKCancel("Grant camera permission to proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                        requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                                    }
                                }
                            });
                        return;
                        }
                    }
                }
                break;
        }
    }
    */
    @Override
    public void onResume(){
        super.onResume();

        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >= Build.VERSION_CODES.M){
            if(checkPermission()){
                if(ScannerView == null){
                    ScannerView = new ZXingScannerView(this);
                    setContentView(ScannerView);
                }
                ScannerView.setResultHandler(this);
                ScannerView.startCamera();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ScannerView.stopCamera();
        ScannerView = null;
    }
    /*
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener){
        new AlertDialog.Builder(QrCameraActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }*/

    @Override
    public void handleResult(Result result) {
        final String qrInfo = result.getText();
        String[] qrInfoDelimit = qrInfo.split("/");


        if(qrInfoDelimit[0].equals("GMmbdv")){
            QrListDAO qrListDAO = new QrListDAOSQLImpl(getApplicationContext());
            QrListAdapter qrListAdapter = new QrListAdapter(getApplicationContext(), qrListDAO.getQrFriends());

            QrFriend newFriend = new QrFriend();
            newFriend.setId(qrListDAO.getQrFriends().size());
            newFriend.setIgn(qrInfoDelimit[1]);
            newFriend.setUid(qrInfoDelimit[2]);

            qrListDAO.addQrFriend(newFriend);
            qrListAdapter.addQrFriends(qrListDAO.getQrFriends());

            Toast.makeText(getApplicationContext(),"Successfully added "+qrInfoDelimit[1]+" to the database!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"You have scanned an invalid QR Code", Toast.LENGTH_LONG).show();
        }

        finish();

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sample");
        builder.setPositiveButton("OK", (dialogInterface, i) -> finish());

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> onDestroy());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/
    }
}