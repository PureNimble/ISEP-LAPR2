package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.AgentUI;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgentMenuGUI implements Runnable, Initializable {

    private Label labelAgentMenu;

    public void listMessages(javafx.scene.input.MouseEvent mouseEvent) {
        loadPage();
    }

    public void run() {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadPage() {
        try {
            FXMLLoader listMessagesLoader = new FXMLLoader();
            listMessagesLoader.setLocation(getClass().getResource("/ListMessages.fxml"));
            Parent root = listMessagesLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            listMessagesLoader.setController(new ListMessageGUI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
