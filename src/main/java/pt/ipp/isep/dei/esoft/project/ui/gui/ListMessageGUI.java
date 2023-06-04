package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ListMessageGUI implements Initializable {

    @FXML
    private TableView<Object> table;

    @FXML
    private ChoiceBox<String> filterChoice;

    private String[] filterCriteria = {"Ascending Date"};

    @FXML
    private TableColumn<Message, String> name;

    @FXML
    private TableColumn<Message, Long> phoneNumber;

    @FXML
    private TableColumn<Message, String> description;

    @FXML
    private TableColumn<Message, Date> initialDate;

    @FXML
    private TableColumn<Message, Integer> initialTime;

    @FXML
    private TableColumn<Message, Integer> endTime;

    @FXML
    private TableColumn<Message, PublishedAnnouncement> publishedAnnouncement;

    private final ListMessageController controller = new ListMessageController();

    ObservableList<Object> listMessages = FXCollections.observableArrayList(controller.getMessagesByAscendingDate());

    public void initialize(URL url, ResourceBundle resourceBundle) {

        filterChoice.getItems().addAll(filterCriteria);
        filterChoice.setOnAction(this::getCriteria);

        name.setCellValueFactory(new PropertyValueFactory<Message, String>("name"));

        phoneNumber.setCellValueFactory(new PropertyValueFactory<Message, Long>("phoneNumber"));
//        phoneNumber.setCellFactory(column -> new TextFieldTableCell<Message, PublishedAnnouncement>() {
//
//            @Override
//            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
//                super.updateItem(publishedAnnouncement, empty);
//                if (empty || publishedAnnouncement == null) {
//                    setText("");
//                } else {
//                    setText("" + publishedAnnouncement.get);
//                }
//            }
//
//
//        });

        description.setCellValueFactory(new PropertyValueFactory<Message, String>("description"));

        initialDate.setCellValueFactory(new PropertyValueFactory<Message, Date>("initialDate"));

        initialTime.setCellValueFactory(new PropertyValueFactory<Message, Integer>("initialTime"));

        endTime.setCellValueFactory(new PropertyValueFactory<Message, Integer>("endTime"));

        publishedAnnouncement.setCellValueFactory(new PropertyValueFactory<Message, PublishedAnnouncement>("publishedAnnouncement"));

        table.setItems(listMessages);


    }

    private void getCriteria(javafx.event.ActionEvent actionEvent) {
        String choiceOption = filterChoice.getValue();

        if (choiceOption.equals("Ascending Date")) {
            listMessages.clear();
            listMessages.addAll(controller.getMessagesByAscendingDate());
        }
    }







}
