package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.adapters.EmailNotificationAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class RespondToBookingRequestGUI implements Runnable,Initializable {
    @FXML
    private Button submitButton;

    @FXML
    private TextField nameField;

    @FXML
    private RadioButton acceptRadioButton;

    @FXML
    private RadioButton declineRadioButton;

    @FXML
    private TextArea reasonTextArea;

    private Message selectedMessage;
    private PublishedAnnouncement publishedAnnouncement;

    private ToggleGroup responseToggleGroup;

    private ListMessageGUI listMessageController;

    @FXML
    private Button btReturn;

    public void setListMessageController(ListMessageGUI listMessageController) {
        this.listMessageController = listMessageController;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;

        // Set the initial values for the fields based on the selected message
        nameField.setText(selectedMessage.getName());
        // ...
    }

    public void setPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add initialization logic here
        submitButton.setOnAction(event -> handleSubmitButtonPressed());
        responseToggleGroup = new ToggleGroup();
        acceptRadioButton.setToggleGroup(responseToggleGroup);
        declineRadioButton.setToggleGroup(responseToggleGroup);

        acceptRadioButton.setOnAction(event -> handleAcceptRadioButtonPressed());
        declineRadioButton.setOnAction(event -> handleDeclineRadioButtonPressed());

    }

    @FXML
    private void handleSubmitButtonPressed() {
        // Handle the submit button action
        String email = nameField.getText();
        String response = acceptRadioButton.isSelected() ? "Accept" : "Decline";
        String reason = declineRadioButton.isSelected() ? reasonTextArea.getText() : "";


        // Perform any necessary operations with the entered data
        // ...

        boolean isValidEmailDomain = EmailNotificationAdapter.isValidEmailDomain(email);
        if (!isValidEmailDomain) {
            // Display error pop-up for invalid email domain
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email Domain");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email domain. Please enter a valid email domain (isep.ipp.pt, gmail.com, hotmail.com, or yahoo.com).");
            alert.showAndWait();
            return;
        }

        if (isValidEmailDomain) {
            String fileName = "EmailNotification - " + email + ".txt";
            Properties properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
                return;
            }

            // Get the agent's email from the properties file
            String agentEmail = properties.getProperty("from");
            String subject;
            String body;

            if (response.equals("Accept")) {
                // Accept the visitation
                subject = "Your Visit Booking Request Has Been Accepted";
                body = "Dear Customer, \n\n" +
                        "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                        " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                        "You had requested a visit for the date: " + selectedMessage.getInitialDate() +
                        " with a start time at: " + selectedMessage.getInitialTime() +
                        " and ending at: " + selectedMessage.getEndTime() + ".\n\n" +
                        "We are pleased to inform you that your booking request has been accepted. You will be greeted by our agent " + publishedAnnouncement.getAgent().getName() + ".\n" +
                        "In case of any changes or queries, you may contact them at the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                        "We look forward to welcoming you for the visit.\n\n" +
                        "Best Regards,\n" +
                        publishedAnnouncement.getAgent().getName();
            } else {
                // Deny the visitation
                subject = "Your Visit Booking Request Has Been Rejected";
                body = "Dear Customer, \n\n" +
                        "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                        " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                        "You had requested a visit for the date: " + selectedMessage.getInitialDate() +
                        " with a start time at: " + selectedMessage.getInitialTime() +
                        " and ending at: " + selectedMessage.getEndTime() + ".\n\n" +
                        "We regret to inform you that your booking request has been rejected for the following reason:\n\n" +
                        reason + "\n\n" +
                        "If you have any doubts and need help, you may contact the agent " + publishedAnnouncement.getAgent().getName() +
                        " with the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                        "Best Regards,\n" +
                        publishedAnnouncement.getAgent().getName();
            }

            try (PrintWriter writer = new PrintWriter(fileName)) {
                writer.println("From: " + agentEmail);
                writer.println("To: " + email);
                writer.println("Subject: " + subject);
                writer.println("Body: " + body);
            } catch (FileNotFoundException e) {
                System.out.println("Failed to write email to file: " + e.getMessage());
            }
        }

        if (listMessageController != null && selectedMessage != null) {
            selectedMessage.setMessageState(MessageState.ANSWERED);
        }


        // Optionally, you can close the current window/stage if needed
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAcceptRadioButtonPressed() {
        // Handle the accept radio button action
        reasonTextArea.setDisable(true);
    }

    @FXML
    private void handleDeclineRadioButtonPressed() {
        // Handle the decline radio button action
        reasonTextArea.setDisable(false);
    }

    @FXML
    public void onBtReturn(ActionEvent actionEvent) {
        Stage stage = getStage();
        FXMLLoader listMessageLoader = new FXMLLoader(getClass().getResource("/ListMessage.fxml")); // Specify the correct file path here
        Parent root = null;
        try {
            root = listMessageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("List Messages Menu");
        stage.show();
    }

    private Stage getStage() {
        return (Stage) btReturn.getScene().getWindow();
    }



    /**
     * Runs this operation.
     */
    @Override
    public void run() {

    }
}
