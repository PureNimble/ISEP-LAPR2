package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.User;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**

 This class represents a repository for authentication-related operations.
 */
public class AuthenticationRepository {

    private final AuthFacade authenticationFacade = new AuthFacade();

    /**

     Performs a login operation using the provided email and password.
     @param email the email address to use for authentication
     @param pwd the password to use for authentication
     @return true if the login was successful, false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }
    /**

     Performs a logout operation.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }
    /**

     Gets the user session for the currently logged-in user.
     @return the user session for the currently logged-in user
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }
    /**

     Adds a new user role with the given ID and description.
     @param id the ID of the new user role
     @param description the description of the new user role
     @return true if the user role was added successfully, false otherwise
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }
    /**

     Adds a new user with the given name, email, password, and role ID.
     @param name the name of the new user
     @param email the email address of the new user
     @param pwd the password of the new user
     @param roleId the ID of the role for the new user
     @return true if the user was added successfully, false otherwise
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }


    public boolean addUserWithRoles(String name, String email, String pwd, String[] roleId) {
        return authenticationFacade.addUserWithRoles(name, email, pwd, roleId);
    }
    /**

     Generates a random password with the specified length, number of capital letters, and number of digits.

     @return a randomly generated password
     */
    public String passwordGenerator(){
        int passwordLength = 7;
        int numCapital = 3;
        int numDigits = 2;

        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < numCapital; i++) {
            password.append(randomCharacter(capitalLetters,random));
        }

        for (int i = 0; i < numDigits; i++) {
            password.append(randomCharacter(digits,random));
        }

        for (int i = 0; i < passwordLength - numCapital - numDigits; i++) {
            password.append(randomCharacter(lowerCaseLetters,random));
        }

        return shuffleString(password.toString(),random);
    }

    /**

     Returns a randomly chosen character from the provided string.
     @param characters the string to choose a character from
     @param random the SecureRandom object to use for generating random numbers
     @return a randomly chosen character from the provided string
     */
    private char randomCharacter(String characters,SecureRandom random) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    /**

     Returns a randomly chosen character from the provided string.
     @param input the string to be shuffled
     @param random the instance of SecureRandom class to generate random numbers
     @return the shuffled string
     */
    private String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }



}
