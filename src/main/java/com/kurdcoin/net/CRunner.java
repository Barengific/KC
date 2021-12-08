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

public class CRunner extends Thread{

    public String privk;
    public void here(String privk) throws IOException {

    }

    public CRunner() {
    }
    ActionEvent event;
    public CRunner(ActionEvent event) {
        this.event = event;
    }

    @Override
    public synchronized void run() {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            System.out.println(ip);

            System.out.println(SecP256K1KeyGenerator.seed_phrase(privk.toString()).toString());
            ArrayList<String> seed = SecP256K1KeyGenerator.seed_phrase(privk.toString());
//            KCController kc = new KCController();
//            kc.seedPhrase(SecP256K1KeyGenerator.seed_phrase(privk.toString()));

            Parent p = ((Node)event.getSource()).getScene().getRoot();
            ObservableList<Node> aa = p.getChildrenUnmodifiable();
            //System.out.println(aa.toString());
            aa.get(3);
            System.out.println(aa.get(3));
            TextField tfSeedPhraseN = (TextField)aa.get(3);
            tfSeedPhraseN.setText(seed.toString());

//            tfSeedPhraseN.setText(seed.get(0));
//            for(int i = 1; i < seed.size(); i++){
//                tfSeedPhraseN.setText(tfSeedPhraseN.getText() + " " + seed.get(i));
//            }
//            tfSeedPhraseN.setText("madeee itt");
            //aa.set(3,(Node)tfSeedPhraseN);

//
//            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/fxml/kc.fxml"));
//            Parent calcRoot = loader.load();
//            KCController controller = loader.getController();
//            controller.tfSeedPhraseN.setText("made iittt");
//            Scene showCalc = new Scene(calcRoot, 500, 1000);

        } catch (Exception ex) {
            Logger.getLogger(CRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
