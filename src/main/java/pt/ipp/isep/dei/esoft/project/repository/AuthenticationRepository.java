package pt.ipp.isep.dei.esoft.project.repository;


import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

public class AuthenticationRepository {
    private final AuthFacade authenticationFacade = new AuthFacade();

    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        authenticationFacade.doLogout();
    }

    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

}
