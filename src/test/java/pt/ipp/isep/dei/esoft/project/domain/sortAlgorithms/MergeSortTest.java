package pt.ipp.isep.dei.esoft.project.domain.sortAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    List<Message> messages = new ArrayList<>();

    // Create message objects with different dates
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
    String description = "Test message";
    Date date = new GregorianCalendar(2023, Calendar.SEPTEMBER, 20).getTime();
    int initialTime = 10;
    int endTime = 11;
    Client client = new Client("client@this.app", 123456789, 123456789, "client", address, 1234567890L);

    Store store = new Store("Store A", 1, address, 5551234, "storea@example.com", 9,1);
    Comission com = new Comission(25.00);
    List<Role> roles = new ArrayList<>();
    Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, address);
    Property property = new Property(2, 2, address);
    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);

    Address addressb = new Address("St", 13426, new District("District"), new City("ity"), new State(" State"));
    String descriptionb = "Test message";
    Date dateb = new GregorianCalendar(2023, Calendar.AUGUST, 20).getTime();
    int initialTimeb = 10;
    int endTimeb = 11;
    Client clientb = new Client("cli@this.app", 123457789, 123457789, "clent", addressb, 1235567890L);

    Comission comB = new Comission(26.00);
    Employee employeeB = new Employee("employee@example.com", 123456789, 987654321, "Employee", 5551294, store, roles, addressb);
    Property propertyB = new Property(2, 2, address);
    Business businessB = new Business(2030);

    Date datec = new GregorianCalendar(2023, Calendar.SEPTEMBER, 28).getTime();


    PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business, employee, client, 77, AnnouncementState.available, store);
    Message message1 = new Message(client.getName(), client.getPhoneNumber(), description, datec, initialTime, endTime, p1, MessageState.ANSWERED, true);
    Message message2 = new Message(client.getName(), client.getPhoneNumber(), description, datec, initialTime, endTimeb, p1, MessageState.ANSWERED, true);
    Message message3 = new Message(client.getName(), client.getPhoneNumber(), description, datec, initialTime, endTime, p1, MessageState.ANSWERED, true);
    Message message4 = new Message(client.getName(), client.getPhoneNumber(), descriptionb, datec, initialTime, endTime, p1, MessageState.ANSWERED, true);
    // Create a sample list of messages for testing

    @BeforeEach
    void setUp() {
        messages = createSampleMessages();
    }

    @Test
    void testMergeArrayElements() {
        MergeSort<Message> mergeSort = new MergeSort<>(messages);

        mergeSort.mergeArrayElements(0, 2, 3);
        List<Message> expected = Arrays.asList(
                message1, message3, message2, message4
        );

        Assertions.assertEquals(expected, messages);
    }

    @Test
    void testDivideArrayElements() {
        MergeSort<Message> mergeSort = new MergeSort<>(messages);

        mergeSort.divideArrayElements(0, messages.size() - 1);
        List<Message> expected = Arrays.asList(
                message1, message3, message2, message4
        );

        Assertions.assertEquals(expected, messages);
    }

    @Test
    void testSingleMessageInList() {
        messages.clear();
        messages.add(message1);
        MergeSort<Message> mergeSort = new MergeSort<>(messages);

        mergeSort.sort(messages);

        Assertions.assertEquals(message1, messages.get(0));
    }

    @Test
    void testEmptyMessageList() {
        messages.clear();
        MergeSort<Message> mergeSort = new MergeSort<>(messages);

        mergeSort.sort(messages);

        Assertions.assertTrue(messages.isEmpty());
    }
    private List<Message> createSampleMessages() {
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        return messages;
    }
}
