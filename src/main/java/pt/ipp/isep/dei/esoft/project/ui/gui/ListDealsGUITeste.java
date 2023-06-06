package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListDealsGUITeste implements Initializable {


    @FXML
    private TableColumn<Offer, Double> orderAmount;

    @FXML
    private TableColumn<Offer, String> name;

    @FXML
    private Pagination photosPagination;

    @FXML
    private ChoiceBox<?> filterChoice;

    @FXML
    private TableView<Object> table;

    @FXML
    private TextArea textArea;

    @FXML
    private Label announcementLabel;


    private final ListDealsController controller = new ListDealsController();

    private PublishedAnnouncement publishedAnnouncement = null;


    ObservableList<Object> listDeals = FXCollections.observableArrayList(
            controller.getOfferMostRecent()
    );


    public void initialize(URL url, ResourceBundle resourceBundle) {



        name.setCellValueFactory(new PropertyValueFactory<Offer, String>("name"));
        orderAmount.setCellValueFactory(new PropertyValueFactory<Offer, Double>("orderAmount"));


        table.setItems(listDeals);

    }


    @FXML
    public void clickItem(MouseEvent mouseEvent) throws FileNotFoundException {

        System.out.println("" + table.getSelectionModel().getSelectedItem());

        for (Object offers : listDeals) {
            if (offers.toString().equals(table.getSelectionModel().getSelectedItem().toString())) {
                Offer offer = (Offer) table.getSelectionModel().getSelectedItem();
                publishedAnnouncement = offer.getPublishedAnnouncement();
            }
        }


      textArea.setVisible(true);
      textArea.setText(""+publishedAnnouncement);

        int indexStart = 0;
        int indexEnd = 0;

        for(String line : textArea.getText().split("\n")) {
            if (line.contains("Photos:")) { //change this to whatever you need
                indexStart = textArea.getText().indexOf(line);
                indexEnd = indexStart + line.length();
            }
        }
            textArea.deleteText(indexStart,indexEnd);

        List<Image> images = new ArrayList<>();
        for (String url : publishedAnnouncement.getProperty().getPhotos().getUrl()) {

            images.add(new Image(url,477,402,false,false));

        }

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);


        photosPagination.setVisible(true);
        photosPagination.setPageCount(publishedAnnouncement.getProperty().getPhotos().getUrl().size());
        photosPagination.setPageFactory(n -> new ImageView(images.get(n)));

        announcementLabel.setVisible(true);

        createPage(0);

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
