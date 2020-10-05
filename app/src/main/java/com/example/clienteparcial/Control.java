package com.example.clienteparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.clienteparcial.model.Coordenadas;
import com.google.gson.Gson;

import java.util.UUID;

public class Control extends AppCompatActivity implements View.OnTouchListener, OnMessageListener{

    private Button right,left,down, up, color;
    boolean buttonPressed;
    int x =0;
    int y = 0;
    int r,g,b;
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

        right.setOnTouchListener(this);
        left.setOnTouchListener(this);
        down.setOnTouchListener(this);
        up.setOnTouchListener(this);
        color.setOnTouchListener(this);

        tcp = TCPsingleton.getInstance();
        tcp.setObserver(this);


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                buttonPressed = true;

                new Thread(

                        ()->{

                            while (buttonPressed){

                                switch (view.getId()){

                                    case R.id.derecha:

                                        right.setBackgroundColor(Color.parseColor("#fe5f55"));

                                        if(x <=469){
                                            x +=10;
                                        }

                                        break;

                                    case R.id.izquierda:

                                        left.setBackgroundColor(Color.parseColor("#fe5f55"));

                                        if(x >= 32){
                                            x-=10;
                                        }

                                        break;

                                    case R.id.abajo:

                                        down.setBackgroundColor(Color.parseColor("#fe5f55"));


                                        if(y <=469) {
                                            y += 10;
                                        }
                                        break;

                                    case R.id.arriba:

                                        up.setBackgroundColor(Color.parseColor("#fe5f55"));

                                        if(y >= 32){
                                            y-=10;
                                        }
                                        break;

                                    case R.id.color:

                                        color.setBackgroundColor(Color.parseColor("#fe5f55"));

                                        r = (int) (Math.random()*255+1);
                                        g = (int) (Math.random()*255+1);
                                        b = (int) (Math.random()*255+1);

                                        break;

                                }

                                String id = UUID.randomUUID().toString();
                                Coordenadas coordenadas = new Coordenadas(x,y,r,g,b,id);
                                Gson gson = new Gson();
                                String json = gson.toJson(coordenadas);
                                tcp.enviar(json);

                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }


                        }

                ).start();

                break;

            case MotionEvent.ACTION_UP:

                buttonPressed = false;

                right.setBackgroundColor(Color.parseColor("#FFFFFF"));
                left.setBackgroundColor(Color.parseColor("#FFFFFF"));
                down.setBackgroundColor(Color.parseColor("#FFFFFF"));
                up.setBackgroundColor(Color.parseColor("#FFFFFF"));
                color.setBackgroundColor(Color.parseColor("#FFFFFF"));




                break;
        }
        return false;
    }

    @Override
    public void messageReceived(String msg) {


    }


}