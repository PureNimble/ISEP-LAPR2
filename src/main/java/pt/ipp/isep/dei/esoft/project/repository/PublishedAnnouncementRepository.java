package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A repository for storing and managing PublishedAnnouncement objects.
 */
public class PublishedAnnouncementRepository implements Serializable {
    /**
     * A list of published announcements.
     */
    private List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

    /**
     * Adds a new published announcement to the repository.
     *
     * @param publishedAnnouncement the published announcement to add.
     * @return an Optional containing the newly added published announcement if the operation was successful,         otherwise an empty Optional.
     */
    public Optional<PublishedAnnouncement> add(PublishedAnnouncement publishedAnnouncement) {

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();
        boolean operationSuccess = false;

        if (validate(publishedAnnouncement)) {
            newPublishedAnnouncement = Optional.of(publishedAnnouncement);
            operationSuccess = publishedAnnouncements.add(newPublishedAnnouncement.get());
        }

        if (!operationSuccess) {
            newPublishedAnnouncement = Optional.empty();
        }

        return newPublishedAnnouncement;
    }

    private boolean validate(PublishedAnnouncement publishedAnnouncement) {
        boolean isValid = !publishedAnnouncements.contains(publishedAnnouncement);
        return isValid;
    }

    /**
     * Checks if a published announcement is valid and adds it to the repository if it is.
     *
     * @param date               the date of the published announcement.
     * @param typeOfBusiness     the type of business of the published announcement.
     * @param property           the property of the published announcement.
     * @param propertyType       the property type of the published announcement.
     * @param comission          the commission of the published announcement.
     * @param business           the business of the published announcement.
     * @param durationOfContract the duration of the contract of the published announcement.
     * @param agent              the agent
     * @param client             the client
     * @param propertyID         the property id
     * @param state              the state
     * @param store              the store
     * @return an Optional containing the newly added published announcement if the operation was successful,         otherwise an empty Optional.
     */
    public Optional<PublishedAnnouncement> publishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract, Employee agent, Client client, int propertyID, AnnouncementState state, Store store) {


        Optional<PublishedAnnouncement> optionalValue = Optional.empty();

        PublishedAnnouncement publishedAnnouncement;

        if (typeOfBusiness.toString().equals("Rent")) {
            publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract, agent, client, state, store);
        } else {
            publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, agent, client, state, store);
        }

        if (addPublishedAnnouncement(publishedAnnouncement)) {
            optionalValue = Optional.of(publishedAnnouncement);

        }
        return optionalValue;
    }

    /**
     * Published announcement request optional.
     *
     * @param announcementRequests   the announcement requests
     * @param announcementRequestDto the announcement request dto
     * @param comission              the comission
     * @param store                  the store
     * @return the optional
     */
    public Optional<PublishedAnnouncement> publishedAnnouncementRequest(List<AnnouncementRequest> announcementRequests, AnnouncementRequestDto announcementRequestDto, Comission comission, Store store, AnnouncementState announcementState) {


        Optional<PublishedAnnouncement> optionalValue = Optional.empty();

        PublishedAnnouncement publishedAnnouncement;

        publishedAnnouncement = new PublishedAnnouncement(announcementRequestDto, comission, store, announcementState);

        AnnouncementRequest announcementRequest = new AnnouncementRequest(announcementRequestDto);

        int i = 0;


        for (AnnouncementRequest announcementRequest1 : announcementRequests) {
            if (announcementRequest1.equals(announcementRequest)) {
                announcementRequests.get(i).setStatus("true");
            }
            i++;
        }

        if (addPublishedAnnouncement(publishedAnnouncement)) {
            optionalValue = Optional.of(publishedAnnouncement);

        }
        return optionalValue;
    }

    /**
     * Adds a new published announcement to the repository.
     *
     * @param publishedAnnouncement the published announcement to add.
     * @return true if the published announcement was added to the repository, false otherwise.
     */
    private boolean addPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        boolean success = false;
        PublishAnnouncementController controller = new PublishAnnouncementController();
        List<PublishedAnnouncement> publishedAnnouncements = controller.getPublishedAnnoucement();
        if (validatePublishedAnnouncement(publishedAnnouncement)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = publishedAnnouncements.add(publishedAnnouncement);
        }
        return success;

    }

    /**
     * This method validates a published announcement, checking if it is not already present in the repository.
     *
     * @param publishedAnnouncement the published announcement to be validated
     * @return true if the published announcement is valid, false otherwise
     */
    private boolean validatePublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        return !tasksDoNotContainAnnouncement(publishedAnnouncement);
    }

    /**
     * This method checks if a PublishedAnnouncement object is present in the list of published announcements
     *
     * @param publishAnnouncement the PublishedAnnouncement object to check for in the list
     * @return true if the object is not present in the list, false otherwise
     */
    private boolean tasksDoNotContainAnnouncement(PublishedAnnouncement publishAnnouncement) {
        return getPublishedAnnouncements().contains(publishAnnouncement);
    }


    /**
     * This method returns a defensive (immutable) copy of the list of published announcements.
     *
     * @return The list of published announcements.
     */
    public List<PublishedAnnouncement> getPublishedAnnouncements() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return publishedAnnouncements;
    }

    /**
     * Gets published announcements desc.
     *
     * @return the published announcements desc
     */
    public List<PublishedAnnouncement> getPublishedAnnouncementsDesc() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        Collections.sort(publishedAnnouncements, Comparator.comparing(PublishedAnnouncement::getDate).reversed());
        return publishedAnnouncements;
    }

    /**
     * Gets available published announcements desc.
     *
     * @return the available published announcements desc
     */
    public List<PublishedAnnouncement> getAvailablePublishedAnnouncementsDesc() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();
        List<PublishedAnnouncement> tempList = new ArrayList<PublishedAnnouncement>();
        for (PublishedAnnouncement publishedAnnouncement : publishedAnnouncements) {
            if (publishedAnnouncement.getAnnouncementState().equals(AnnouncementState.available)) {
                tempList.add(publishedAnnouncement);
            }
        }
        tempList.sort(Comparator.comparing(PublishedAnnouncement::getDate).reversed());
        resultList.addAll(tempList);

        return resultList;
    }


    /**
     * Create publish announcement by file reading.
     *
     * @param arrayListOwnerInformations the array list owner informations
     */
    public List<PublishedAnnouncement> createPublishAnnouncementByFileReading(ArrayList<String[]> arrayListOwnerInformations, List<Store> stores, List<Client> clients) {

        int aux = 0;
        String propertyType;
        int area;
        Address propertyLocation;
        int distanceFromCityCenter;
        int numberOfBedrooms = 0;
        int numberOfBathrooms = 0;
        int parkingSpaces = 0;
        String centralHeating = "";
        String airConditionated = "";
        String basement = "";
        String loft = "";
        String sunExposure = "";
        double price;
        double comission;
        int contractDuration;
        Date date = null;
        int id = 0;
        String typeOfBusiness;
        int i = 0;
        int auxID = 0;
        Client client;
        Store store ;
        List<PublishedAnnouncement> publishedAnnouncementsList = new ArrayList<>();

        for (String[] ownerInformations : arrayListOwnerInformations) {

            if (aux != 0) {

                propertyType = ownerInformations[6];
                area = Integer.parseInt(ownerInformations[7]);
                String[] adressInformations = ownerInformations[8].split(",");
                propertyLocation = createAddress(adressInformations);
                distanceFromCityCenter = Integer.parseInt(ownerInformations[9]);
                if (propertyType.equals("house") || propertyType.equals("apartment")) {
                    numberOfBedrooms = Integer.parseInt(ownerInformations[10]);
                    numberOfBathrooms = Integer.parseInt(ownerInformations[11]);
                    parkingSpaces = Integer.parseInt(ownerInformations[12]);
                    centralHeating = ownerInformations[13];
                    if (propertyType.equals("house")) {
                        basement = ownerInformations[15];
                        loft = ownerInformations[16];
                        sunExposure = ownerInformations[17];
                    }
                }
                price = Double.parseDouble(ownerInformations[18]);
                comission = Double.parseDouble(ownerInformations[20]);
                if (!ownerInformations[21].equals("NA")) {
                    contractDuration = Integer.parseInt(ownerInformations[21]);
                } else {
                    contractDuration = 0;
                }

                String dateString = ownerInformations[23].replaceAll("/", "-");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    date = dateFormat.parse(dateString);
                } catch (Exception e) {

                }


                typeOfBusiness = ownerInformations[24];

                id = Integer.parseInt(ownerInformations[25]);

                client = clients.get(id-1);
                store = stores.get(id-1);

                TypeOfBusiness typeOfBusinessA = new TypeOfBusiness(typeOfBusiness);
                AvailableEquipment availableEquipment = new AvailableEquipment(centralHeating);
                PropertyType propertyTypeA = new PropertyType(propertyType);
                Business business = new Business(price);
                Comission comissionA = new Comission(comission);

                PublishedAnnouncement publishedAnnouncement;
                List<Role> roles = new ArrayList<>();
                roles.add(new Role("Agent"));
                String nameAgent = store.getDesignation()+" Agent"+id;
                String email = store.getDesignation()+"agent"+"@"+"realstateUS.com";

                long phoneNumber = (long) (1000000000+Math.random()*9999999999L);

                Employee agent = new Employee(email, 000000000, 000000000, nameAgent, phoneNumber, store, roles);
                AnnouncementState state = AnnouncementState.sold;

                if (propertyType.equals("house")) {
                    House house = new House(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, basement, loft, sunExposure, propertyLocation);

                    if (typeOfBusiness.equals("sale")) {
                        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusinessA, house, propertyTypeA, comissionA, business, agent, client, state, store);
                    } else {
                        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusinessA, house, propertyTypeA, comissionA, business, contractDuration, agent, client, state, store);
                    }


                } else if (propertyType.equals("land")) {

                    Property land = new Property(distanceFromCityCenter, area, propertyLocation);

                    if (typeOfBusiness.equals("sale")) {

                        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusinessA, land, propertyTypeA, comissionA, business, agent, client, state, store);

                    } else {

                        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusinessA, land, propertyTypeA, comissionA, business, contractDuration, agent, client, state, store);
                    }

                } else {

                    Residence appartment = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, propertyLocation);

                    if (typeOfBusiness.equals("sale")) {

                        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusinessA, appartment, propertyTypeA, comissionA, business, agent, client, state, store);

                    } else {

                        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusinessA, appartment, propertyTypeA, comissionA, business, contractDuration, agent, client, state, store);

                    }

                }


                if (!publishedAnnouncements.contains(publishedAnnouncement)) {
                    publishedAnnouncements.add(publishedAnnouncement);
                    publishedAnnouncementsList.add(publishedAnnouncement);
                }

            } else {
                aux = 1;
            }
        }

        return publishedAnnouncementsList;

    }




    /**
     * Create address address.
     *
     * @param addressInformations the address informations
     * @return the address
     */
    public Address createAddress(String[] addressInformations) {

        Address address = null;

        if (addressInformations.length == 4) {
            String street = addressInformations[0];
            City city = new City(addressInformations[1]);
            State state = new State(addressInformations[2]);
            int zipCode = Integer.parseInt(addressInformations[3]);

            address = new Address(street, zipCode, city, state);
        } else if (addressInformations.length == 5) {

            String street = addressInformations[0];
            City city = new City(addressInformations[1]);
            District district = new District(addressInformations[2]);
            State state = new State(addressInformations[3]);
            int zipCode = Integer.parseInt(addressInformations[4]);

            address = new Address(street, zipCode, district, city, state);
        } else if (addressInformations.length == 7) {

            String street = null;
            for (int i = 0; i < 4; i++) {

                street = addressInformations[i] + " ";
            }
            City city = new City(addressInformations[4]);
            State state = new State(addressInformations[5]);
            int zipCode = Integer.parseInt(addressInformations[6]);
            address = new Address(street, zipCode, city, state);
        } else if (addressInformations.length == 8) {

            String street = null;
            for (int i = 0; i < 4; i++) {
                street = addressInformations[i] + " ";
            }
            City city = new City(addressInformations[4]);
            District district = new District(addressInformations[5]);
            State state = new State(addressInformations[6]);
            int zipCode = Integer.parseInt(addressInformations[7]);
            address = new Address(street, zipCode, city, state);


            address = new Address(street, zipCode, district, city, state);
        }

        return address;
    }

    /**
     * Filter list list.
     *
     * @param propertyType           the property type
     * @param businessType           the business type
     * @param numberOfRooms          the number of rooms
     * @param publishedAnnouncements the published announcements
     * @return the list
     */
    public List<PublishedAnnouncement> filterList(String propertyType, String businessType, int numberOfRooms, List<PublishedAnnouncement> publishedAnnouncements) {
        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement publishedAnnouncement : publishedAnnouncements) {
            List<PublishedAnnouncement> tempList = new ArrayList<PublishedAnnouncement>();
            if (publishedAnnouncement.getAnnouncementState().equals(AnnouncementState.available)) {
                if (publishedAnnouncement.getPropertyType().getDesignation().equalsIgnoreCase(propertyType)) {
                    if (publishedAnnouncement.getTypeOfBusiness().getTypeOfBusiness().equalsIgnoreCase(businessType)) {
                        if (propertyType != "Land") {
                            if (publishedAnnouncement.getProperty().getNumberOfBedrooms() == numberOfRooms) {
                                tempList.add(publishedAnnouncement);
                            }
                        } else tempList.add(publishedAnnouncement);
                    }
                }
                resultList.addAll(tempList);
            }
        }
        return resultList;
    }

    /**
     * Compare ascending price list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> compareAscendingPrice(List<PublishedAnnouncement> announcements) {
        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement publishedAnnouncement : announcements) {
            resultList.add(publishedAnnouncement);
        }

        resultList.sort(new Comparator<PublishedAnnouncement>() {
            @Override
            public int compare(PublishedAnnouncement p1, PublishedAnnouncement p2) {
                double price1 = p1.getBusiness().getPrice();

                double price2 = p2.getBusiness().getPrice();

                if (price1 < price2) {
                    return -1;
                } else if (price1 > price2) {
                    return 1;
                } else {
                    return 0;
                }

            }
        });
        return resultList;
    }

    public void changeAnnouncementState(PublishedAnnouncement publishedAnnouncement) {
        publishedAnnouncement.setAnnouncementState(AnnouncementState.sold);
    }

    /**
     * Compare ascending city list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> compareAscendingCity(List<PublishedAnnouncement> announcements) {

        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();
        List<PublishedAnnouncement> tempList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement announcement : announcements) {
            tempList.add(announcement);
        }
        tempList.sort(Comparator.comparing(PublishedAnnouncement::getCity));
        resultList.addAll(tempList);

        return resultList;
    }

    /**
     * Compare ascending state list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> compareAscendingState(List<PublishedAnnouncement> announcements) {

        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();
        List<PublishedAnnouncement> tempList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement announcement : announcements) {
            tempList.add(announcement);
        }
        tempList.sort(Comparator.comparing(PublishedAnnouncement::getState));
        resultList.addAll(tempList);

        return resultList;
    }

    /**
     * Compare descending price list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> compareDescendingPrice(List<PublishedAnnouncement> announcements) {
        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement publishedAnnouncement : announcements) {
            resultList.add(publishedAnnouncement);
        }

        resultList.sort(new Comparator<PublishedAnnouncement>() {
            @Override
            public int compare(PublishedAnnouncement p1, PublishedAnnouncement p2) {
                double price1 = p1.getBusiness().getPrice();

                double price2 = p2.getBusiness().getPrice();

                if (price1 > price2) {
                    return -1;
                } else if (price1 < price2) {
                    return 1;
                } else {
                    return 0;
                }

            }
        });

        return resultList;
    }

    /**
     * Compare descending city list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> compareDescendingCity(List<PublishedAnnouncement> announcements) {

        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();
        List<PublishedAnnouncement> tempList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement announcement : announcements) {
            tempList.add(announcement);
        }
        tempList.sort(Comparator.comparing(PublishedAnnouncement::getCity).reversed());
        resultList.addAll(tempList);

        return resultList;
    }

    /**
     * Compare descending state list.
     *
     * @param announcements the announcements
     * @return the list
     */
    public List<PublishedAnnouncement> compareDescendingState(List<PublishedAnnouncement> announcements) {

        List<PublishedAnnouncement> resultList = new ArrayList<PublishedAnnouncement>();
        List<PublishedAnnouncement> tempList = new ArrayList<PublishedAnnouncement>();

        for (PublishedAnnouncement announcement : announcements) {
            tempList.add(announcement);
        }
        tempList.sort(Comparator.comparing(PublishedAnnouncement::getState).reversed());
        resultList.addAll(tempList);

        return resultList;
    }

    public ArrayList<Double> getArea(){
        ArrayList<Double> parameterList = new ArrayList<>();
        for (PublishedAnnouncement announcement : publishedAnnouncements) {
            if (!announcement.getPropertyType().getDesignation().equalsIgnoreCase("Land") && announcement.getAnnouncementState().toString().equals("SOLD")) {
                parameterList.add(Double.valueOf(announcement.getProperty().getArea()));
            }
        }
        return parameterList;
    }

    public ArrayList<Double> getDistanceFromCityCenter(){
        ArrayList<Double> parameterList = new ArrayList<>();
        for (PublishedAnnouncement announcement : publishedAnnouncements) {
            if (!announcement.getPropertyType().getDesignation().equalsIgnoreCase("Land") && announcement.getAnnouncementState().toString().equals("SOLD")) {
                parameterList.add(Double.valueOf(announcement.getProperty().getDistanceFromCityCenter()));
            }
        }
        return parameterList;
    }

    public ArrayList<Double> getNumberOfBedrooms(){
        ArrayList<Double> parameterList = new ArrayList<>();
        for (PublishedAnnouncement announcement : publishedAnnouncements) {
            if (!announcement.getPropertyType().getDesignation().equalsIgnoreCase("Land") && announcement.getAnnouncementState().toString().equals("SOLD")) {
                parameterList.add(Double.valueOf(announcement.getProperty().getResidence().getNumberOfBedrooms()));
            }
        }
        return parameterList;
    }

    public ArrayList<Double> getNumberOfBathrooms(){
        ArrayList<Double> parameterList = new ArrayList<>();
        for (PublishedAnnouncement announcement : publishedAnnouncements) {
            if (!announcement.getPropertyType().getDesignation().equalsIgnoreCase("Land") && announcement.getAnnouncementState().toString().equals("SOLD")) {
                parameterList.add(Double.valueOf(announcement.getProperty().getResidence().getNumberOfBathrooms()));
            }
        }
        return parameterList;
    }

    public ArrayList<Double> getParkingSpaces(){
        ArrayList<Double> parameterList = new ArrayList<>();
        for (PublishedAnnouncement announcement : publishedAnnouncements) {
            if (!announcement.getPropertyType().getDesignation().equalsIgnoreCase("Land") && announcement.getAnnouncementState().toString().equals("SOLD")) {
                parameterList.add(Double.valueOf(announcement.getProperty().getResidence().getParkingSpaces()));
            }
        }
        return parameterList;
    }

    public ArrayList<Double> getPrice(){
        ArrayList<Double> parameterList = new ArrayList<>();
        for (PublishedAnnouncement announcement : publishedAnnouncements) {
            if (!announcement.getPropertyType().getDesignation().equalsIgnoreCase("Land") && announcement.getAnnouncementState().toString().equals("SOLD")) {
                parameterList.add(announcement.getBusiness().getPrice());
            }
        }
        return parameterList;
    }


    public double[][] getParameterMatrix(int param) {
        int size = (param == -1) ? 6 : 2;
        ArrayList<ArrayList<Double>> tempParameterList = new ArrayList<>();

        switch (param){
            case -1:
                tempParameterList.add(getArea());
                tempParameterList.add(getDistanceFromCityCenter());
                tempParameterList.add(getNumberOfBedrooms());
                tempParameterList.add(getNumberOfBathrooms());
                tempParameterList.add(getParkingSpaces());
                break;
            case 1:
                tempParameterList.add(getArea());
                break;
            case 2:
                tempParameterList.add(getDistanceFromCityCenter());
                break;
            case 3:
                tempParameterList.add(getNumberOfBedrooms());
                break;
            case 4:
                tempParameterList.add(getNumberOfBathrooms());
                break;
            case 5:
                tempParameterList.add(getParkingSpaces());
                break;
        }
        tempParameterList.add(getPrice());
        double[][] parameterMatrix = new double[size][tempParameterList.get(0).size()];

        for (int i = 0; i < tempParameterList.size(); i++){
            ArrayList<Double> tempList = tempParameterList.get(i);
            for (int j = 0; j < tempList.size(); j++){
                parameterMatrix[i][j] = tempList.get(j);
            }
        }

        return parameterMatrix;
    }


}
