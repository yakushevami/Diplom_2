import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import client.ClientUser;
import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import useroperations.CreateAndDeleteUser;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class UserUpdateTest extends CreateAndDeleteUser {
    private final ClientUser clientUser = new ClientUser();
    private final String email;
    private final String password;
    private final String name;
    private final String updatedFields; // for tests naming
    public static final String INI = "initial_data";
    public static final String EM_AUTHORIZED = "You should be authorised";

    public UserUpdateTest(String name, String email, String password, String updatedFields) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.updatedFields = updatedFields;
    }

    private static String getUserName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    private static String getUserEmail() {
        return RandomStringUtils.randomAlphanumeric(7) + "@yandex.ru";
    }

    private static String getUserPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    private Map<String, String> newDataMap(String email, String password, String name) {
        Map<String, String> updateData = new HashMap<>();
        if (email.contains(INI)) {
            updateData.put("email", user.getEmail());
        } else if (!email.contains("null")) {
            updateData.put("email", email);
        }
        if (password.contains(INI)) {
            updateData.put("password", user.getPassword());
        } else if (!password.contains("null")) {
            updateData.put("password", password);
        }
        if (name.contains(INI)) {
            updateData.put("name", user.getName());
        } else if (!name.contains("null")) {
            updateData.put("name", name);
        }
        return updateData;
    }

    @Parameterized.Parameters(name = "update in {3}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {getUserName(), INI, INI, "name"},
                {INI, getUserEmail(), INI, "email"},
                {INI, INI, getUserPassword(), "password"},
                {getUserName(), getUserEmail(), INI, "name + email"},
                {getUserName(), INI, getUserPassword(), "name + password"},
                {INI, getUserEmail(), getUserPassword(), "email + password"},
                {getUserName(), getUserEmail(), getUserPassword(), "all fields"},
        };
    }

    @Test
    @DisplayName("Update authorized user test")
    public void updateAuthorizedUserTest() {
        Map<String, String> updateData = newDataMap(email, password, name);
        Response response = clientUser.updateAuthorizedUser(updateData, accessToken);
        response.then().statusCode(200).body("success", equalTo(true));
    }

    @Test
    @DisplayName("Update unauthorized user test")
    public void updateUnauthorizedUserTest() {
        Map<String, String> updateData = newDataMap(email, password, name);
        Response response = clientUser.updateUnauthorizedUser(updateData);
        response.then().statusCode(401).body("success", equalTo(false)).body("message", equalTo(EM_AUTHORIZED));
    }
}