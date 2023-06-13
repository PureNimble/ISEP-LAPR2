    package pt.ipp.isep.dei.esoft.project.ui.gui;

    import javafx.beans.property.SimpleObjectProperty;
    import javafx.beans.value.ObservableValue;
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
    import javafx.scene.input.KeyCode;
    import javafx.scene.input.KeyEvent;
    import javafx.scene.input.MouseEvent;
    import javafx.stage.Stage;
    import javafx.util.Callback;
    import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
    import pt.ipp.isep.dei.esoft.project.domain.*;


    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.net.URL;
    import java.time.Instant;
    import java.time.ZoneId;
    import java.util.*;

    /**
     * The type List message gui.
     */
    public class ListMessageGUI implements Initializable {

        @FXML
        private TableView<Object> tvBookingRequests;

        @FXML
        private ChoiceBox<String> filterChoice;

        @FXML
        private TableColumn<Message, String> name;

        @FXML
        private TableColumn<Message, Long> phoneNumber;

        @FXML
        private TableColumn<Message, String> description;

        @FXML
        private TableColumn<Message, Integer> propertyID;

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

        @FXML
        private Button btReturn;

        private final ListMessageController controller = new ListMessageController();

        /**
         * The List messages.
         */
        ObservableList<Object> listMessages = FXCollections.observableArrayList(controller.getMessagesByAscendingDate());

        public void initialize(URL url, ResourceBundle resourceBundle) {

            name.setCellValueFactory(new PropertyValueFactory<Message, String>("name"));

            phoneNumber.setCellValueFactory(new PropertyValueFactory<Message, Long>("phoneNumber"));

            description.setCellValueFactory(new PropertyValueFactory<Message, String>("description"));

            propertyID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Message, Integer>, ObservableValue<Integer>>() {
                @Override
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Message, Integer> param) {
                    PublishedAnnouncement publishedAnnouncement = param.getValue().getPublishedAnnouncement();
                    Integer propertyID = publishedAnnouncement != null ? publishedAnnouncement.getPropertyID() : null;
                    return new SimpleObjectProperty<>(propertyID);
                }
            });

            initialDate.setCellValueFactory(new PropertyValueFactory<Message, Date>("initialDate"));

            initialTime.setCellValueFactory(new PropertyValueFactory<Message, Integer>("initialTime"));

            endTime.setCellValueFactory(new PropertyValueFactory<Message, Integer>("endTime"));

            tvBookingRequests.setItems(listMessages);

            dpStartDate.setValue(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
            dpEndDate.setValue(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
            btFilterSchedules.setOnAction(event -> handleFilterButtonPressed());
        }

        /**
         * Click item.
         *
         * @param mouseEvent the mouse event
         * @throws FileNotFoundException the file not found exception
         */
        @FXML
        public void clickItem(MouseEvent mouseEvent) throws FileNotFoundException {
            System.out.println("" + tvBookingRequests.getSelectionModel().getSelectedItem());

            for (Object messages : listMessages) {
                if (messages.toString().equals(tvBookingRequests.getSelectionModel().getSelectedItem().toString())) {
                    Message message = (Message) tvBookingRequests.getSelectionModel().getSelectedItem();
                    publishedAnnouncement = message.getPublishedAnnouncement();
                }
            }

            textArea.setVisible(true);
            textArea.setText("" + publishedAnnouncement);

            int indexStart = 0;
            int indexEnd = 0;

            for (String line : textArea.getText().split("\n")) {
                if (line.contains("Photos:")) {
                    indexStart = textArea.getText().indexOf(line);
                    indexEnd = indexStart + line.length();
                }
            }
            textArea.deleteText(indexStart, indexEnd);

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

        /**
         * Handle filter button pressed.
         */
        @FXML
        public void handleFilterButtonPressed() {
            updateFilteredMessages();
        }

        /**
         * On bt return.
         *
         * @param actionEvent the action event
         */
        @FXML
        public void onBtReturn(ActionEvent actionEvent) {
            Stage stage = getStage();
            FXMLLoader agentMenuLoader = new FXMLLoader(getClass().getResource("/AgentMenuGUI.fxml"));
            Parent root = null;
            try {
                root = agentMenuLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Agent Menu");
            stage.show();
        }

        private Stage getStage() {
            return (Stage) btReturn.getScene().getWindow();
        }

        /**
         * On bt respond.
         */
        public void onBtRespond() {
            Message selectedMessage = (Message) tvBookingRequests.getSelectionModel().getSelectedItem();
            if (selectedMessage != null) {
                FXMLLoader respondToBookingRequestLoader = new FXMLLoader(getClass().getResource("/RespondToBookingRequest.fxml"));
                Parent root = null;
                try {
                    root = respondToBookingRequestLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                RespondToBookingRequestGUI respondController = respondToBookingRequestLoader.getController();
                respondController.setSelectedMessage(selectedMessage);
                respondController.setPublishedAnnouncement(selectedMessage.getPublishedAnnouncement()); // Set the publishedAnnouncement

                respondController = respondToBookingRequestLoader.getController();
                respondController.setSelectedMessage(selectedMessage);
                respondController.setPublishedAnnouncement(selectedMessage.getPublishedAnnouncement());
                respondController.setListMessageController(this);



                Stage stage = getStage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Respond To Booking Request");
                stage.show();
            }
        }

        /**
         * On key enter bt respond.
         *
         * @param mouseEvent the mouse event
         */
        public void onKeyEnterBtRespond(MouseEvent mouseEvent) {
                onBtRespond();
        }

        /**
         * Remove message.
         *
         * @param message the message
         */
        public void removeMessage(Message message) {
            listMessages.remove(message);
        }
    }
