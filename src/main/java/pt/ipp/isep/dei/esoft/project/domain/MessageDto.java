package pt.ipp.isep.dei.esoft.project.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Message dto.
 */
public class MessageDto {

    private String name;
    private long phoneNumber;
    private String description;
    private Date initialDate;
    private int initialTime;
    private int endTime;
    private PublishedAnnouncement publishedAnnouncement;

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
     */
    public MessageDto(String name, long phoneNumber, String description, Date initialDate, int initialTime, int endTime, PublishedAnnouncement publishedAnnouncement) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.initialDate = initialDate;
        this.initialTime = initialTime;
        this.endTime = endTime;
        this.publishedAnnouncement = publishedAnnouncement;
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

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(initialDate);
        return String.format("Message: \nThe client %s, with phone number %s, wants to schedule a visit from %s until %s at %s. \n\nDescription: \n%s \n\nProperty: \n%s", name, phoneNumber, initialTime, endTime, date, description, publishedAnnouncement.toString());
    }


}
