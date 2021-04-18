package com.example.lubrigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
    }

    public void showMainMenu ()  {
        Intent intent = new Intent(getApplicationContext(), MainMenuView.class);
        startActivity(intent);

    }

    public void signIn (View view)  {

        EditText emailEditText = (EditText) findViewById(R.id.editTextEmail);
        EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);

        if (emailEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")){
            Toast.makeText(this, "Cannot leave Username or Password fields empty !", Toast.LENGTH_SHORT).show();
        }else if (emailEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("admin")){
            showMainMenu();
        }else {
            Toast.makeText(this, "Incorrect Username or Password !", Toast.LENGTH_SHORT).show();
        }
    }
}
