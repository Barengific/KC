package com.kurdcoin.net;

import com.kurdcoin.KCController;
import com.kurdcoin.crypto.secp256k1.SecP256K1KeyGenerator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRunner extends Thread {

    public static String privk;
    public static String seed;
    public static String pubK;
    public static String wallet;

    public static String Ip;
    public static String msg;
    ActionEvent event;

    public void here(String privk) throws IOException {

    }

    public CRunner() {
    }

    public CRunner(ActionEvent event) {
        this.event = event;
    }

    @Override
    public synchronized void run() {
        try {
            System.out.println("cRunner: " + msg);
            if (msg.equals("1")) {
                //System.out.println(SecP256K1KeyGenerator.seed_phrase(privk.toString()).toString());
                ArrayList<String> seed = SecP256K1KeyGenerator.seed_phrase(privk.toString());

                Parent p = ((Node) event.getSource()).getScene().getRoot();
                ObservableList<Node> aa = p.getChildrenUnmodifiable();
//            System.out.println(aa.toString());
//            for(int i = 0; i < aa.size(); i++){
//                if(aa.get(i) == (Node)"TextField[id=tfSeedPhraseN, styleClass=text-input text-field]");
//            }
//            aa.get(3);
                //System.out.println(aa.get(3));
                TextField tfSeedPhraseN = (TextField) aa.get(4);
                tfSeedPhraseN.setText(seed.toString());

            } else if (msg.equals("2")) {
                Parent p = ((Node) event.getSource()).getScene().getRoot();
                ObservableList<Node> aa = p.getChildrenUnmodifiable();
                System.out.println(aa.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(CRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String readExIp() {
        URL whatismyip = null;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            Ip = in.readLine(); //you get the IP as a String
            System.out.println(Ip);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Ip;
    }
}
