package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ListDealsGUI implements Initializable {

    @FXML
    private TableView<Object> table;


    @FXML
        private TableColumn<Offer, PublishedAnnouncement> area;

        @FXML
        private TableColumn<Offer, PublishedAnnouncement> date;

        @FXML
        private TableColumn<Offer, PublishedAnnouncement> durationOfContract;

        @FXML
        private TableColumn<Offer, Double> orderAmount;

        @FXML
        private TableColumn<Offer,PublishedAnnouncement> business;

        @FXML
        private TableColumn<Offer, PublishedAnnouncement> comission;

        @FXML
        private TableColumn<Offer, String> name;

        @FXML
        private TableColumn<Offer, PublishedAnnouncement> publishedAnnouncement;

        @FXML
        private TableColumn<Offer, PublishedAnnouncement> typeOfBusiness;


         private final ListDealsController controller = new ListDealsController();




        ObservableList<Object> listDeals = FXCollections.observableArrayList(
          controller.getDealsByAscendingArea()
        );

    public void initialize(URL url, ResourceBundle resourceBundle){


        area.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        area.setCellFactory(column -> new TextFieldTableCell<Offer,PublishedAnnouncement>(){
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement,boolean empty){
                super.updateItem(publishedAnnouncement,empty);
                if (empty || publishedAnnouncement == null){
                    setText("");
                }else {
                    setText(""+publishedAnnouncement.getProperty().getArea());
                }
            }

        });


        date.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        date.setCellFactory(column -> new TextFieldTableCell<Offer,PublishedAnnouncement>(){
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement,boolean empty){
                super.updateItem(publishedAnnouncement,empty);
                if (empty || publishedAnnouncement == null){
                    setText("");
                }else {
                    setText(""+publishedAnnouncement.getDate());
                }
            }

        });



        durationOfContract.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        durationOfContract.setCellFactory(column -> new TextFieldTableCell<Offer,PublishedAnnouncement>(){
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement,boolean empty){
                super.updateItem(publishedAnnouncement,empty);
                if (empty || publishedAnnouncement == null){
                    setText("");
                }else {
                    setText(""+publishedAnnouncement.getDurationOfContract());
                }
            }

        });

        orderAmount.setCellValueFactory(new PropertyValueFactory<Offer,Double>("orderAmount"));

        business.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        business.setCellFactory(column -> new TextFieldTableCell<Offer,PublishedAnnouncement>(){
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement,boolean empty){
                super.updateItem(publishedAnnouncement,empty);
                if (empty || publishedAnnouncement == null){
                    setText("");
                }else {
                    setText(""+publishedAnnouncement.getBusiness());
                }
            }

        });


        comission.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        comission.setCellFactory(column -> new TextFieldTableCell<Offer,PublishedAnnouncement>(){
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement,boolean empty){
                super.updateItem(publishedAnnouncement,empty);
                if (empty || publishedAnnouncement == null){
                    setText("");
                }else {
                    setText(""+publishedAnnouncement.getComission());
                }
            }

        });
        name.setCellValueFactory(new PropertyValueFactory<Offer,String>("name"));

        typeOfBusiness.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        typeOfBusiness.setCellFactory(column -> new TextFieldTableCell<Offer,PublishedAnnouncement>(){
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement,boolean empty){
                super.updateItem(publishedAnnouncement,empty);
                if (empty || publishedAnnouncement == null){
                    setText("");
                }else {
                    setText(""+publishedAnnouncement.getTypeOfBusiness());
                }
            }

        });

        table.setItems(listDeals);
    }


}
