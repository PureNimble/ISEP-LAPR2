package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.FileNotFoundException;
import java.net.URL;
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
            images.add(new Image(url, 477, 402, false, false));
        }

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        photosPagination.setVisible(true);
        photosPagination.setPageCount(publishedAnnouncement.getProperty().getPhotos().getUrl().size());
        photosPagination.setPageFactory(n -> new ImageView(images.get(n)));
        announcementLabel.setVisible(true);

    }

    public VBox createPage(int pageIndex){

        VBox vBox = new VBox();

        for (String url : publishedAnnouncement.getProperty().getPhotos().getUrl()) {

            Image image = new Image(url);
            ImageView imageView = new ImageView();

            imageView.setFitHeight(300);
            imageView.setFitWidth(300);

            imageView.setImage(image);

            vBox.getChildren().add(imageView);
        }
        return vBox;
    }

}
