package com.devopstraining.test.integration;

import com.devopstraining.backend.persistence.domain.backend.Role;
import com.devopstraining.backend.persistence.domain.backend.User;
import com.devopstraining.backend.persistence.domain.backend.UserRole;
import com.devopstraining.backend.service.UserService;
import com.devopstraining.enums.PlansEnum;
import com.devopstraining.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static com.devopstraining.utils.UsersUtils.createBasicUser;


@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateNewUser() throws Exception {

        // Given
        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = createBasicUser();
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        // When
        User user = userService.createUser(basicUser, PlansEnum.BASIC, userRoles);

        // Then
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());



    }
}
