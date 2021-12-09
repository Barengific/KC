package com.kurdcoin.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class KRunner extends Thread {
    Socket socket;
    ServerSocket ssocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    //constructor to get the same socket from Server
    public KRunner(Socket socket) {
        this.socket = socket;
    }

    public KRunner() {
    }

    @Override
    public synchronized void run() {
        try {
            ssocket = new ServerSocket(1234);
            int peer = 1;
            System.out.println("****** listening ******");
            while (true) {
                socket = ssocket.accept();
                peer += 1;
                threader(socket, peer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void threader(Socket socket, int num) {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            Object obj = ois.readObject();
            System.out.println("reading from KRunner: " + obj.toString());

            oos.writeObject("from KRunner");
            oos.flush();
            System.out.println(ois.readObject());

//            while (true) {
//
//            }//count .. seconds before reseting states and

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
