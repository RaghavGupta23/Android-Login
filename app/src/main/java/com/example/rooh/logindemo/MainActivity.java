package com.example.rooh.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

public class MainActivity extends AppCompatActivity {

    private EditText UserId;
    private  EditText Password;
    private TextView Info;
    private Button Show;
    private Button Login;
    private int counter = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        UserId = (EditText) findViewById(R.id.etUserId);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.txtvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Show = (Button) findViewById(R.id.btnShPas);

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Show();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(UserId.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void Show() {
        if (Show.getText() == "Show") {
            Password.setInputType(TYPE_CLASS_TEXT);
            Show.setText("Hide");
        } else {
            Password.setInputType(TYPE_CLASS_TEXT|TYPE_TEXT_VARIATION_PASSWORD);
            Show.setText("Show");
        }
    }


    private void validate(String UserId, String Password){
        if ((UserId.equals("010013653")) && (Password.equals("CMPE#137"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
                    }
                    else{
            counter--;

            if(!UserId.equals("010013653") && !Password.equals("CMPE#137")) {
                Info.setText("invalid UserId and Password");
            } else if (!UserId.equals("010013653")) {
                Info.setText("invalid UserId");
            } else if (Password.toLowerCase().equals("cmpe#137")) {
                Info.setText("Try upper case");
            } else if (!Password.equals("CMPE#137")) {
                Info.setText("invalid Password");
            }

            if (counter == 0){
                Login.setEnabled(false);
               // Password.setEnabled(false);
                Info.setText("Too Many Attempts! Try Again in 30 seconds");

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Login.setEnabled(true);
                                counter = 3;
                                Info.setText("you can try signing in again now");
                            }
                        });
                    }
                }, 30000);


            }

        }

    }

}
