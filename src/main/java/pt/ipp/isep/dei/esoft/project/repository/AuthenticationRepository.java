package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.User;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationRepository {
    private final AuthFacade authenticationFacade = new AuthFacade();

    List<User> users = new ArrayList<>();

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


    private char randomCharacter(String characters,SecureRandom random) {

        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    private String shuffleString(String input,SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public List<User> getUsers() {
        return users;
    }

}
