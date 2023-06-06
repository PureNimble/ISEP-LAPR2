package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AnnouncementRequestMapper {
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


    public AnnouncementRequestDto toDtoObject(String status, Employee agent, Property property, TypeOfBusiness typeOfBusiness, PropertyType propertyType, Business business, Date date, int durationOfContract) {

        AnnouncementRequestDto announcementRequestDto;

        if (typeOfBusiness.getTypeOfBusiness().equals("Rent")) {
            announcementRequestDto = new AnnouncementRequestDto(status, date, typeOfBusiness, property, propertyType, business, durationOfContract, agent);
        } else {
            announcementRequestDto = new AnnouncementRequestDto(status, date, typeOfBusiness, property, propertyType, business, agent);
        }
        return announcementRequestDto;
    }

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
