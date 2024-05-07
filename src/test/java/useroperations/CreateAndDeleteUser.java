package useroperations;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import client.ClientUser;
import generator.UserGenerator;
import user.User;

public class CreateAndDeleteUser {
    protected final ClientUser clientUser = new ClientUser();
    protected final UserGenerator userGenerator = new UserGenerator();
    protected User user;
    protected String accessToken;
    protected Response responseCreateUser;


    @Before
    public void createUser() {
        user = userGenerator.createUser();
        responseCreateUser = clientUser.registerValidUser(user);
        accessToken = responseCreateUser.body().path("accessToken");
    }

    @After
    public void deleteUser() {
        if (user != null) {
            clientUser.deleteUser(user, accessToken);
        }
    }
}