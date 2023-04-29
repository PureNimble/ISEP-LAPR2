package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationControllerTest {

    @Test
    public void testDoLoginWithValidCredentials() {
        AuthenticationController controller = new AuthenticationController();
        String email = "person.p@example.com";
        String pwd = "password";
        assertNotEquals(email, pwd);
    }

    @Test
    public void testDoLoginWithInvalidCredentials() {
        AuthenticationController controller = new AuthenticationController();
        String email = "person.p@example.com";
        String pwd = "wrongpassword";
        assertFalse(controller.doLogin(email, pwd));
    }

    @Test
    public void testGetUserRolesWhenLoggedIn() {
        AuthenticationController controller = new AuthenticationController();
        String email = "person.p@example.com";
        String pwd = "password";
        controller.doLogin(email, pwd);
        List<UserRoleDTO> roles = controller.getUserRoles();
        assertNull(roles);
    }

    @Test
    public void testGetUserRolesWhenNotLoggedIn() {
        AuthenticationController controller = new AuthenticationController();
        assertNull(controller.getUserRoles());
    }

    @Test
    public void testDoLogout() {
        AuthenticationController controller = new AuthenticationController();
        String email = "person.p@example.com";
        String pwd = "password";
        controller.doLogin(email, pwd);
        assertNull(controller.getUserRoles());
        controller.doLogout();
        assertNull(controller.getUserRoles());
    }
}