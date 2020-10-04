package com.example.clienteparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.clienteparcial.model.Coordenadas;
import com.google.gson.Gson;

import java.util.UUID;

public class Control extends AppCompatActivity implements View.OnClickListener, OnMessageListener{

    private Button right,left,down, up, color;
    int x,y;
    private TCPsingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        right = findViewById(R.id.derecha);
        left = findViewById(R.id.izquierda);
        down = findViewById(R.id.abajo);
        up = findViewById(R.id.arriba);
        color = findViewById(R.id.color);

        right.setOnClickListener(this);
        left.setOnClickListener(this);
        down.setOnClickListener(this);
        up.setOnClickListener(this);
        color.setOnClickListener(this);

        tcp = TCPsingleton.getInstance();
        tcp.setObserver(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.derecha:

                x +=10;

                break;

            case R.id.izquierda:

                x-=10;

                break;

            case R.id.abajo:

                y+=10;

                break;

            case R.id.arriba:

                y-=10;

                break;

        }

        String id = UUID.randomUUID().toString();
        Coordenadas coordenadas = new Coordenadas(x,y,id);
        Gson gson = new Gson();
        String json = gson.toJson(coordenadas);

        Log.e("TCP",""+json);

        tcp.enviar(json);

    }

    @Override
    public void messageReceived(String msg) {

        Log.e("Servidor",""+msg);

    }
}