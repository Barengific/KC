package com.kurdcoin.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SRunner extends Thread{
    ObjectOutputStream oos;
    ObjectInputStream ois;

    @Override
    public synchronized void run() {
        try {
            Socket socket = new Socket("localhost", 1234);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject("from SRunner");
            oos.flush();

            System.out.println("reading from SRunner: " + ois.readObject());
            oos.writeObject("from SRunner");
            oos.flush();

//            while (true) {
//
//            }
        } catch (Exception ex) {
            System.out.println("in SRun: " +ex);
        }
    }

}
