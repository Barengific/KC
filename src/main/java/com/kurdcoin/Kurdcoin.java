//
//

package com.kurdcoin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.kurdcoin.crypto.*;


/**
 * Kurdcoin
 */
public class Kurdcoin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/kc.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) { //throws NoSuchAlgorithmException {
//        byte[] genesisBytes = Chainers.genesisBlock().calcHash(Chainers.genesisBlock());
//        String genesisHash = bytesToHex(genesisBytes);
//        //System.out.println(genesisHash);
//
//        //System.out.println(a.hashCode());
//        Block aaa = new Block(0,"",
//                1614876976, "Here", 1, 12);
//
//        //System.out.println(Block.toStringHash(aaa));
//        //System.out.println(Block.toString(aaa));
//        findBlock(aaa);
        System.out.println("hello");

        //
        //
        launch(args);

    }

}