package com.example.lubrigo;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OilTesterView extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private float inputReading;
    private float lightTestReading;
    private float oilSampleReading;

    public static float ratio;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oil_quality_tester);

        final Button buttonTestLight = findViewById(R.id.button7);
        final Button buttonTestSample = findViewById(R.id.button8);
        final Button buttonDisplayOilQuality = findViewById(R.id.button9);


        buttonTestLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightTestReading = lightSensorData();
                System.out.println("Test 1 "+lightTestReading);
            }
        });


        buttonTestSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oilSampleReading = lightSensorData();
                System.out.println("Test 2 "+oilSampleReading);
            }
        });

        buttonDisplayOilQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratio = oilSampleReading/lightTestReading;
                new Connection().execute();

            }
        });

//        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if(!bluetoothAdapter.isEnabled()) {
//            bluetoothAdapter.enable();
//        }
//
//        BluetoothDevice hc06 = bluetoothAdapter.getRemoteDevice("00:20:10:08:11:D3");
//        System.out.println(hc06.getName());
//
//        BluetoothSocket bluetoothSocket = null;
//        int count = 0;
//        do {
//            try {
//
//                bluetoothSocket = hc06.createRfcommSocketToServiceRecord(mUUID);
//                System.out.println(bluetoothSocket);
//                bluetoothSocket.connect();
//                System.out.println("Bluetooth Socket connected : " + bluetoothSocket.isConnected());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("Test1");
//            }
//            count++;
//        }while(!bluetoothSocket.isConnected() && count<3);
//
//
//
//        try {
//            assert bluetoothSocket != null;
//            OutputStream outputStream = bluetoothSocket.getOutputStream();
//            System.out.println("Test2");
//            outputStream.write(48);
//            System.out.println("Test3");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        InputStream inputStream = null;
//        int bytes; // bytes returned from read()
//        byte a;
//        ArrayList<Byte> readings = null;
//        byte[] buffer = new byte[256];
//        try {
////            while(!isTestDone) {
//                System.out.println("Test4");
//                inputStream = bluetoothSocket.getInputStream();
//                System.out.println("Test5");
//
//                String reading = inputStream.toString();
//                a = (byte) inputStream.read();
//                bytes = inputStream.read(buffer);
//                String readMessage = new String(buffer, 0, bytes);
//                System.out.println(readMessage);
//
////                    readings.add(a);
//
//
////            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            bluetoothSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
//        System.out.println(pairedDevices);
    }

    //button click method to stop the oil test
//    public void testLight(View view){
//        lightTestReading = lightSensorData();
//        System.out.println(lightTestReading);
//    }
//
//    public void testSample(View view){
//        oilSampleReading = lightSensorData();
//        System.out.println(oilSampleReading);
//    }
//
//
//    public void displayOilQuality(View view){
//
//    }




    //method to capture light sensor data via bluetooth and return it as a float
    public float lightSensorData(){
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


        InputStream inputStream;
        int bytes; // bytes returned from read()
        byte[] buffer = new byte[256];
        try {

            inputStream = bluetoothSocket.getInputStream();
            bytes = inputStream.read(buffer);
            String readMessage = new String(buffer, 0, bytes);
            inputReading = Float.parseFloat(readMessage);

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

        return inputReading;
    }







    public void aws() throws IOException {
        float ratio = oilSampleReading/lightTestReading;
//
////        awsRunnable myRunnable = new awsRunnable();
////        Thread t = new Thread(myRunnable);
////        t.start();
//
//        System.out.println("Start");
//        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
//                this.getApplicationContext(),"us-east-1:f82d38b4-c9eb-47ea-bca3-355976956591", // Identity pool ID
//                Regions.US_EAST_1 // Region
//        );
//
//// Create LambdaInvokerFactory, to be used to instantiate the Lambda proxy.
//        LambdaInvokerFactory factory = new LambdaInvokerFactory(this.getApplicationContext(),
//                Regions.US_EAST_1, cognitoProvider);
//
//        System.out.println("End");
//
//// Create the Lambda proxy object with a default Json data binder.
//// You can provide your own data binder by implementing
//// LambdaDataBinder.
//        final MyInterface myInterface = factory.build(MyInterface.class);
//
//        RequestClass request = new RequestClass(oilSampleReading/lightTestReading);
//// The Lambda function invocation results in a network call.
//// Make sure it is not called from the main thread.
//        new AsyncTask<RequestClass, Void, ResponseClass>() {
//            @Override
//            protected ResponseClass doInBackground(RequestClass... params) {
//                // invoke "echo" method. In case it fails, it will throw a
//                // LambdaFunctionException.
//                try {
//                    System.out.println("MILES");
//                    return myInterface.LubrigoTrial(params[0]);
//
//                } catch (LambdaFunctionException lfe) {
//                    return null;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(ResponseClass result) {
//                if (result == null) {
//                    return;
//                }
//            }
//        }.execute(request);

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"oil_intensity\": \"0.00059\"\r\n}");
//        Request request = new Request.Builder()
//                .url("https://gm6za5m0ta.execute-api.us-east-2.amazonaws.com/test/helloworld")
//                .method("POST", body)
//                .addHeader("X-Amz-Content-Sha256", "beaead3198f7da1e70d03ab969765e0821b24fc913697e929e726aeaebf0eba3")
//                .addHeader("X-Amz-Date", "20210422T140934Z")
//                .addHeader("Authorization", "AWS4-HMAC-SHA256 Credential=AKIAUCORP2XGURDEEGVR/20210422/us-east-2/execute-api/aws4_request, SignedHeaders=host;x-amz-content-sha256;x-amz-date, Signature=0181730f8da6dc3918b250ad46250b89a6d6b64438b041c79136851897c993bf")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        System.out.println("Working");
//        Response response = client.newCall(request).execute();
//        System.out.println(response);
    }
}
