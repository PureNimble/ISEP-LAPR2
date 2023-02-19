package pt.ipp.isep.dei.esoft.project.tp.one.repository;

import pt.ipp.isep.dei.esoft.project.tp.one.domain.Employee;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Organization;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Task;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;

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


}
