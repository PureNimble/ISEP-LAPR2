package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ListDealsGUI implements Initializable {

    @FXML
    private TableView<Object> table;

    @FXML
    private ChoiceBox<String> filterChoice;

    private String[] filterCriteria = {"Descending", "Ascending"};
    @FXML
    private TableColumn<Offer, PublishedAnnouncement> area;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> basement;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> inahnitableLoft;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> sunExposure;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> availableEquipment;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> date;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> parkingSpaces;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> numberOfBedrooms;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> durationOfContract;

    @FXML
    private TableColumn<Offer, Double> orderAmount;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> business;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> comission;

    @FXML
    private TableColumn<Offer, String> name;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> publishedAnnouncement;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> typeOfBusiness;

    @FXML
    private TableColumn<Offer, PublishedAnnouncement> numberOfBathrooms;


    private final ListDealsController controller = new ListDealsController();


    ObservableList<Object> listDeals = FXCollections.observableArrayList(
         controller.getOfferMostRecent()
    );

    public void initialize(URL url, ResourceBundle resourceBundle) {

        filterChoice.getItems().addAll(filterCriteria);
        filterChoice.setOnAction(this::getCriteria);


        area.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        area.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText("");
                } else {
                    setText("" + publishedAnnouncement.getProperty().getArea());
                }
            }

        });


        date.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        date.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText("");
                } else {
                    setText("" + publishedAnnouncement.getDate());
                }
            }

        });


        durationOfContract.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        durationOfContract.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText("");
                } else {
                    setText("" + publishedAnnouncement.getDurationOfContract());
                }
            }

        });

        orderAmount.setCellValueFactory(new PropertyValueFactory<Offer, Double>("orderAmount"));

        business.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        business.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText("");
                } else {
                    setText("" + publishedAnnouncement.getBusiness());
                }
            }

        });


        comission.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        comission.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText("");
                } else {
                    setText("" + publishedAnnouncement.getComission());
                }
            }

        });
        name.setCellValueFactory(new PropertyValueFactory<Offer, String>("name"));

        typeOfBusiness.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        typeOfBusiness.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {
            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText("");
                } else {
                    setText("" + publishedAnnouncement.getTypeOfBusiness());
                }
            }

        });

        basement.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        basement.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);

                if (empty || publishedAnnouncement == null) {
                    setText(" ");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        setText("" + house.getBasement());
                    } else {
                        setText(" ");
                    }
                }
            }
        });


        sunExposure.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        sunExposure.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty || publishedAnnouncement == null) {
                    setText(" ");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        String sunExposure = house.getSunExposure();
                        setText("" + sunExposure);
                    } else {
                        setText(" ");
                    }
                }
            }
        });

        inahnitableLoft.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        inahnitableLoft.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);

                if (empty) {
                    setText("");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        String inhabitableLoft = house.getInhabitableLoft();
                        setText("" + inhabitableLoft);
                    } else {
                        setText(" ");
                    }
                }
            }
        });

        availableEquipment.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        availableEquipment.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);

                if (empty) {
                    setText("");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        AvailableEquipment availableEquipment = house.getAvailableEquipment();

                        setText("" + availableEquipment);
                    } else {
                        if (publishedAnnouncement.getProperty() instanceof Residence) {
                            Residence residence = ((Residence) publishedAnnouncement.getProperty());
                            AvailableEquipment availableEquipment = residence.getAvailableEquipment();
                            setText("" + availableEquipment);
                        } else {
                            setText(" ");
                        }
                    }
                }
            }
        });


        numberOfBedrooms.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        numberOfBedrooms.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty) {
                    setText("");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        int numberOfBedrooms = house.getNumberOfBedrooms();
                        setText("" + numberOfBedrooms);
                    } else {
                        if (publishedAnnouncement.getProperty() instanceof Residence) {
                            Residence residence = ((Residence) publishedAnnouncement.getProperty());
                            int numberOfBedrooms = residence.getNumberOfBedrooms();
                            setText("" + numberOfBedrooms);
                        } else {
                            setText(" ");
                        }
                    }
                }


            }
        });

        numberOfBathrooms.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        numberOfBathrooms.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);
                if (empty) {
                    setText("");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        int numberOfBathrooms = house.getNumberOfBathrooms();
                        setText("" + numberOfBathrooms);
                    } else {
                        if (publishedAnnouncement.getProperty() instanceof Residence) {
                            Residence residence = ((Residence) publishedAnnouncement.getProperty());
                            int numberOfBathrooms = residence.getNumberOfBathrooms();

                            setText("" + numberOfBathrooms);
                        } else {
                            setText(" ");
                        }

                    }
                }
            }
        });


        parkingSpaces.setCellValueFactory(new PropertyValueFactory<>("publishedAnnouncement"));
        parkingSpaces.setCellFactory(column -> new TextFieldTableCell<Offer, PublishedAnnouncement>() {

            @Override
            public void updateItem(PublishedAnnouncement publishedAnnouncement, boolean empty) {
                super.updateItem(publishedAnnouncement, empty);

                if (empty) {
                    setText("");
                } else {
                    if (publishedAnnouncement.getProperty() instanceof House) {
                        House house = ((House) publishedAnnouncement.getProperty());
                        int parkingSpaces = house.getParkingSpaces();
                        setText("" + parkingSpaces);
                    } else {
                        if (publishedAnnouncement.getProperty() instanceof Residence) {
                            Residence residence = ((Residence) publishedAnnouncement.getProperty());
                            int parkingSpaces = residence.getParkingSpaces();
                            setText("" + parkingSpaces);
                        } else {
                            setText(" ");
                        }

                    }
                }
            }
        });


        table.setItems(listDeals);
    }

    private void getCriteria(javafx.event.ActionEvent actionEvent) {

        String choiceOption = filterChoice.getValue();


        if (choiceOption.equals("Descending")) {
            listDeals.clear();
            listDeals.addAll(controller.getDealsByAscendingArea());
        } else if (choiceOption.equals("Ascending")){
            listDeals.clear();
            listDeals.addAll(controller.getDealsByDescendinggArea());
        }

    }


}
