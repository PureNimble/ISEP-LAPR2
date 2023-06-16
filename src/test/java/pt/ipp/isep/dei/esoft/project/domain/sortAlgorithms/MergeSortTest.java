package pt.ipp.isep.dei.esoft.project.domain.sortAlgorithms;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

public class MergeSortTest {
    public static void main(String[] args) {
        // Test empty list
        List<Message> emptyList = new ArrayList<>();
        testSort(emptyList);

        // Test list with a single element
        List<Message> singleElementList = new ArrayList<>();
        singleElementList.add(createMessage("Test", new Date()));
        testSort(singleElementList);

        // Test sorted list
        List<Message> sortedList = createSortedMessages();
        testSort(sortedList);

        // Test list with elements in descending order
        List<Message> descendingList = createDescendingMessages();
        testSort(descendingList);
    }

    private static void testSort(List<Message> messages) {
        Comparator<Message> messageComparator = Comparator.comparing(Message::getInitialDate);
        MergeSort<Message> mergeSort = new MergeSort<>(messageComparator);

        List<Message> sortedMessages = mergeSort.sort(new ArrayList<>(messages));

        // Check if the list is sorted in ascending order
        for (int i = 0; i < sortedMessages.size() - 1; i++) {
            Message current = sortedMessages.get(i);
            Message next = sortedMessages.get(i + 1);
            if (messageComparator.compare(current, next) > 0) {
                System.out.println("Sorting failed: elements are not in ascending order");
                return;
            }
        }

        System.out.println("Sorting successful: " + sortedMessages.size() + " elements sorted");
    }

    private static Message createMessage(String description, Date date) {
        // Create a sample message
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        int initialTime = 10;
        int endTime = 11;
        Client client = new Client("client@this.app", 123456789, 123456789, "client", address, 1234567890L);
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com", 9, 1);
        Comission com = new Comission(25.00);
        List<Role> roles = new ArrayList<>();
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, address);
        Property property = new Property(2, 2, address);
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Business business = new Business(200);
        PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business, employee, client, 77, AnnouncementState.available, store);
        return new Message(client.getName(), client.getPhoneNumber(), description, date, initialTime, endTime, p1, MessageState.ANSWERED, true);
    }

    private static List<Message> createSortedMessages() {
        List<Message> messages = new ArrayList<>();
        Date currentDate = new Date();
        for (int i = 0; i < 100; i++) {
            Date date = new GregorianCalendar(2023, Calendar.SEPTEMBER, i + 1).getTime();
            messages.add(createMessage("Test " + (i + 1), date));
        }
        return messages;
    }

    private static List<Message> createDescendingMessages() {
        List<Message> messages = new ArrayList<>();
        Date currentDate = new Date();
        for (int i = 99; i >= 0; i--) {
            Date date = new GregorianCalendar(2023, Calendar.SEPTEMBER, i + 1).getTime();
            messages.add(createMessage("Test " + (i + 1), date));
        }
        return messages;
    }
}
