package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * The type Store repository.
 */
public class StoreRepository implements Serializable {
    private List<Store> stores = new ArrayList<>();

    /**
     * Gets store by description.
     *
     * @param storeIDDescription the store id description
     * @return the store by description
     */
    public Store getStoreByDescription(String storeIDDescription) {
        Store newStore = new Store();

        for (Store store : stores) {
            if (store.getId() == Integer.parseInt(storeIDDescription)) {
                newStore = store;
            }
        }

        return newStore;
    }

    /**
     * Add optional.
     *
     * @param store the store
     * @return the optional
     */
    public Optional<Store> add(Store store) {
        Optional<Store> newStore = Optional.empty();
        boolean operationSuccess = false;

        if (validateStore(store)) {
            newStore = Optional.of(store);
            operationSuccess = stores.add(newStore.get());
        }

        if (!operationSuccess) {
            newStore = Optional.empty();
        }

        return newStore;
    }

    private boolean validateStore(Store store) {
        boolean isValid = !stores.contains(store);
        return isValid;
    }

    /**
     * Gets stores.
     *
     * @return the stores
     */
    public List<Store> getStores() {
        return stores;
    }

    /**
     * Gets stores property.
     *
     * @param publishedAnnouncements the published announcements
     */
    public void getStoresProperty(List<PublishedAnnouncement> publishedAnnouncements) {

        List<Store> storesList = getStores();
        int counter = 0;
        for (Store stores : storesList) {
            for (PublishedAnnouncement publishedAnnouncement : publishedAnnouncements) {
                if (publishedAnnouncement.getAnnouncementState().equals(AnnouncementState.available)) {
                    if (publishedAnnouncement.getStore().equals(stores)) {
                        counter++;
                    }
                }
            }
            stores.setListing(counter);
            counter = 0;
        }
    }

    /**
     * Gets stores by most listings.
     *
     * @return the stores by most listings
     */
    public List<Store> getStoresByMostListings() {
        List<Store> sortedStores = new ArrayList<>(stores);

        Collections.sort(sortedStores, compareToDescendingList);

        return sortedStores;
    }

    /**
     * The Compare to descending list.
     */
    Comparator<Store> compareToDescendingList  = new Comparator<Store>()  {
        @Override
        public int compare(Store store1, Store store2) {
            int listing1 = store1.getListing();
            int listing2 = store2.getListing();

            if (listing2 < listing1) {
                return -1;
            } else if (listing2 > listing1) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    /**
     * Create store by file reading.
     *
     * @param arrayListStoreInformations the array list store information
     */
    public List<Store> createStoreByFileReading(ArrayList<String[]> arrayListStoreInformations) {

        int aux = 0;
        int auxID=1;
        int id = 0;
        Address address = null;
        String designation="";
        long phoneNumber=0;
        String email="";
        int listing = 0;
        List<Store> storeList = new ArrayList<>();

        for (String[] storeInformations : arrayListStoreInformations) {
            if (aux > 0) {
                listing++;
                id = Integer.parseInt(storeInformations[0]);
                if (auxID != id){
                    Store store = new Store(designation, auxID, address, phoneNumber, email, listing-1);

                    auxID = id;
                    listing = 0;

                    if (!stores.contains(store)){
                        stores.add(store);
                        storeList.add(store);
                    }

                }
                designation = storeInformations[1];
                String[] addressInformations = storeInformations[2].split(",");

                if (addressInformations.length < 5) {
                    String street = addressInformations[0];
                    City city = new City(addressInformations[1]);
                    State state = new State(addressInformations[2]);
                    int zipCode = Integer.parseInt(addressInformations[3]);

                    address = new Address(street, zipCode, city, state);
                } else {
                    String street = addressInformations[0];
                    City city = new City(addressInformations[1]);
                    District district = new District(addressInformations[2]);
                    State state = new State(addressInformations[3]);
                    int zipCode = Integer.parseInt(addressInformations[4]);

                    address = new Address(street, zipCode, district, city, state);
                }
                phoneNumber = Long.parseLong(storeInformations[3].replaceAll("-", ""));
                email = storeInformations[4];

            } else {
                aux = 1;
            }


        }

        Store store = new Store(designation,id,address,phoneNumber,email,listing);

        if (!stores.contains(store)){
            stores.add(store);
            storeList.add(store);
        }

        return storeList;


    }


    public String findPartition(){

        int size = stores.size();

        double totalSubsets = Math.pow(2, size) - 1;

        int sum1 = 0;

        int sum2 = 0;

        int minDifference = 9999;

        int difference;

        String auxBinary = "";


        for (int i = 0; i < totalSubsets; i++) {

            String binary = Integer.toBinaryString(i);

            for (int j = 0; j < size; j++) {

                if (j < binary.length()) {
                    if (Character.toString(binary.charAt(j)).equals("1")) {

                        sum1 = stores.get(j).getListing() + sum1;
                    } else {
                        sum2 = stores.get(j).getListing() + sum2;
                    }
                } else {
                    sum2 = stores.get(j).getListing() + sum2;
                }
            }


            difference = Math.abs(sum1 - sum2);

            if (difference < minDifference) {
                minDifference = difference;
                auxBinary = binary;
            }

            sum2 = 0;
            sum1 = 0;
        }

        generateSubsets(auxBinary,minDifference);


        return auxBinary;
    }



    public List<List<String>> generateSubsets(String auxBinary,int minDifference){

        List<String> l1 = new ArrayList<>();

        List<String> l2 = new ArrayList<>();


        for (int i = 0; i < stores.size(); i++) {


            if (i < auxBinary.length()){
                if (Character.toString(auxBinary.charAt(i)).equals("1")) {
                    l1.add("ID:"+stores.get(i).getId()+" "+stores.get(i).getListing());
                } else {
                    l2.add("ID:"+stores.get(i).getId()+" "+stores.get(i).getListing());
                }
            }else {
                l2.add("ID:"+stores.get(i).getId()+" "+stores.get(i).getListing());
            }


        }

        List<List<String>> result = new ArrayList<>();
        String minDifferenceString = ""+ minDifference;
        List<String> minDifferencelist = new ArrayList<>();
        minDifferencelist.add(minDifferenceString);

        result.add(l1);
        result.add(l2);
        result.add(minDifferencelist);


        return result;
    }


}
