package com.example.lubrigo;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connection extends AsyncTask<Void,Void,Void> {
    int bytes;

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"oil_intensity\": \""+OilTesterView.ratio+"\"\r\n}");
        Request request = new Request.Builder()
                .url("https://gm6za5m0ta.execute-api.us-east-2.amazonaws.com/test/helloworld")
                .method("POST", body)
                .addHeader("X-Amz-Content-Sha256", "beaead3198f7da1e70d03ab969765e0821b24fc913697e929e726aeaebf0eba3")
                .addHeader("X-Amz-Date", "20210422T140934Z")
                .addHeader("Authorization", "AWS4-HMAC-SHA256 Credential=AKIAUCORP2XGURDEEGVR/20210422/us-east-2/execute-api/aws4_request, SignedHeaders=host;x-amz-content-sha256;x-amz-date, Signature=0181730f8da6dc3918b250ad46250b89a6d6b64438b041c79136851897c993bf")
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println("Working");
        Response response = null;
        try {
            response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        InputStream inputStream;

        byte[] buffer = new byte[256];


            inputStream = response.body().byteStream();
        try {
            bytes = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String readMessage = new String(buffer, 0, bytes);
        Float result = Float.parseFloat(readMessage);
        System.out.println("Result is :"+result);
        return null;
    }
}
