package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.util.List;
import java.util.Comparator;

public class DisplayPropertiesController {

    private void displayProperties(List<PublishedAnnouncement> announcements) {
    }

    private List<PublishedAnnouncement> sortByAscendingCityName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    private List<PublishedAnnouncement> sortByDescendingCityName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    private List<PublishedAnnouncement> sortByAscendingPrice(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    private List<PublishedAnnouncement> sortByDescendingPrice(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    private List<PublishedAnnouncement> sortByAscendingStateName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    private List<PublishedAnnouncement> sortByDescendingStateName(List<PublishedAnnouncement> announcements) {
        return announcements;
    }

    Comparator<PublishedAnnouncement> compareToAscendingCityName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToDescendingCityName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToAscendingPrice = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToDescendingPrice = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToAscendingStateName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToDescendingStateName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };
}
