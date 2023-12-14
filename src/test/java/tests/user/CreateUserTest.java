package tests.user;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.user.UserSteps.createUser;

public class CreateUserTest extends AbstractTest {
    @DisplayName("Creates user")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkCreatingUser() {
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

        var response = createUser(spec, body);

        assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
    }
}
