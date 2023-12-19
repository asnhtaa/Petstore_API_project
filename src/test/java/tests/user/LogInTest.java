package tests.user;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static steps.user.UserSteps.logIn;

public class LogInTest extends AbstractTest {
    @DisplayName("Check the functionality of logging user into the system")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void loginUserTest() {
        var response = logIn(spec, "natasha", "password");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("message"))
                    .as("Message is different from expected").contains("logged in user session");
        });
    }
}
