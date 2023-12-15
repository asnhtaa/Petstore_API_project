package tests.user;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.user.UserSteps.*;

public class UpdateUserTests extends AbstractTest {
    @DisplayName("Update a user")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkUpdatingUser() {
        String body = """
                {
                  "id": 0,
                  "username": "string",
                  "firstName": "string",
                  "lastName": "string",
                  "email": "string",
                  "password": "string",
                  "phone": "string",
                  "userStatus": 0
                }
                """;

        var response = updateUser(spec, body, "string");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
