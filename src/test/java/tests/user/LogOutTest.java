package tests.user;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.user.UserSteps.logOut;

public class LogOutTest extends AbstractTest {
    @DisplayName("Check the functionality of logging out user")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void logoutUserTest() {
        var response = logOut(spec);

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
