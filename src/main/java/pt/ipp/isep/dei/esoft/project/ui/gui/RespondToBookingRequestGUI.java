package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.emailServices.EmailService;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The type Respond to booking request gui.
 */
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

    private static final String FILE_NAME = "EmailNotification.txt";


    /**
     * Sets list message controller.
     *
     * @param listMessageController the list message controller
     */
    public void setListMessageController(ListMessageGUI listMessageController) {
        this.listMessageController = listMessageController;
    }

    /**
     * Sets selected message.
     *
     * @param selectedMessage the selected message
     */
    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;

        // Set the initial values for the fields based on the selected message
        nameField.setText(selectedMessage.getName());
        // ...
    }

    /**
     * Sets published announcement.
     *
     * @param publishedAnnouncement the published announcement
     */
    public void setPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add initialization logic here
        submitButton.setOnAction(event -> {
            if (acceptRadioButton.isSelected() || declineRadioButton.isSelected()) {
                handleSubmitButtonPressed();
            } else {
                // Display an error message or perform any other desired action
                // when neither radio button is selected
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Selection Required");
                alert.setHeaderText(null);
                alert.setContentText("Please select either Accept or Decline.");
                alert.showAndWait();
            }
        });
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
        if (!isValidEmail(email)) {
            // Display an error message or perform any other desired action
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address (e.g., example@example.com).");
            alert.showAndWait();
            return;
        }
        String response = acceptRadioButton.isSelected() ? "Accept" : "Decline";
        String reason = declineRadioButton.isSelected() ? reasonTextArea.getText() : "";

        // Perform any necessary operations with the entered data
        // ...

        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        String emailServiceClass = properties.getProperty("emailService");

        // Instantiate the email service
        EmailService emailService;
        try {
            Class<?> serviceClass = Class.forName(emailServiceClass);
            emailService = (EmailService) serviceClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Failed to instantiate the email service: " + e.getMessage());
            return;
        }

            // Get the agent's email from the properties file
            String agentEmail = properties.getProperty("from");
            String subject;
            String body;

            if (response.equals("Accept")) {
                // Accept the visitation
                subject = "Your Visit Booking Request Has Been Accepted";
                // Construct the body of the email
                body = "Dear Customer, \n\n" +
                        "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                        " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                        "You had requested a visit for the date: " + selectedMessage.getInitialDate() +
                        ", starting at: " + selectedMessage.getInitialTime() +
                        " and ending at: " + selectedMessage.getEndTime() + ".\n\n" +
                        "We are pleased to inform you that your booking request has been accepted. You will be greeted by our agent " + publishedAnnouncement.getAgent().getName() + ".\n" +
                        "In case of any changes or queries, you may contact the agent at the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                        "We look forward to welcoming you for the visit.\n\n" +
                        "Best Regards,\n" +
                        publishedAnnouncement.getAgent().getName();
            } else {
                // Deny the visitation
                subject = "Your Visit Booking Request Has Been Rejected";
                // Construct the body of the email
                body = "Dear Customer, \n\n" +
                        "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                        " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                        "You had requested a visit for the date: " + selectedMessage.getInitialDate() +
                        ", starting at: " + selectedMessage.getInitialTime() +
                        " and ending at: " + selectedMessage.getEndTime() + ".\n\n" +
                        "We regret to inform you that your booking request has been rejected for the following reason:\n\n" +
                        reason + "\n\n" +
                        "If you have any doubts and need help, you may contact the agent " + publishedAnnouncement.getAgent().getName() +
                        " at the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                        "Thank you for your understanding.\n\n" +
                        "Best Regards,\n" +
                        publishedAnnouncement.getAgent().getName();

            }

        emailService.sendEmail(email, subject, body);

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

    /**
     * On bt return.
     *
     * @param actionEvent the action event
     */
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

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9]+[A-Za-z0-9._]*@[A-Za-z0-9]+(\\.[A-Za-z]+[A-Za-z0-9]*)+[A-Za-z]$");
    }
    /**
     * Runs this operation.
     */
    @Override
    public void run() {

    }
}
