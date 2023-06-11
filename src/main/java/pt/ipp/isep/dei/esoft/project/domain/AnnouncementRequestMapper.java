package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Announcement request mapper.
 */
public class AnnouncementRequestMapper {
    /**
     * To dto list.
     *
     * @param announcementRequestList the announcement request list
     * @return the list
     */
    public List<AnnouncementRequestDto> toDto(List<AnnouncementRequest> announcementRequestList) {

        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();

        for (AnnouncementRequest announcementRequest : announcementRequestList) {
            AnnouncementRequestDto announcementRequestDto;
            Employee agent = announcementRequest.getAgent();
            Property property = announcementRequest.getProperty();
            TypeOfBusiness typeOfBusiness = announcementRequest.getTypeOfBusiness();
            PropertyType propertyType = announcementRequest.getPropertyType();
            Business business = announcementRequest.getBusiness();
            Date date = announcementRequest.getDate();
            String status = announcementRequest.getStatus();
            if (typeOfBusiness.getTypeOfBusiness().equals("Rent")) {
                int durationOfContract = announcementRequest.getDurationOfContract();
                announcementRequestDto = toDtoObject(status, agent, property, typeOfBusiness, propertyType, business, date, durationOfContract);
            } else {
                announcementRequestDto = toDtoObject(status, agent, property, typeOfBusiness, propertyType, business, date, 0);
            }


            announcementRequestDtos.add(announcementRequestDto);


        }

        return announcementRequestDtos;

    }


    /**
     * To dto object announcement request dto.
     *
     * @param status             the status
     * @param agent              the agent
     * @param property           the property
     * @param typeOfBusiness     the type of business
     * @param propertyType       the property type
     * @param business           the business
     * @param date               the date
     * @param durationOfContract the duration of contract
     * @return the announcement request dto
     */
    public AnnouncementRequestDto toDtoObject(String status, Employee agent, Property property, TypeOfBusiness typeOfBusiness, PropertyType propertyType, Business business, Date date, int durationOfContract) {

        AnnouncementRequestDto announcementRequestDto;

        if (typeOfBusiness.getTypeOfBusiness().equals("Rent")) {
            announcementRequestDto = new AnnouncementRequestDto(status, date, typeOfBusiness, property, propertyType, business, durationOfContract, agent);
        } else {
            announcementRequestDto = new AnnouncementRequestDto(status, date, typeOfBusiness, property, propertyType, business, agent);
        }
        return announcementRequestDto;
    }

    /**
     * Gets announcement request dto by description.
     *
     * @param announcementRequestDtos         the announcement request dtos
     * @param annnouncementRequestDescription the annnouncement request description
     * @return the announcement request dto by description
     */
    public AnnouncementRequestDto getAnnouncementRequestDtoByDescription(List<AnnouncementRequestDto> announcementRequestDtos,int annnouncementRequestDescription) {
        AnnouncementRequestDto newAnnouncementRequestDto = announcementRequestDtos.get(annnouncementRequestDescription);
        AnnouncementRequestDto announcementRequestDto = null;
        if (announcementRequestDtos.contains(newAnnouncementRequestDto)) {
            announcementRequestDto = announcementRequestDtos.get(announcementRequestDtos.indexOf(newAnnouncementRequestDto));
        }
        if (announcementRequestDto == null) {
            throw new IllegalArgumentException(
                    "Announcement Request requested for [" + newAnnouncementRequestDto.toString() + "] does not exist.");
        }
        return announcementRequestDto;
    }


}
