package pt.ipp.isep.dei.esoft.project.application.session;

import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * The type User session.
 */
public class UserSession {
    /**
     * The UserSession instance representing the user session.
     */
    private pt.isep.lei.esoft.auth.UserSession userSession;

    /**
     * Instantiates a new User session.
     *
     * @param userSession the user session
     */
    public UserSession(pt.isep.lei.esoft.auth.UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * Get user email string.
     *
     * @return the string
     */
    public String getUserEmail(){
        return userSession.getUserId().getEmail();
    }

    /**
     * Get user name string.
     *
     * @return the string
     */
    public String getUserName(){
        return this.userSession.getUserName();
    }

    /**
     * Get user roles list.
     *
     * @return the list
     */
    public List<UserRoleDTO> getUserRoles(){
        return this.userSession.getUserRoles();
    }

    /**
     * Do logout.
     */
    public void doLogout() {
        this.userSession.doLogout();
    }

    /**
     * Is logged in boolean.
     *
     * @return the boolean
     */
    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }

    /**
     * Is logged in with role boolean.
     *
     * @param roleId the role id
     * @return the boolean
     */
    public boolean isLoggedInWithRole(String roleId) {
        return this.userSession.isLoggedInWithRole(roleId);
    }

}
