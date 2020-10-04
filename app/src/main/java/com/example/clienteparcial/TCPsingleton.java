package com.example.clienteparcial;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPsingleton extends Thread{

    private static TCPsingleton instance;

    private TCPsingleton() {}

    public static TCPsingleton getInstance() {

        if(instance == null) {
            instance = new TCPsingleton();
            instance.start();

        }
        return instance;

    }

    private BufferedWriter writer;
    private Socket socket;
    private OnMessageListener observer;

    public void setObserver(OnMessageListener observer) {

        this.observer = observer;
    }

    public void run(){

        try {

            Log.e("TCP","conectando..");
            socket = new Socket("192.168.0.42",4000);
            Log.e("TCP","conectado");

            InputStream is = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            writer = new BufferedWriter(new OutputStreamWriter(out));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while(true) {

                String mensaje = reader.readLine();
                observer.messageReceived(mensaje);

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void enviar(String mgs) {

        new Thread(

                ()->{

                    try {
                        writer.write(mgs + "\n");
                        writer.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();


    }


}
