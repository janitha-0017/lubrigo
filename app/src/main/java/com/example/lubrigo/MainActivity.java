package com.example.lubrigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        System.out.println("Output begins here");

//        python();
        System.out.println("Output ends here");




    }

//
//    private String python(){
//        Python python = Python.getInstance();
//        PyObject pythonFile = python.getModule("LassoML");
//        return pythonFile.callAttr("viz_polymonial").toString();
//    }







    public void login (View view)  {
        Intent intent = new Intent(getApplicationContext(), LoginView.class);
        startActivity(intent);

    }
}