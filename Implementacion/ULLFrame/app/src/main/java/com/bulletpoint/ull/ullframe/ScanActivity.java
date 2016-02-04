package com.bulletpoint.ull.ullframe;

import android.app.Activity;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class ScanActivity extends Activity implements BeaconConsumer {

    protected static final String info = "MonitoringActivity";
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                logToDisplay("Welcome to the parking!");
            }

            @Override
            public void didExitRegion(Region region) {
                logToDisplay("Goodbye!");
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) { logToDisplay("Status: "+ i); }
        });
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                String message="";
                if (beacons != null && beacons.iterator().hasNext()) {
                    Beacon parkingBeacon = beacons.iterator().next();
                    if (parkingBeacon.getDistance() > 1.0 && parkingBeacon.getDistance() < 2.0) {
                        message="You are between 1.0 and 2.0 meters from me.";
                    } else if (parkingBeacon.getDistance() >= 2.0 && parkingBeacon.getDistance() < 3.0) {
                        message="You are between 2.0 and 3.0 meters from me.";
                    } else if (parkingBeacon.getDistance() > 3.0) {
                        message="You are farther than 3.0 meters away.";
                    } else if (parkingBeacon.getDistance() < 1.0) {
                        message = "You are : " + (double) Math.round(parkingBeacon.getDistance() * 1000d) / 1000d + " meters from me.";
                        logToDisplay(message);
                    }
                    logToDisplay(message);
                }
            }
        });
        beaconManager.setBackgroundScanPeriod(1100l);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                TextView textView = (TextView) ScanActivity.this
                        .findViewById(R.id.statusText);
                textView.setText(line);
            }
        });
    }

    public void startScan(View view){
        try {
            Log.i(info, "Changing to Monitoring...");
            beaconManager.stopRangingBeaconsInRegion((new Region("myRangingUniqueId", null, null, null)));
            beaconManager.startMonitoringBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }

    public void startDistanceScan(View view){
        try {
            Log.i(info, "Changing to Ranging");
            beaconManager.stopMonitoringBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }

    public void stopScan(View view){
        if(beaconManager.isBound(this)){
            try {
                beaconManager.stopMonitoringBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
                beaconManager.stopRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            logToDisplay("Stopping scan");
        }
    }


}
