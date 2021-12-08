package com.kurdcoin;

import com.kurdcoin.core.Sha256;
import com.kurdcoin.crypto.KeyPair;
import com.kurdcoin.crypto.PrivateKey;
import com.kurdcoin.crypto.PublicKey;
import com.kurdcoin.crypto.secp256k1.SecP256K1KeyGenerator;
import com.kurdcoin.net.CRunner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that controls the frontend GUI client.
 */
public class KCController {
    SecP256K1KeyGenerator ss = new SecP256K1KeyGenerator();

    @FXML
    private Button importWallet;
    @FXML
    private Button newWallet;

    @FXML
    private TextField tfPrivKeyN;
    @FXML
    public TextField tfSeedPhraseN;
    @FXML
    private TextField tfPubKeyN;
    @FXML
    private TextField tfWalletAddrN;
    @FXML
    private TextField tfPrivKeyIm;
    @FXML
    private TextField tfSeedPhraseIm;

    @FXML
    private void onImportWallet(){
        System.out.println("improtinng");
    }
    @FXML
    private void onNewWallet(ActionEvent event) throws IOException {

        System.out.println("generating new wallet");

        KeyPair kp = ss.generateKeyPair();

        PrivateKey privk = kp.getPrivateKey();
        tfPrivKeyN.setText(privk.toString());
        PublicKey pubk = ss.derivePublicKey(privk);
        tfPubKeyN.setText(pubk.toString());
        tfWalletAddrN.setText(Sha256.hashes(pubk.toString()));
        CRunner cRun = new CRunner(event);
        cRun.privk = privk.toString();
        cRun.run();

    }
    public void seedPhrase(ArrayList<String> seed){
        //Stage.getScene().getRoot().
        tfSeedPhraseN.setText(seed.get(0));
        for(int i = 1; i < seed.size(); i++){
            tfSeedPhraseN.setText(tfSeedPhraseN.getText() + " " + seed.get(i));
        }

    }

    Stage stage;
    Parent scene;
    public void switchViews(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/kc111.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }




}
