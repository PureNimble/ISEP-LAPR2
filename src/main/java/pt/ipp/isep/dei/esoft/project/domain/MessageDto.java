package pt.ipp.isep.dei.esoft.project.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Message dto.
 */
public class MessageDto {
    /**
     * The name of the client.
     */
    private String name;
    /**
     * The phone number of the client.
     */
    private long phoneNumber;
    /**
     * The description of the message.
     */
    private String description;
    /**
     * The initial date of the message.
     */
    private Date initialDate;
    /**
     * The initial time of the message.
     */
    private int initialTime;
    /**
     * The end time of the message.
     */
    private int endTime;
    /**
     * The published announcement associated with the message.
     */
    private PublishedAnnouncement publishedAnnouncement;

    private MessageState messageState;

    private boolean isApprovedByAgent;

    /**
     * Instantiates a new Message dto.
     *
     * @param name                  the name
     * @param phoneNumber           the phone number
     * @param description           the description
     * @param initialDate           the initial date
     * @param initialTime           the initial time
     * @param endTime               the end time
     * @param publishedAnnouncement the published announcement
     * @param messageState          the message state
     * @param isApprovedByAgent     the is approved by agent
     */
    public MessageDto(String name, long phoneNumber, String description, Date initialDate, int initialTime, int endTime, PublishedAnnouncement publishedAnnouncement, MessageState messageState, boolean isApprovedByAgent) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.initialDate = initialDate;
        this.initialTime = initialTime;
        this.endTime = endTime;
        this.publishedAnnouncement = publishedAnnouncement;
        this.messageState = messageState;
        this.isApprovedByAgent = isApprovedByAgent;
    }

    /**
     * Instantiates a new Message dto.
     */
    public MessageDto(){
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets initial date.
     *
     * @return the initial date
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * Sets initial date.
     *
     * @param initialDate the initial date
     */
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * Gets initial time.
     *
     * @return the initial time
     */
    public int getInitialTime() {
        return initialTime;
    }

    /**
     * Sets initial time.
     *
     * @param initialTime the initial time
     */
    public void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets published announcement.
     *
     * @return the published announcement
     */
    public PublishedAnnouncement getPublishedAnnouncement() {
        return publishedAnnouncement;
    }

    /**
     * Sets published announcement.
     *
     * @param publishedAnnouncement the published announcement
     */
    public void setPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }

    /**
     * Gets message state.
     *
     * @return the message state
     */
    public MessageState getMessageState() {
        return messageState;
    }

    /**
     * Sets message state.
     *
     * @param messageState the message state
     */
    public void setMessageState(MessageState messageState) {
        this.messageState = messageState;
    }

    /**
     * Is approved by agent boolean.
     *
     * @return the boolean
     */
    public boolean isApprovedByAgent() {
        return isApprovedByAgent;
    }

    /**
     * Sets approved by agent.
     *
     * @param approvedByAgent the approved by agent
     */
    public void setApprovedByAgent(boolean approvedByAgent) {
        isApprovedByAgent = approvedByAgent;
    }

    /**
     * Returns a string representation of the Message object.
     *
     * @return A string representation of the Message object.
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(initialDate);
        return String.format("Message: \nThe client %s, with phone number %s, wants to schedule a visit from %s until %s at %s. \n\nDescription: \n%s \n\nProperty: \n%s", name, phoneNumber, initialTime, endTime, date, description, publishedAnnouncement.toString(), messageState);
    }


}
