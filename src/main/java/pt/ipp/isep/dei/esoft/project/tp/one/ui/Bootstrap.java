package pt.ipp.isep.dei.esoft.project.tp.one.ui;

import pt.ipp.isep.dei.esoft.project.tp.one.domain.Employee;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.Organization;
import pt.ipp.isep.dei.esoft.project.tp.one.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tp.one.repository.TaskCategoryRepository;

public class Bootstrap {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
    }

    private void addOrganization() {
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("john.doe@this.company.com"));
        organizationRepository.add(organization);

    }

    private void addTaskCategories() {
        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }


}
