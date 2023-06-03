package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.util.InputMismatchException;
import java.util.Scanner;

/**

 The PlaceOrderUI class represents the user interface for placing an order.

 It implements the Runnable interface to support concurrent execution.
 */
public class DisplayPropertiesUI implements Runnable {

    private final Scanner input = new Scanner(System.in);
    private final DisplayPropertiesController controller = new DisplayPropertiesController();
    /**

     Executes the logic for placing an order.
     */
    @Override
    public void run() {
        System.out.println("\nList of Properties (Sorted By most recent): ");

        var recentProp = controller.getPublishedAnnouncementsDesc();

        for (int i = 0; i < recentProp.size(); i++){
            PublishedAnnouncement publishedAnnouncement = recentProp.get(i);
            System.out.println(i+1 + ". " + publishedAnnouncement.toString());
        }

        if(recentProp.isEmpty()){
            System.out.println("\nThere are no properties");
        } else{
            System.out.println("\nDo you wish to filter the list?");
            if (acceptOrDecline() == true) {
                filterCriteria();
            } 
            System.out.println("\nSorting Options: ");
            sortingCriteria();
        }

    }

    private boolean acceptOrDecline(){
        System.out.println("\n1. Yes");
        System.out.println("2. No");
        System.out.println("0. Cancel");

        double choice;

        do {

            try {
                choice = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double value:");
                input.nextLine();
                choice = -1;
            }

            if (choice > 2){
                choice = -1;
            }
            else if (choice == 1){
                return true;
                
            }
            else if (choice == 2){
                return false;
            }
            else if (choice == 0){
                break;
            }

        } while (choice < 0);
        return true;
    }

    private void filterCriteria(){
        System.out.println("\nType of bussiness: ");
        System.out.println("\nType of property: ");
        System.out.println("\nNumber of rooms: ");
    }

    private void sortingCriteria(){
        System.out.println("\n1. Price");
        System.out.println("2. City");
        System.out.println("3. State");
        System.out.println("0. Cancel");

        double choice;

        do {

            try {
                choice = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double value:");
                input.nextLine();
                choice = -1;
            }

            if (choice > 3){
                choice = -1;
            }
            else if (choice == 1){
                break;
            }
            else if (choice == 2){
                break;
            }
            else if (choice == 3){
                break;
            }
            else if (choice == 0){
                break;
            }

        } while (choice < 0);
    }

}