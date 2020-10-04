package com.example.clienteparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener{

    private EditText nameText;
    private Button nextButton;
    private TCPsingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(this);

        tcp = TCPsingleton.getInstance();
        tcp.setObserver(this);


    }

    @Override
    public void onClick(View view) {

        String name = nameText.getText().toString();


        Intent i = new Intent(this,Control.class);
        startActivity(i);



    }

    @Override
    public void messageReceived(String msg) {

    }
}