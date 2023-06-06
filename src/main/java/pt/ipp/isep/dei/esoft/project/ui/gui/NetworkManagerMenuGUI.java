package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.NetworkManagerUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NetworkManagerMenuGUI implements Runnable,Initializable {

    @FXML
    private Label labelNetworkManagerMenu;

    public void listDeals(javafx.scene.input.MouseEvent mouseEvent) {
        loadPage();
    }

    public void run(){

    }



    public void initialize(URL url, ResourceBundle resourceBundle){


    }



    private void loadPage(){


        try {
            FXMLLoader listDealsLoader = new FXMLLoader();
            listDealsLoader.setLocation(getClass().getResource("/ListDealsGUITeste.fxml"));
            Parent root = listDealsLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            listDealsLoader.setController(new ListDealsGUI());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
