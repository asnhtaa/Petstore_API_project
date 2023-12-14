package tests.user;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static steps.user.UserSteps.createUserList;

public class CreateUserListTest extends AbstractTest {

    @DisplayName("Creates list of users with given input array")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void createUserListWithArray() {
        String body = """
                [
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
                ]
                   
                """;

        var response = createUserList(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("message"))
                    .as("Message is different from expected").isEqualTo("ok");
        });
    }
}
