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

import java.io.IOException;

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

    Stage stage;
    Parent scene;

    String rprivK;
    String rSeed;
    String rpubK;
    String rWallet;

    @FXML
    private void onImportWallet(){
        if(tfPrivKeyIm.getText().length() == 64){
            //TODO check validity of private key entered
        }else if(tfSeedPhraseIm.getText() != null){
            //TODO check validity of seed phrase entered
        }

    }

    @FXML
    private void onNewWallet(ActionEvent event) throws IOException {
        KeyPair kp = ss.generateKeyPair();
        PrivateKey privk = kp.getPrivateKey();
        tfPrivKeyN.setText(privk.toString());
        PublicKey pubk = ss.derivePublicKey(privk);
        tfPubKeyN.setText(pubk.toString());
        tfWalletAddrN.setText(Sha256.hashes(pubk.toString()));
        CRunner cRun = new CRunner(event);
        cRun.msg = "1";
        cRun.privk = privk.toString();
        cRun.run();

        rprivK = privk.toString();
        rSeed = tfSeedPhraseN.getText();
        rpubK = pubk.toString();
        rWallet = Sha256.hashes(pubk.toString());


    }
//    public void seedPhrase(ArrayList<String> seed){
//        //Stage.getScene().getRoot().
//        tfSeedPhraseN.setText(seed.get(0));
//        for(int i = 1; i < seed.size(); i++){
//            tfSeedPhraseN.setText(tfSeedPhraseN.getText() + " " + seed.get(i));
//        }
//    }

    @FXML
    public void switchToClient(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/kcc.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        CRunner cRun = new CRunner(event);
        cRun.msg = "2";
        cRun.privk = rprivK;
        cRun.pubK = rpubK;
        cRun.seed = rSeed;
        cRun.wallet = rWallet;
        cRun.run();
        window.setScene(tableViewScene);
        window.show();
    }

}
