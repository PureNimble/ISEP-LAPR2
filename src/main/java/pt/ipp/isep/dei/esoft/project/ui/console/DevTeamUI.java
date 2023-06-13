package pt.ipp.isep.dei.esoft.project.ui.console;


/**
 * The DevTeamUI class is responsible for displaying the names and emails of the development team.
 * It implements the Runnable interface, allowing it to be run on a separate thread.
 */
public class DevTeamUI implements Runnable {
    /**
     * Constructs a DevTeamUI object.
     */
    public DevTeamUI() {

    }
    /**

     Displays the names and emails of the development team.
     */
    public void run() {
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t Pedro Coelho - 1220688@isep.ipp.pt \n");
        System.out.print("\t Luna Silva - 1221184@isep.ipp.pt \n");
        System.out.print("\t Diogo Moutinho - 1221014@isep.ipp.pt \n");
        System.out.print("\t Vasco Sousa - 1221700@isep.ipp.pt \n");
        System.out.print("\t Rafael Araújo - 1201804@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
