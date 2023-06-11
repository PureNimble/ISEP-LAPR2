package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.RespondBookingRequestGUI;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ListMessageGUI implements Initializable {

    @FXML
    private TableView<Object> table;

    @FXML
    private ChoiceBox<String> filterChoice;

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
    private Pagination photosPagination;

    @FXML
    private TextArea textArea;
    @FXML
    private Label announcementLabel;

    private PublishedAnnouncement publishedAnnouncement = null;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private Button btFilterSchedules;

    @FXML
    private Button btRespond;

    private final ListMessageController controller = new ListMessageController();

    ObservableList<Object> listMessages = FXCollections.observableArrayList(controller.getMessagesByAscendingDate());

    public void initialize(URL url, ResourceBundle resourceBundle) {

        name.setCellValueFactory(new PropertyValueFactory<Message, String>("name"));

        phoneNumber.setCellValueFactory(new PropertyValueFactory<Message, Long>("phoneNumber"));

        description.setCellValueFactory(new PropertyValueFactory<Message, String>("description"));

        initialDate.setCellValueFactory(new PropertyValueFactory<Message, Date>("initialDate"));

        initialTime.setCellValueFactory(new PropertyValueFactory<Message, Integer>("initialTime"));

        endTime.setCellValueFactory(new PropertyValueFactory<Message, Integer>("endTime"));

        table.setItems(listMessages);

        dpStartDate.setValue(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
        dpEndDate.setValue(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
        btFilterSchedules.setOnAction(event -> handleFilterButtonPressed());
        btRespond.setOnAction(event -> handleRespondButtonPressed());



    }

    @FXML
    public void clickItem(MouseEvent mouseEvent) throws FileNotFoundException {
        System.out.println("" + table.getSelectionModel().getSelectedItem());

        for (Object messages : listMessages) {
            if (messages.toString().equals(table.getSelectionModel().getSelectedItem().toString())) {
                Message message = (Message) table.getSelectionModel().getSelectedItem();
                publishedAnnouncement = message.getPublishedAnnouncement();
            }
        }

        textArea.setVisible(true);
        textArea.setText(""+publishedAnnouncement);

        int indexStart = 0;
        int indexEnd = 0;

        for (String line : textArea.getText().split("\n")) {
            if (line.contains("Photos:")) {
                indexStart = textArea.getText().indexOf(line);
                indexEnd = indexStart + line.length();
            }
        }
        textArea.deleteText(indexStart,indexEnd);

        List<Image> images = new ArrayList<>();
        for (String url : publishedAnnouncement.getProperty().getPhotos().getUrl()) {
            images.add(new Image(url, 383, 354, false, false));
        }

        ImageView imageView = new ImageView();
        imageView.setFitHeight(354);
        imageView.setFitWidth(383);

        photosPagination.setVisible(true);
        photosPagination.setPageCount(publishedAnnouncement.getProperty().getPhotos().getUrl().size());
        photosPagination.setPageFactory(n -> new ImageView(images.get(n)));
        announcementLabel.setVisible(true);

        dpStartDate.setValue(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
        dpEndDate.setValue(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());



    }

    private void updateFilteredMessages() {
        Date startDate = Date.from(dpStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(dpEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Call the ListMessageController to get the filtered list of messages
        List<Message> filteredMessages = controller.getMessageRequestsForPeriod(startDate, endDate);

        // Update the listMessages observable list with the filtered messages
        listMessages.setAll(filteredMessages);
    }

    @FXML
    public void handleFilterButtonPressed() {
        updateFilteredMessages();
    }

    @FXML
    public void handleRespondButtonPressed() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RespondBookingRequest.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Respond To Booking Request");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
