package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DivideStoresGUI implements Initializable {

    private final DivideStoresController controller = new DivideStoresController();

    @FXML
    private TableView<String> subset1;

    @FXML
    private Label minimumDifferenceLabel;

    @FXML
    private TableColumn<String, String> idSubset1;

    @FXML
    private TableColumn<String, String> numberOfPropertiesSubset1;

    @FXML
    private TableColumn<String, String> idSubset2;

    @FXML
    private TableColumn<String, String> numberOfPropertiesSubset2;

    @FXML
    private TableView<String> subset2;

    /**
     * The Subsets Partition.
     */
    ObservableList<List<String>> subsets = FXCollections.observableArrayList(
            controller.findPartition()
    );

    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println(controller.findPartition());


    }

}
