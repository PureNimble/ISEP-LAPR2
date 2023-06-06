package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.*;

public class MainMenuGUI implements Runnable,Initializable {

    private Scene networkManagerScene;

    private Scene agentScene;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button onBtnLogin;

    private final AuthenticationController ctrl;

    public MainMenuGUI() {
        ctrl = new AuthenticationController();
    }

   public void run(){

   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FXMLLoader networkManagerLoader = new FXMLLoader();
            FXMLLoader agentLoader = new FXMLLoader();
            networkManagerLoader.setLocation(getClass().getResource("/NetworkManagerMenuGUI.fxml"));
            agentLoader.setLocation(getClass().getResource("/AgentMenuGUI.fxml"));
            Parent networkManagerRoot = networkManagerLoader.load();
            Parent agentRoot = agentLoader.load();
            networkManagerScene = new Scene(networkManagerRoot);
            agentScene = new Scene(agentRoot);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<UserRoleDTO> roles = ctrl.getUserRoles();
        List<MenuItem> rolesUI = new ArrayList<>();
        for(UserRoleDTO role : roles){
            if(role.getDescription().equals(AuthenticationController.ROLE_ADMIN)){
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new MainMenuGUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_EMPLOYEE)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_EMPLOYEE, new MainMenuGUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_CLIENT)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_CLIENT, new MainMenuGUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_NETWORK_MANAGER)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_NETWORK_MANAGER, new NetworkManagerMenuGUI()));
            } else if (role.getDescription().equals(AuthenticationController.ROLE_AGENT)) {
                rolesUI.add(new MenuItem(AuthenticationController.ROLE_AGENT, new AgentMenuGUI()));
            }
        }

        return rolesUI;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                if (item.toString().equals(AuthenticationController.ROLE_NETWORK_MANAGER)) {
                    txtEmail.clear();
                    passwordField.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(networkManagerScene);
                    mainStage.setTitle("Store Network Manager Menu");
                    mainStage.show();
                } else if (item.toString().equals(AuthenticationController.ROLE_AGENT)) {
                    txtEmail.clear();
                    passwordField.clear();
                    Stage mainStage = getMainStage();
                    mainStage.setScene(agentScene);
                    mainStage.setTitle("Agent Menu");
                    mainStage.show();
                }
            }
            if (!found) {
                System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
            }
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOneRole(roles,"Select the role you want to adopt in this session:" );
        }
    }

    public Alert createAlert(Alert.AlertType alertType, String title, String content){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(title);
        alert.setContentText(content);

        return alert;
    }


    public void onBtLoginAcntion(javafx.event.ActionEvent actionEvent) {
        boolean success = false;
        Alert alert;
        success = ctrl.doLogin(txtEmail.getText(),passwordField.getText());

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                alert = createAlert(Alert.AlertType.ERROR,"No role","There is no role assigned to the user");
                alert.show();
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    alert = createAlert(Alert.AlertType.ERROR,"Role Selected","There was no role selected");
                    alert.show();
                }
            }
        }else {
            alert = createAlert(Alert.AlertType.ERROR,"Invalid UserID and/or Password","The UserID and/or password typed is not correct");
            alert.show();
        }
        this.logout();
    }

        private Stage getMainStage() {
            return (Stage) this.onBtnLogin.getScene().getWindow();
        }
}
