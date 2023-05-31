package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ListDealsGUI implements Initializable {

    @FXML
    private TableView<Object> table;


    @FXML
        private TableColumn<Offer, PublishedAnnouncement> area;

        @FXML
        private TableColumn<PublishedAnnouncement, Date> date;

        @FXML
        private TableColumn<PublishedAnnouncement, Integer> durationOfContract;

        @FXML
        private TableColumn<Offer, Double> orderAmount;

        @FXML
        private TableColumn<PublishedAnnouncement, Business> business;

        @FXML
        private TableColumn<PublishedAnnouncement, Comission> comission;

        @FXML
        private TableColumn<PublishedAnnouncement, String> name;

        @FXML
        private TableColumn<Offer, PublishedAnnouncement> publishedAnnouncement;

        @FXML
        private TableColumn<PublishedAnnouncement, TypeOfBusiness> typeOfBusiness;


    private final ListDealsController controller = new ListDealsController();

        ObservableList<Object> listDeals = FXCollections.observableArrayList(
             new Offer("Diogo",12141.142,new PublishedAnnouncement(new Date(),new TypeOfBusiness("Sale"),new Property(5,10),new PropertyType("Land"),new Comission(50),new Business(231421.213)),OfferState.accepted)
        );

    public void initialize(URL url, ResourceBundle resourceBundle){

        area.setCellValueFactory(new PropertyValueFactory<>("area"));

        date.setCellValueFactory(new PropertyValueFactory<PublishedAnnouncement,Date>("date"));
        durationOfContract.setCellValueFactory(new PropertyValueFactory<PublishedAnnouncement,Integer>("durationOfContract"));
        orderAmount.setCellValueFactory(new PropertyValueFactory<Offer,Double>("orderAmount"));
        business.setCellValueFactory(new PropertyValueFactory<PublishedAnnouncement,Business>("business"));
        comission.setCellValueFactory(new PropertyValueFactory<PublishedAnnouncement,Comission>("comission"));
        name.setCellValueFactory(new PropertyValueFactory<PublishedAnnouncement,String>("name"));
        typeOfBusiness.setCellValueFactory(new PropertyValueFactory<PublishedAnnouncement,TypeOfBusiness>("typeOfBusiness"));

        table.setItems(listDeals);
    }


}
