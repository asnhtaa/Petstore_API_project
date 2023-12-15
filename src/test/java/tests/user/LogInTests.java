package tests.user;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.user.UserSteps.logIn;

public class LogInTests extends AbstractTest {
    @DisplayName("Logs user into the system")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkLoginUser() {
        var response = logIn(spec, "natasha", "password");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
