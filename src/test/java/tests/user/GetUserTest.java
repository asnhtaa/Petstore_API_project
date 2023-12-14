package tests.user;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static steps.user.UserSteps.getUser;

public class GetUserTest extends AbstractTest {
    @DisplayName("Get user by user name")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void getUserByName() {
        var response = getUser(spec, "string");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("id"))
                    .as("Id is different from expected").isNotNull();
        });
    }

    @DisplayName("Get user by user name")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void getNonexistentUserByName() {
        var response = getUser(spec, "userName");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(404);
            softly.assertThat(response.jsonPath().getString("message"))
                    .as("Message is different from expected").isEqualTo("User not found");
        });
    }
}
