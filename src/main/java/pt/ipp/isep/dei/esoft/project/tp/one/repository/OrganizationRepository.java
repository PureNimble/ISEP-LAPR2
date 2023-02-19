package pt.ipp.isep.dei.esoft.project.tp.one.repository;

import pt.ipp.isep.dei.esoft.project.tp.one.domain.Employee;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationRepository {


    List<Organization> organizationList = new ArrayList<>();

    public Organization getOrganizationByEmployee(Employee employee) {

        Organization returnOrganization = null;

        for (Organization organization : organizationList) {
            if (organization.employs(employee)) {
                returnOrganization = organization;
            }
        }

        if (returnOrganization == null) {
            throw new IllegalArgumentException("Organization requested for [" + employee + "] does not exist.");
        }
        return returnOrganization;
    }

    public Organization getOrganizationByEmail(String email) {

        Organization returnOrganization = null;

        for (Organization organization : organizationList) {
            if (organization.anyEmployeeHasEmail(email)) {
                returnOrganization = organization;
            }
        }

        if (returnOrganization == null) {
            throw new IllegalArgumentException("Organization requested for [" + email + "] does not exist.");
        }
        return returnOrganization;
    }

}
