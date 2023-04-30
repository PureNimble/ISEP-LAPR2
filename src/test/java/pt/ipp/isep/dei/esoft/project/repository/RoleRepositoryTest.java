package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {

    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        roleRepository = new RoleRepository();
    }

    @Test
    void getTaskCategoryByDescription() {
        Role role1 = new Role("Test Role 1");
        Role role2 = new Role("Test Role 2");

        roleRepository.add(role1);
        roleRepository.add(role2);

        Role retrievedRole = roleRepository.getRoleByDescription("Test Role 2");

        Assertions.assertEquals(retrievedRole, role2);
    }

    @Test
    void getRolesByDescription() {
        Role role1 = new Role("Test Role 1");
        Role role2 = new Role("Test Role 2");

        roleRepository.add(role1);
        roleRepository.add(role2);

        List<String> roleDescriptions = new ArrayList<>();
        roleDescriptions.add("Test Role 1");
        roleDescriptions.add("Test Role 2");

//        List<Role> retrievedRoles = roleRepository.getRolesByDescription(roleDescriptions);

//        Assertions.assertEquals(retrievedRoles.size(), 2);
//        Assertions.assertTrue(retrievedRoles.contains(role1));
//        Assertions.assertTrue(retrievedRoles.contains(role2));
    }

    @Test
    void add() {
        Role role = new Role("Test Role");
        Optional<Role> addedRole = roleRepository.add(role);

        Assertions.assertTrue(addedRole.isPresent());
        Assertions.assertEquals(addedRole.get(), role);
        Assertions.assertEquals(roleRepository.getRoles().size(), 1);
        Assertions.assertEquals(roleRepository.getRoles().get(0), role);
    }

    @Test
    void getRoles() {
        // create some test data
        Role role1 = new Role("Administrator");
        Role role2 = new Role("Manager");
        Role role3 = new Role("Employee");

        // add the test data to the repository
        RoleRepository roleRepository = new RoleRepository();
        roleRepository.add(role1);
        roleRepository.add(role2);
        roleRepository.add(role3);

        // get a copy of the roles list and check that it matches the test data
        List<Role> roles = roleRepository.getRoles();
        assertEquals(3, roles.size());
        assertTrue(roles.contains(role1));
        assertTrue(roles.contains(role2));
        assertTrue(roles.contains(role3));
    }
}