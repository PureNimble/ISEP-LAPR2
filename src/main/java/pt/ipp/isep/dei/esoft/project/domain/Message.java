    package pt.ipp.isep.dei.esoft.project.domain;

    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Objects;

    /**
     * The Message class represents a messsage with a description, a name, a phone number, an initial date, initial time, an end date and an end time.
     */
    public class Message {

        /**
         The name of the client.
         */

        private String name;

        /**

         The phone number of the client.
         */

        private long phoneNumber;

        /**

         The description of the message.
         */

        private String description;

        /**

         The initial date for visiting.
         */

        private Date initialDate;

        /**

         The initial time for visiting.
         */

        private int initialTime;

        /**

         The end time for visiting.
         */

        private int endTime;

        private PublishedAnnouncement publishedAnnouncement;

        private MessageState messageState;

        private boolean isApprovedByAgent;


        /**
         * Constructs a new Message object with the specified parameters.
         *
         * @param name                  the client's name associated with the message
         * @param phoneNumber           the client's phone number associated with the message
         * @param description           the description of the message
         * @param initialDate           the initial date for visiting
         * @param initialTime           the initial time for visiting
         * @param endTime               the end time for visiting
         * @param publishedAnnouncement the published announcement
         */
        public Message(String name, long phoneNumber, String description, Date initialDate, int initialTime, int endTime, PublishedAnnouncement publishedAnnouncement, MessageState messageState, boolean isApprovedByAgent) {
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
         * Constructs an empty Message object.
         */
        public Message(){
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

        public MessageState getMessageState() {
            return messageState;
        }

        public void setMessageState(MessageState messageState) {
            this.messageState = messageState;
        }

        public boolean isApprovedByAgent() {
            return isApprovedByAgent;
        }
        public void setApprovedByAgent(boolean approvedByAgent) {
            isApprovedByAgent = approvedByAgent;
        }

        /**
         * Returns a string representation of the Message object.
         *
         * @return a string containing information about the client's visit schedule and description
         */
        @Override
        public String toString() {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String date = format.format(initialDate);
            return String.format("Message: \nThe client %s, with phone number %s, wants to schedule a visit from %s until %s at %s. \n\nDescription: \n%s \n\nProperty: \n%s", name, phoneNumber, initialTime, endTime, date, description, publishedAnnouncement.toString(), messageState);
        }

        /**
         * Checks if the given object is equal to this Message object.
         *
         * @param o The object to compare with.
         * @return True if the object is equal to this Message object, false otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Message)) return false;

            Message message = (Message) o;
            return phoneNumber == message.phoneNumber && initialTime == message.initialTime && endTime == message.endTime && Objects.equals(name, message.name) && Objects.equals(description, message.description) && Objects.equals(initialDate, message.initialDate) && Objects.equals(publishedAnnouncement, message.publishedAnnouncement) && messageState == message.messageState;
        }
        /**
         * Generates the hash code for this Message object.
         *
         * @return The hash code value for this Message object.
         */
        @Override
        public int hashCode() {
            return Objects.hash(name, phoneNumber, description, initialDate, initialTime, endTime, publishedAnnouncement, messageState);
        }
    }
