package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Network manager menu gui.
 */
public class StoreManagerMenuGUI implements Runnable, Initializable {

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
}
