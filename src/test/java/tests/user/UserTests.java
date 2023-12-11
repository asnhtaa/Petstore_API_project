package tests.user;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static steps.user.UserSteps.createUserList;

public class UserTests extends AbstractTest {

    @DisplayName("PLace an order for a pet")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkFeaturesList() {
        String body = """
                {
                  "id": 0,
                  "username": "nataliaKim",
                  "firstName": "Natalia",
                  "lastName": "Kim",
                  "email": "nlkim@gmail.com",
                  "password": "natasha12",
                  "phone": "81235673481",
                  "userStatus": 0
                }
                """;


//        String body = """
//                {
//                  "id": 0,
//                  "username": "string",
//                  "firstName": "string",
//                  "lastName": "string",
//                  "email": "string",
//                  "password": "string",
//                  "phone": "string",
//                  "userStatus": 0
//                }
//                """;

        var response = createUserList(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("status"))
                    .as("Order status is different from expected").isEqualTo("placed");
        });
    }
}
