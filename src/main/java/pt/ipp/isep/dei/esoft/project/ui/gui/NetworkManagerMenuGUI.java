package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Network manager menu gui.
 */
public class NetworkManagerMenuGUI implements Runnable, Initializable {

    @FXML
    private Label labelNetworkManagerMenu;

    /**
     * List deals.
     *
     * @param mouseEvent the mouse event
     */
    public void listDeals(javafx.scene.input.MouseEvent mouseEvent) {
        loadPageListDeals();
    }

    /**
     * Deal Analyses.
     *
     * @param mouseEvent the mouse event
     */
    public void dealAnalyses(javafx.scene.input.MouseEvent mouseEvent) {
        loadPageDealAnalyses();
    }


    public void run() {

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    private void loadPageListDeals() {


        try {
            FXMLLoader listDealsLoader = new FXMLLoader();
            listDealsLoader.setLocation(getClass().getResource("/ListDealsGUI.fxml"));
            Parent root = listDealsLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("List Deals Menu");
            stage.setScene(scene);
            stage.show();
            listDealsLoader.setController(new ListDealsGUI());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPageDivideStores() {

        try {
            FXMLLoader divideStoresLoader = new FXMLLoader();
            divideStoresLoader.setLocation(getClass().getResource("/DivideStores.fxml"));
            Parent root = divideStoresLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Divide Stores Menu");
            stage.setScene(scene);
            stage.show();
            divideStoresLoader.setController(new DivideStoresGUI());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPageDealAnalyses() {


        try {
            FXMLLoader dealAnalysesLoader = new FXMLLoader();
            dealAnalysesLoader.setLocation(getClass().getResource("/DealAnalyses.fxml"));
            Parent root = dealAnalysesLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            dealAnalysesLoader.setController(new DealAnalysesGUI());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void divideStores(MouseEvent mouseEvent) {
        Alert alert;
        alert = createAlert(Alert.AlertType.INFORMATION, "Operation Execution time", "This Operation might take some minutes please wait");
        alert.showAndWait();
        loadPageDivideStores();

    }

    public Alert createAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);

        return alert;
    }
}
