package com.example.lubrigo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class OilTesterView extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private boolean isTestDone = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oil_quality_tester);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }

        BluetoothDevice hc06 = bluetoothAdapter.getRemoteDevice("00:20:10:08:11:D3");
        System.out.println(hc06.getName());

        BluetoothSocket bluetoothSocket = null;
        int count = 0;
        do {
            try {

                bluetoothSocket = hc06.createRfcommSocketToServiceRecord(mUUID);
                System.out.println(bluetoothSocket);
                bluetoothSocket.connect();
                System.out.println("Bluetooth Socket connected : " + bluetoothSocket.isConnected());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Test1");
            }
            count++;
        }while(!bluetoothSocket.isConnected() && count<3);



        try {
            assert bluetoothSocket != null;
            OutputStream outputStream = bluetoothSocket.getOutputStream();
            System.out.println("Test2");
            outputStream.write(48);
            System.out.println("Test3");
        } catch (IOException e) {
            e.printStackTrace();
        }


        InputStream inputStream = null;
        int bytes; // bytes returned from read()
        byte a;
        ArrayList<Byte> readings = null;
        byte[] buffer = new byte[256];
        try {
//            while(!isTestDone) {
                System.out.println("Test4");
                inputStream = bluetoothSocket.getInputStream();
                System.out.println("Test5");

                String reading = inputStream.toString();
                a = (byte) inputStream.read();
                bytes = inputStream.read(buffer);
                String readMessage = new String(buffer, 0, bytes);
                System.out.println(readMessage);

//                    readings.add(a);


//            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        System.out.println(pairedDevices);
    }

    //button click method to stop the oil test
    public void endTest ()  {
        isTestDone = true;
    }
}
