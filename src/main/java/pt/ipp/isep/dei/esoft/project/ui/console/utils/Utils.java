package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Utils.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {

    /**
     * Read line from console string.
     *
     * @param prompt the prompt
     * @return the string
     */
    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read integer from console int.
     *
     * @param prompt the prompt
     * @return the int
     */
    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Read double from console double.
     *
     * @param prompt the prompt
     * @return the double
     */
    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Read date from console date.
     *
     * @param prompt the prompt
     * @return the date
     */
    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Confirm boolean.
     *
     * @param message the message
     * @return the boolean
     */
    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    /**
     * Show and select one object.
     *
     * @param list   the list
     * @param header the header
     * @return the object
     */
    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    /**
     * Show and select one role object.
     *
     * @param list   the list
     * @param header the header
     * @return the object
     */
    static public Object showAndSelectOneRole(List list, String header) {
        showListRoleSession(list, header);
        return selectsObject(list);
    }


    /**
     * Show and select index int.
     *
     * @param list   the list
     * @param header the header
     * @return the int
     */
    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    /**
     * Show list.
     *
     * @param list   the list
     * @param header the header
     */
    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }


    /**
     * Show list role session.
     *
     * @param list   the list
     * @param header the header
     */
    static public void showListRoleSession(List<UserRoleDTO> list, String header) {
        System.out.println(header);

        int index = 0;
        for (UserRoleDTO o : list) {
            index++;

            System.out.println(index + ". " +o.getDescription() );
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }


    /**
     * Selects object object.
     *
     * @param list the list
     * @return the object
     */
    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    /**
     * Selects index int.
     *
     * @param list the list
     * @return the int
     */
    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }
}
