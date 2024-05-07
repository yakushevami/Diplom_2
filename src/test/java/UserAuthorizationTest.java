import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientUser;
import user.UserWithoutEmail;
import user.UserWithoutPassword;
import useroperations.CreateAndDeleteUser;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserAuthorizationTest extends CreateAndDeleteUser {
    private final ClientUser clientUser = new ClientUser();

    @Test
    @DisplayName("Authorize valid user test")
    public void authorizeValidUserTest() {
        clientUser.authorizeValidUser(user)
                .then().statusCode(200).body("success", equalTo(true));
    }

    @Test
    @DisplayName("Authorize user without email value test")
    public void authorizeUserWithoutEmailTest() {
        UserWithoutEmail userWithoutEmail = new UserWithoutEmail(user.getPassword(), user.getName());
        clientUser.authorizeUserWithoutEmail(userWithoutEmail)
                .statusCode(401).body("success", equalTo(false));
    }

    @Test
    @DisplayName("Authorize user with incorrect email value test")
    public void authorizeUserWithIncorrectEmailTest() {
        user.setEmail("123");
        clientUser.authorizeValidUser(user)
                .then().statusCode(401).body("success", equalTo(false));
    }

    @Test
    @DisplayName("Authorize user without password value")
    public void authorizeUserWithoutPasswordTest() {
        UserWithoutPassword userWithoutPassword = new UserWithoutPassword(user.getEmail(), user.getName());
        clientUser.authorizeUserWithoutPassword(userWithoutPassword)
                .statusCode(401).body("success", equalTo(false));
    }

    @Test
    @DisplayName("Authorize user with incorrect password value test")
    public void authorizeUserWithIncorrectPasswordTest() {
        user.setPassword("1");
        clientUser.authorizeValidUser(user)
                .then().statusCode(401).body("success", equalTo(false));
    }
}