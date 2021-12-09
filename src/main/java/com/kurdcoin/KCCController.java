package com.kurdcoin;

import com.kurdcoin.net.CRunner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class KCCController {
    @FXML
    private Pane mPane;




    @FXML
    public void cContinues(ActionEvent event) throws IOException {
        mPane.setVisible(false);
        System.out.println("yup" + CRunner.privk);
        System.out.println(CRunner.pubK);
        System.out.println(CRunner.seed);
        System.out.println(CRunner.wallet);

    }
}
