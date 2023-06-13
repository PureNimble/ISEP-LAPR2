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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.House;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.Residence;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListDealsGUITeste implements Initializable {


    @FXML
    private TableColumn<Offer, Double> orderAmount;

    @FXML
    private Label labelNumberOfBathrooms;


    @FXML
    private Label labelparkingSpaces;

    @FXML
    private Label typeOfProperrtyLabel;

    @FXML
    private Label adressLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label typeOfBusinessLabel;


    @FXML
    private AnchorPane announcementAnchorPane;
    @FXML
    private Label labelNumberOfBedrooms;

    @FXML
    private TableColumn<Offer, String> name;


    @FXML
    private Label agentDescription;

    @FXML
    private Label clientDescription;
    @FXML
    private Pagination photosPagination;

    @FXML
    private ChoiceBox<String> filterChoice;

    private String[] filterCriteria = {"Sort Selection Algorithm", "Bubble Sort Algorithm"};

    @FXML
    private TableView<Object> table;

    @FXML
    private TextArea textArea;
    @FXML
    private Label priceLabel;

    @FXML
    private Label announcementLabel;


    @FXML
    private ChoiceBox<String> ascendOrDescendChoice;


    @FXML
    private ImageView numberBathroomsIcon;

    @FXML
    private ImageView SunExposureIcon;
    @FXML
    private ImageView parkingSpacesIcon;


    @FXML
    private ImageView numberBedroomsIcon;

    @FXML
    private ImageView basementICon;

    @FXML
    private Label labelBasement;

    @FXML
    private Label labelSunExposure;

    @FXML
    private Label labelLoft;


    @FXML
    private ImageView loftIcon;

    private String[] filterAscendOrDescend = {"Ascending", "Descending"};

    private final ListDealsController controller = new ListDealsController();

    private PublishedAnnouncement publishedAnnouncement = null;


    @FXML
    private Label labelDistanceCenter;

    @FXML
    private Label labelArea;


    ObservableList<Object> listDeals = FXCollections.observableArrayList(
            controller.getOfferMostRecent()
    );


    public void initialize(URL url, ResourceBundle resourceBundle) {



        name.setCellValueFactory(new PropertyValueFactory<Offer, String>("name"));
        orderAmount.setCellValueFactory(new PropertyValueFactory<Offer, Double>("orderAmount"));


        table.setItems(listDeals);

        filterChoice.getItems().addAll(filterCriteria);
        ascendOrDescendChoice.getItems().addAll(filterAscendOrDescend);
        filterChoice.setOnAction(this::getCriteria);

    }


    @FXML
    public void clickItem(MouseEvent mouseEvent) throws FileNotFoundException {


        System.out.println("" + table.getSelectionModel().getSelectedItem());

        Offer offer;

        for (Object offers : listDeals) {
            if (offers.toString().equals(table.getSelectionModel().getSelectedItem().toString())) {
                offer = (Offer) table.getSelectionModel().getSelectedItem();
                publishedAnnouncement = offer.getPublishedAnnouncement();
            }
        }



        announcementAnchorPane.setVisible(true);


        clientDescription.setText("Client: "+publishedAnnouncement.getClient().getName()+" "+publishedAnnouncement.getClient().getClientEmail() +" "+publishedAnnouncement.getClient().getPhoneNumber());
        agentDescription.setText("Responsible Agent: "+publishedAnnouncement.getAgent().getName()+" "+publishedAnnouncement.getAgent().getEmail()+" "+publishedAnnouncement.getAgent().getPhoneNumber());
        priceLabel.setText("" + publishedAnnouncement.getBusiness()+" $");
        labelDistanceCenter.setText(""+publishedAnnouncement.getProperty().getDistanceFromCityCenter());
        adressLabel.setText("" + publishedAnnouncement.getProperty().getAddress());
        typeOfBusinessLabel.setText("" + publishedAnnouncement.getTypeOfBusiness());
        typeOfProperrtyLabel.setText("" + publishedAnnouncement.getPropertyType());
        dateLabel.setText("" + publishedAnnouncement.getDate());
        labelArea.setText(""+publishedAnnouncement.getProperty().getArea());


        if (publishedAnnouncement.getProperty() instanceof House){
            House house = (House) publishedAnnouncement.getProperty();


            labelLoft.setVisible(true);
            loftIcon.setVisible(true);
            labelLoft.setText(""+house.getInhabitableLoft());


            labelBasement.setVisible(true);
            basementICon.setVisible(true);
            labelBasement.setText(""+house.getBasement());

            SunExposureIcon.setVisible(true);
            labelSunExposure.setVisible(true);
            labelSunExposure.setText(""+house.getSunExposure());

            numberBedroomsIcon.setVisible(true);
            labelNumberOfBedrooms.setVisible(true);
            labelNumberOfBedrooms.setText("" +house.getNumberOfBedrooms());

            parkingSpacesIcon.setVisible(true);
            labelparkingSpaces.setVisible(true);
            labelparkingSpaces.setText(""+house.getParkingSpaces());

            numberBathroomsIcon.setVisible(true);
            labelNumberOfBathrooms.setVisible(true);
            labelNumberOfBathrooms.setText(""+house.getNumberOfBathrooms());

    }else if (publishedAnnouncement.getPropertyType().getDesignation().equals("Appartment")){

            Residence residence = (Residence) publishedAnnouncement.getProperty();

            SunExposureIcon.setVisible(false);
            labelSunExposure.setVisible(false);
            basementICon.setVisible(false);
            labelBasement.setVisible(false);
            labelLoft.setVisible(false);
            loftIcon.setVisible(false);



            numberBedroomsIcon.setVisible(true);
            labelNumberOfBedrooms.setVisible(true);
            labelNumberOfBedrooms.setText("" +residence.getNumberOfBedrooms());

            parkingSpacesIcon.setVisible(true);
            labelparkingSpaces.setVisible(true);
            labelparkingSpaces.setText(""+residence.getParkingSpaces());

            numberBathroomsIcon.setVisible(true);
            labelNumberOfBathrooms.setVisible(true);
            labelNumberOfBathrooms.setText(""+residence.getNumberOfBathrooms());
        }else {
            SunExposureIcon.setVisible(false);
            labelSunExposure.setVisible(false);
            basementICon.setVisible(false);
            labelBasement.setVisible(false);
            labelLoft.setVisible(false);
            loftIcon.setVisible(false);

            numberBedroomsIcon.setVisible(false);
            labelNumberOfBedrooms.setVisible(false);


            numberBathroomsIcon.setVisible(false);
            labelNumberOfBathrooms.setVisible(false);

            parkingSpacesIcon.setVisible(false);
            labelparkingSpaces.setVisible(false);
        }


        List<Image> images = new ArrayList<>();
        for (String url : publishedAnnouncement.getProperty().getPhotos().getUrl()) {

            images.add(new Image(url,658,258,false,false));

        }

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);


        photosPagination.setVisible(true);
        photosPagination.setPageCount(publishedAnnouncement.getProperty().getPhotos().getUrl().size());
        photosPagination.setPageFactory(n -> new ImageView(images.get(n)));

        announcementLabel.setVisible(true);



    }

    private void getCriteria(javafx.event.ActionEvent actionEvent) {

        String choiceOption = filterChoice.getValue();


        if (choiceOption.equals("Sort Selection Algorithm")) {
            ascendOrDescendChoice.setVisible(true);
            ascendOrDescendChoice.setOnAction(this::getOrderSelectionSort);
        } else if (choiceOption.equals("Bubble Sort Algorithm")){
            ascendOrDescendChoice.setVisible(true);
            ascendOrDescendChoice.setOnAction(this::getOrderBubbleSort);
        }

    }




    private void getOrderBubbleSort(javafx.event.ActionEvent actionEvent) {

        String choiceOption = ascendOrDescendChoice.getValue();

        if (choiceOption.equals("Descending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByDescendingAreaBubbleSort());
        } else if (choiceOption.equals("Ascending")){
            listDeals.clear();
            listDeals.addAll(controller.getDealsByAscendingAreaBubbleSort());

        }

    }

    private void getOrderSelectionSort(javafx.event.ActionEvent actionEvent) {

        String choiceOption = ascendOrDescendChoice.getValue();

        if (choiceOption.equals("Descending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByDescendingAreaSortSelection());
        } else if (choiceOption.equals("Ascending")){
            listDeals.clear();
            listDeals.addAll(controller.getDealsByAscendingAreaSortSelection());

        }

    }



}
