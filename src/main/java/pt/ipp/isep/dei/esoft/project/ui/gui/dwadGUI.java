package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;

import java.net.URL;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class dwadGUI implements Initializable {


    @FXML
    private TextArea textAreaSubset1;

    @FXML
    private TextArea textAreaSubset2;
    @FXML
    private Label minimumDifference;


    private final DivideStoresController controller = new DivideStoresController();


    public void initialize(URL url, ResourceBundle resourceBundle) {


        long initial = System.nanoTime();

        List<List<String>> list = controller.findPartition();

        long endTime = System.nanoTime();

        long executionTime = Math.abs(initial-endTime) ;

        System.out.println(executionTime);

        minimumDifference.setText("Minimun difference= " + list.get(2).get(0));

        textAreaSubset1.setText(createListString(list.get(0)).toString());

        textAreaSubset2.setText(createListString(list.get(1)).toString());


    }


    public StringBuilder createListString(List<String> subsets) {
        StringBuilder st = new StringBuilder();

        for (String string : subsets) {
            st.append(string);
            st.append("\n");
        }
        return st;
    }
}
