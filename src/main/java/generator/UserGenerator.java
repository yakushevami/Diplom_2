package generator;

import org.apache.commons.lang3.RandomStringUtils;
import user.User;
import user.UserWithoutEmail;
import user.UserWithoutName;
import user.UserWithoutPassword;

public class UserGenerator {
    private String getUserName() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    private String getUserEmail() {
        return RandomStringUtils.randomAlphanumeric(7) + "@yandex.ru";
    }

    private String getUserPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public User createUser() {
        return new User(
                getUserEmail(),
                getUserPassword(),
                getUserName());
    }

    public UserWithoutName createUserWithoutName() {
        return new UserWithoutName(
                getUserEmail(),
                getUserPassword());
    }

    public UserWithoutPassword createUserWithoutPassword() {
        return new UserWithoutPassword(
                getUserEmail(),
                getUserName());
    }

    public UserWithoutEmail createUserWithoutEmail() {
        return new UserWithoutEmail(
                getUserName(),
                getUserPassword());
    }
}