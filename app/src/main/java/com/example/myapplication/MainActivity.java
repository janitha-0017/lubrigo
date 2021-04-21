package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    private static final String DB_URL= "jdbc:mysql://database-trialrun.cqkvxdikjvom.us-east-2.rds.amazonaws.com/Lubrigo";
    private static final String USER = "admin";
    private static final String PASS = "admin123";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRefer = findViewById(R.id.button);


        buttonRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Button CLicked");

                try {
                    Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);

                    if(connection==null){
                        System.out.println();
                    }else {
                        String query  = "SELECT * FROM Lubrigo.EngOil_01 where Lubrigo.EngOil_01.Brand=\"Mitsubishi\" AND Lubrigo.EngOil_01.Year=1998";
                        Statement stmt = connection.createStatement();
                        stmt.execute(query);

                        System.out.println(connection);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}