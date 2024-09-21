package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
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


    public javafx.scene.control.Button btReturn;
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


    /**
     * On bt return.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onBtReturn(ActionEvent actionEvent) {
        Stage stage = getStage();
        FXMLLoader listMessageLoader = new FXMLLoader(getClass().getResource("/MainMenu.fxml")); // Specify the correct file path here
        Parent root = null;
        try {
            root = listMessageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    private Stage getStage() {
        return (Stage) btReturn.getScene().getWindow();
    }
}
