package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type List deals gui teste.
 */
public class ListDealsGUI implements Initializable {


    @FXML
    private Label idNameLabelStore;

    @FXML
    private TableColumn<Offer, Double> orderAmount;

    @FXML
    private Label noPhotos;

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
    private TableColumn<Offer, Client> clientName;

    @FXML
    private TableColumn<Offer, Integer> idOffer;


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
    private Label contractDuration;

    @FXML
    private TableView<Object> table;

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


    /**
     * The List deals.
     */
    ObservableList<Object> listDeals = FXCollections.observableArrayList(
            controller.getOfferMostRecent()
    );


    public void initialize(URL url, ResourceBundle resourceBundle) {


        clientName.setCellValueFactory(new PropertyValueFactory<Offer, Client>("client"));
        clientName.setCellFactory(column -> new TextFieldTableCell<Offer, Client>() {
            @Override
            public void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);
                if (empty || client == null) {
                    setText("");
                } else {
                    setText("" + client.getClientEmail());
                }
            }
        });


        orderAmount.setCellValueFactory(new PropertyValueFactory<Offer, Double>("orderAmount"));
        idOffer.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("offerID"));


        table.setItems(listDeals);

        filterChoice.getItems().addAll(filterCriteria);
        ascendOrDescendChoice.getItems().addAll(filterAscendOrDescend);
        filterChoice.setOnAction(this::getCriteria);

    }


    /**
     * Click item.
     *
     * @param mouseEvent the mouse event
     * @throws FileNotFoundException the file not found exception
     */
    @FXML
    public void clickItem(MouseEvent mouseEvent) throws FileNotFoundException {


        Offer offer;

        for (Object offers : listDeals) {
            if (offers.toString().equals(table.getSelectionModel().getSelectedItem().toString())) {
                offer = (Offer) table.getSelectionModel().getSelectedItem();
                publishedAnnouncement = offer.getPublishedAnnouncement();
            }
        }


        announcementAnchorPane.setVisible(true);

        setGeneralLabel();

        setLabelAndIconsAccordinglyToTypeOfProperty();


        List<Image> images = new ArrayList<>();

        if (publishedAnnouncement.getProperty().getPhotos() != null) {
            for (String url : publishedAnnouncement.getProperty().getPhotos().getUrl()) {

                images.add(new Image(url, 773, 257, false, false));

                noPhotos.setVisible(false);
                photosPagination.setVisible(true);
                photosPagination.setPageCount(publishedAnnouncement.getProperty().getPhotos().getUrl().size());
                photosPagination.setPageFactory(n -> new ImageView(images.get(n)));

            }
        } else {
            photosPagination.setVisible(false);
            noPhotos.setVisible(true);
        }

        announcementLabel.setVisible(true);


    }

    private void getCriteria(javafx.event.ActionEvent actionEvent) {

        String choiceOption = filterChoice.getValue();


        if (choiceOption.equals("Sort Selection Algorithm")) {
            ascendOrDescendChoice.setVisible(true);
            ascendOrDescendChoice.setOnAction(this::getOrderSelectionSort);
        } else if (choiceOption.equals("Bubble Sort Algorithm")) {
            ascendOrDescendChoice.setVisible(true);
            ascendOrDescendChoice.setOnAction(this::getOrderBubbleSort);
        }

    }


    private void getOrderBubbleSort(javafx.event.ActionEvent actionEvent) {

        String choiceOption = ascendOrDescendChoice.getValue();

        if (choiceOption.equals("Descending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByDescendingAreaBubbleSort());
        } else if (choiceOption.equals("Ascending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByAscendingAreaBubbleSort());

        }

    }

    private void getOrderSelectionSort(javafx.event.ActionEvent actionEvent) {

        String choiceOption = ascendOrDescendChoice.getValue();

        if (choiceOption.equals("Descending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByDescendingAreaSortSelection());
        } else if (choiceOption.equals("Ascending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByAscendingAreaSortSelection());

        }

    }

    public void setGeneralLabel() {

        clientDescription.setText("Client Name: " + publishedAnnouncement.getClient().getName() + "      Contacts: " + publishedAnnouncement.getClient().getClientEmail() + ", " + publishedAnnouncement.getClient().getPhoneNumber());
        agentDescription.setText("Responsible Agent Name: " + publishedAnnouncement.getAgent().getName() + "      Contacts: " + publishedAnnouncement.getAgent().getEmail() + ", " + publishedAnnouncement.getAgent().getPhoneNumber());
        priceLabel.setText("" + publishedAnnouncement.getBusiness() + " $");
        labelDistanceCenter.setText("" + publishedAnnouncement.getProperty().getDistanceFromCityCenter());
        adressLabel.setText("" + publishedAnnouncement.getProperty().getAddress());
        typeOfBusinessLabel.setText("" + publishedAnnouncement.getTypeOfBusiness());
        typeOfProperrtyLabel.setText("" + publishedAnnouncement.getPropertyType());
        dateLabel.setText("" + publishedAnnouncement.getDate());
        labelArea.setText("" + publishedAnnouncement.getProperty().getArea());
        idNameLabelStore.setText("Name: " + publishedAnnouncement.getAgent().getStore().getDesignation() + "   ID: " + publishedAnnouncement.getAgent().getStore().getId());

        if (publishedAnnouncement.getTypeOfBusiness().getTypeOfBusiness().equals("Rent") || publishedAnnouncement.getTypeOfBusiness().getTypeOfBusiness().equals("rent")) {
            contractDuration.setVisible(true);
            contractDuration.setText("Contract Duration:             %s" + publishedAnnouncement.getDurationOfContract());
        }

    }

    public void setLabelAndIconsAccordinglyToTypeOfProperty() {
        if (publishedAnnouncement.getProperty() instanceof House) {
            House house = (House) publishedAnnouncement.getProperty();


            labelLoft.setVisible(true);
            loftIcon.setVisible(true);
            labelLoft.setText("" + house.getInhabitableLoft());


            labelBasement.setVisible(true);
            basementICon.setVisible(true);
            labelBasement.setText("" + house.getBasement());

            SunExposureIcon.setVisible(true);
            labelSunExposure.setVisible(true);
            labelSunExposure.setText("" + house.getSunExposure());

            numberBedroomsIcon.setVisible(true);
            labelNumberOfBedrooms.setVisible(true);
            labelNumberOfBedrooms.setText("" + house.getNumberOfBedrooms());

            parkingSpacesIcon.setVisible(true);
            labelparkingSpaces.setVisible(true);
            labelparkingSpaces.setText("" + house.getParkingSpaces());

            numberBathroomsIcon.setVisible(true);
            labelNumberOfBathrooms.setVisible(true);
            labelNumberOfBathrooms.setText("" + house.getNumberOfBathrooms());

        } else if (publishedAnnouncement.getPropertyType().getDesignation().equals("Appartment")) {

            Residence residence = (Residence) publishedAnnouncement.getProperty();

            SunExposureIcon.setVisible(false);
            labelSunExposure.setVisible(false);
            basementICon.setVisible(false);
            labelBasement.setVisible(false);
            labelLoft.setVisible(false);
            loftIcon.setVisible(false);


            numberBedroomsIcon.setVisible(true);
            labelNumberOfBedrooms.setVisible(true);
            labelNumberOfBedrooms.setText("" + residence.getNumberOfBedrooms());

            parkingSpacesIcon.setVisible(true);
            labelparkingSpaces.setVisible(true);
            labelparkingSpaces.setText("" + residence.getParkingSpaces());

            numberBathroomsIcon.setVisible(true);
            labelNumberOfBathrooms.setVisible(true);
            labelNumberOfBathrooms.setText("" + residence.getNumberOfBathrooms());
        } else {
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
    }


}
