package com.bulletpoint.ull.ullframe;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean bluetoothIsActive(){
        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return (bluetoothAdapter != null && bluetoothAdapter.isEnabled());
    }


    public void startScanning(View view){
        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothIsActive()) {
            startActivity(new Intent(this, ScanActivity.class));
        }
        else{
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "El Bluetooth ha sido activado", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ScanActivity.class));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Has cancelado la activacion", Toast.LENGTH_LONG).show();
            }
        }
    }
}
