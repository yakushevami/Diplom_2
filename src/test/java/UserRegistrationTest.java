import org.junit.After;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import client.ClientUser;
import generator.UserGenerator;
import user.User;
import user.UserWithoutEmail;
import user.UserWithoutName;
import user.UserWithoutPassword;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserRegistrationTest {
    protected User user;
    protected String accessToken;
    private final ClientUser clientUser = new ClientUser();
    private final UserGenerator userGenerator = new UserGenerator();
    public static final String EM_EXISTS = "User already exists";
    public static final String EM_FIELDS_REQUIRED = "Email, password and name are required fields";

    @After
    public void deleteUser() {
        if (user != null) {
            clientUser.deleteUser(user, accessToken);
        }
    }

    @Test
    @DisplayName("Register valid user test")
    public void registerValidUserTest() {
        user = userGenerator.createUser();
        Response response = clientUser.registerValidUser(user);
        accessToken = response.body().path("accessToken");
        response.then().statusCode(200).body("success", equalTo(true));
    }

    @Test
    @DisplayName("Register user that already exists")
    public void registerUserThatAlreadyExistsTest() {
        user = userGenerator.createUser();
        Response response = clientUser.registerValidUser(user);
        accessToken = response.body().path("accessToken");
        response.then().statusCode(200).body("success", equalTo(true));
        Response repeatedResponse = clientUser.registerValidUser(user);
        repeatedResponse.then().statusCode(403).body("success", equalTo(false)).body("message", equalTo(EM_EXISTS));
    }

    @Test
    @DisplayName("Register user without name value test")
    public void registerUserWithoutNameTest() {
        UserWithoutName userWithoutName = userGenerator.createUserWithoutName();
        ValidatableResponse response = clientUser.registerUserWithoutName(userWithoutName);
        response.statusCode(403).body("success", equalTo(false)).body("message", equalTo(EM_FIELDS_REQUIRED));
    }

    @Test
    @DisplayName("Register user without email value test")
    public void registerUserWithoutEmailTest() {
        UserWithoutEmail userWithoutEmail = userGenerator.createUserWithoutEmail();
        ValidatableResponse response = clientUser.registerUserWithoutEmail(userWithoutEmail);
        response.statusCode(403).body("success", equalTo(false)).body("message", equalTo(EM_FIELDS_REQUIRED));
    }

    @Test
    @DisplayName("Register user without password value test")
    public void registerUserWithoutPasswordTest() {
        UserWithoutPassword userWithoutPassword = userGenerator.createUserWithoutPassword();
        ValidatableResponse response = clientUser.registerUserWithoutPassword(userWithoutPassword);
        response.statusCode(403).body("success", equalTo(false)).body("message", equalTo(EM_FIELDS_REQUIRED));
    }
}