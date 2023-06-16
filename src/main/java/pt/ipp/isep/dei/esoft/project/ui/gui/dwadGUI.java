package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class dwadGUI implements Initializable {


    @FXML
    private TableColumn<?, ?> storeIDSubset1;

    @FXML
    private TableColumn<?, ?> storeIDSubset2;

    @FXML
    private TableColumn<?, ?> numberOfPropertiesSubset1;

    @FXML
    private TableColumn<?, ?> numberOfPropertiesSubset2;

    @FXML
    private Label minimumDifference;

    @FXML
    private TableView<List<String>> subset1;

    @FXML
    private TableView<?> subset2;

    private final DivideStoresController controller = new DivideStoresController();


    public void initialize(URL url, ResourceBundle resourceBundle) {


        List<List<String>> lists = controller.findPartition();

        System.out.println(lists);





    }
}
