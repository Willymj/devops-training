package com.devopstraining.test.integration;

import com.devopstraining.backend.persistence.domain.backend.Plan;
import com.devopstraining.backend.persistence.domain.backend.Role;
import com.devopstraining.backend.persistence.domain.backend.User;
import com.devopstraining.backend.persistence.domain.backend.UserRole;
import com.devopstraining.backend.persistence.repositories.PlanRepository;
import com.devopstraining.backend.persistence.repositories.RoleRepository;
import com.devopstraining.backend.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    private static final int BASIC_PLAN_ID = 1;
    private static final int BASIC_ROLE_ID = 1;

    @Before
    public void init(){
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(planRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception{

        // Given
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);
        // When
        Optional<Plan> retrievedPlan = planRepository.findById(BASIC_PLAN_ID);
        // Then
        Assert.assertNotNull(retrievedPlan);

    }

    @Test
    public void testCreateNewRole() throws Exception{

        // Given
        Role basicRole = createBasicRole();
        roleRepository.save(basicRole);
        // When
        Optional<Role> retrievedRole = roleRepository.findById(BASIC_ROLE_ID);
        // Then
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void createNewUser() throws Exception{

        // Given
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createBasicRole();
        Set<UserRole> userRoles = new HashSet<UserRole>();
        UserRole userRole = new UserRole();
        userRole.setRole(basicRole);
        userRole.setUser(basicUser);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        // When
        for(UserRole ur : userRoles){
            roleRepository.save(ur.getRole());
        }

        basicUser = userRepository.save(basicUser);

        // Then
        Optional<User> newlyCreatedUser = userRepository.findById(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        //Assert.assertTrue(newlyCreatedUser.getId() != 0);
        //Assert.assertNotNull(newlyCreatedUser.getPlan());
        //Assert.assertNotNull(newlyCreatedUser.getPlan().getId());

    }

    // --------------> Private methods
    private Plan createBasicPlan() {
        Plan plan = new Plan();
        plan.setId(BASIC_PLAN_ID);
        plan.setName("basic");
        return plan;
    }

    private Role createBasicRole(){
        Role role = new Role();
        role.setId(BASIC_ROLE_ID);
        role.setName("ROLE_USER");
        return role;
    }

    private User createBasicUser(){
        User user = new User();
        user.setUsername("basicUser");
        user.setCountry("France");
        user.setDescription("Descrition");
        user.setEmail("user@me.com");
        user.setFirstname("Firstname");
        user.setLastname("Lastname");
        user.setPassword("secret");
        user.setPhoneNumber("0606060606");
        user.setEnabled(true);
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }

}
