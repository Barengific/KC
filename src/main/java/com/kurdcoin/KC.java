//
//

package com.kurdcoin;

import com.kurdcoin.net.CRunner;
import com.kurdcoin.net.KRunner;
import com.kurdcoin.net.NodeRegistrar;
import com.kurdcoin.net.SRunner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.kurdcoin.crypto.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Kurdcoin
 */
public class KC extends Application {
    NodeRegistrar nr = new NodeRegistrar();
    CRunner cRun = new CRunner();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/kc.fxml"));
        primaryStage.setTitle("Kurdcoin Client");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException { //throws NoSuchAlgorithmException {
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

        //
        //
        launch(args);

    }
    public void initKc(){
        nr.setNodeIp(cRun.readExIp());
        nr.setPortNo(1234);
        nr.setNodeName("Bob");
        nr.setNodeId(nr.getNodeIp(), nr.getPortNo(), nr.getNodeName());

        KRunner kRun = new KRunner();
        kRun.start();
        SRunner sRun = new SRunner();
        sRun.start();

    }

}