package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PlaceOfferController;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

public class PlaceOrderUI implements Runnable {

    private final PlaceOfferController controller = new PlaceOfferController();

    @Override
    public void run() {
        System.out.println("Place Order");


    }
}
