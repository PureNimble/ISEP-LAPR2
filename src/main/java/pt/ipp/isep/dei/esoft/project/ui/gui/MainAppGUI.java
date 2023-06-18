package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

/**
 * The type Main app gui.
 */
public class MainAppGUI extends Application {


    public  void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Real Estate USA - Application");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) throws IOException {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.run();
            Repositories.getInstance().deserialize();
            launch();
            Repositories.getInstance().serialize();
    }


}
