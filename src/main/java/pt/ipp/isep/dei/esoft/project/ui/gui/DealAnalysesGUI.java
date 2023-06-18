package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pt.ipp.isep.dei.esoft.project.application.controller.DealAnalysesController;
import pt.ipp.isep.dei.esoft.project.domain.RegressionDTO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Deal Analyses gui.
 */
public class DealAnalysesGUI implements Initializable {

    /*
        Controller for managing the Deaks Analyses.
    */
    private final DealAnalysesController controller = new DealAnalysesController();

    @FXML
    private AnchorPane dealAnalysesPane;

    @FXML
    private AnchorPane multiLinearPane;

    @FXML
    private AnchorPane singleLinearPane;

    @FXML
    private ChoiceBox<String> modelChoiceBox;
    private String[] regressionModel = {"Multi-Linear", "Simple Linear"};

    @FXML
    private Button chosenModelNextButton;

    @FXML
    private TextField multiArea;

    @FXML
    private TextField multiDistance;

    @FXML
    private TextField multiNBedrooms;

    @FXML
    private TextField multiNBathrooms;

    @FXML
    private TextField multiParking;

    @FXML
    private Button sendValuesButton;

    @FXML
    private TextField singleValue;

    @FXML
    private RadioButton areaButton;

    @FXML
    private RadioButton distanceButton;

    @FXML
    private RadioButton bedroomButton;

    @FXML
    private RadioButton bathroomButton;

    @FXML
    private RadioButton parkingButton;

    @FXML
    private Button sendValueButton;

    @FXML
    private ToggleGroup param;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        multiLinearPane.setVisible(false);
        singleLinearPane.setVisible(false);
        chosenModelNextButton.setDisable(true);
        modelChoiceBox.getItems().addAll(regressionModel);
        modelChoiceBox.setOnAction(event -> chosenModelNextButton.setDisable(false));
        chosenModelNextButton.setOnAction(this::getRegressionModel);
    }

    public void getRegressionModel(ActionEvent event) {
        String regressionModel = modelChoiceBox.getValue();
        dealAnalysesPane.setVisible(false);
        if (regressionModel == "Multi-Linear") {
            multiLinear();
        } else simpleLinear();
    }

    public void multiLinear() {
        multiLinearPane.setVisible(true);
        sendValuesButton.setOnAction(this::checkResultsMulti);
    }

    public void checkResultsMulti(ActionEvent event) {

        double[] valuesToPredict = new double[5];
        try {
            valuesToPredict[0] = Float.parseFloat(multiArea.getText());
            valuesToPredict[1] = Float.parseFloat(multiDistance.getText());
            valuesToPredict[2] = Float.parseFloat(multiNBedrooms.getText());
            valuesToPredict[3] = Float.parseFloat(multiNBathrooms.getText());
            valuesToPredict[4] = Float.parseFloat(multiParking.getText());
            for (int i = 0; i < 5; i++) {
                if (valuesToPredict[i] < 0) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid input detected");
            alert.setContentText("Please enter valid numeric values in the text fields.");
            alert.showAndWait();

            multiArea.clear();
            multiDistance.clear();
            multiNBedrooms.clear();
            multiNBathrooms.clear();
            multiParking.clear();
            return;
        }
        RegressionDTO regressionDTO = controller.regressionModel(-1, 0.05, valuesToPredict);

        if (regressionDTO.getReport() != null){
                System.out.println("\n" + regressionDTO.getReport());
            }

        if (regressionDTO.getPrediction() != null){
            System.out.println("Prediction: " + regressionDTO.getPrediction());
        }
    }

    public void simpleLinear() {
        singleLinearPane.setVisible(true);
        areaButton.setUserData(1);
        distanceButton.setUserData(2);
        bedroomButton.setUserData(3);
        bathroomButton.setUserData(4);
        parkingButton.setUserData(5);
        sendValueButton.setOnAction(this::checkResultSimple);
    }

    public void checkResultSimple(ActionEvent event) {

        double option;
        try {
            option = Float.parseFloat(singleValue.getText());
            if (option < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid input detected");
            alert.setContentText("Please enter valid numeric values in the text fields.");
            alert.showAndWait();

            singleValue.clear();
            return;
        }

        RadioButton selectedRadioButton = (RadioButton) param.getSelectedToggle();
        int selectedNumber = (int) selectedRadioButton.getUserData();
        double[] value ={option};

        RegressionDTO regressionDTO = controller.regressionModel(selectedNumber, 0.05, value);

        if (regressionDTO.getReport() != null){
                System.out.println("\n" + regressionDTO.getReport());
            }

        if (regressionDTO.getPrediction() != null){
            System.out.println("Prediction: " + regressionDTO.getPrediction());
        }
    }

}
