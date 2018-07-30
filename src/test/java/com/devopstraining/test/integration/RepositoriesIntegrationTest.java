package com.devopstraining.test.integration;

import com.devopstraining.backend.persistence.domain.backend.Plan;
import com.devopstraining.backend.persistence.domain.backend.Role;
import com.devopstraining.backend.persistence.domain.backend.User;
import com.devopstraining.backend.persistence.domain.backend.UserRole;
import com.devopstraining.backend.persistence.repositories.PlanRepository;
import com.devopstraining.backend.persistence.repositories.RoleRepository;
import com.devopstraining.backend.persistence.repositories.UserRepository;
import com.devopstraining.enums.PlansEnum;
import com.devopstraining.enums.RolesEnum;
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

import static com.devopstraining.utils.UsersUtils.createBasicUser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;
    
    @Before
    public void init(){
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(planRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception{

        // Given
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        // When
        Optional<Plan> retrievedPlan = planRepository.findById(PlansEnum.BASIC.getId());
        // Then
        Assert.assertNotNull(retrievedPlan);

    }

    @Test
    public void testCreateNewRole() throws Exception{

        // Given
        Role basicRole = createRole(RolesEnum.BASIC);
        roleRepository.save(basicRole);
        // When
        Optional<Role> retrievedRole = roleRepository.findById(RolesEnum.BASIC.getId());
        // Then
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void createNewUser() throws Exception{

        // Given
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createRole(RolesEnum.BASIC);
        Set<UserRole> userRoles = new HashSet<UserRole>();
        UserRole userRole = new UserRole(basicUser, basicRole);
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
    private Plan createPlan(PlansEnum plansEnum) {
        return new Plan(plansEnum);
    }

    private Role createRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }


}
