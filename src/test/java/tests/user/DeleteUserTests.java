package tests.user;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.user.UserSteps.*;

public class DeleteUserTests extends AbstractTest {
    @DisplayName("Delete user")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkDeletingUser() {
        String body = """
                {
                  "id": 0,
                  "username": "mark",
                  "firstName": "string",
                  "lastName": "string",
                  "email": "string",
                  "password": "string",
                  "phone": "string",
                  "userStatus": 0
                }
                """;

        var creatingUser = createUser(spec, body);

        assertThat(creatingUser.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);

        var deletingUser = deleteUser(spec, "mark");

        assertThat(deletingUser.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
