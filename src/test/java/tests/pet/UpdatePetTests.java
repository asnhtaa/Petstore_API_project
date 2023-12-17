package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.*;

public class UpdatePetTests extends AbstractTest {

    @DisplayName("Check the functionality of updating a pet in the store with form data")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void updatingPetFormDataTest() {
        var response = updatePetFormData(spec, "2", "cat", "available");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }

    @DisplayName("Check the functionality of updating a pet in the store with form data - negative case (id not found)")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void updatingPetFormDataIdNotFoundTest() {
        var response = updatePetFormData(spec, "1234567897654321", "cat", "available");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(404);
            softly.assertThat(response.jsonPath().getString("message"))
                    .as("Field 'message' is different from expected").isEqualTo("not found");
        });
    }

    @DisplayName("Check the functionality of updating pet in the store")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void updatingPetTest() {
        String body = """
                {
                  "id": 2,
                    "category": {
                      "id": 2,
                      "name": "dogs"
                    },
                    "name": "Charlie",
                    "photoUrls": [
                      "https://wildcard.codestuff.io/dog/250/250"
                    ],
                    "tags": [
                      {
                        "id": 2,
                        "name": "cute"
                      }
                    ],
                    "status": "available"
                }
                """;

        var response = updatePet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("id"))
                    .as("Field 'id' is not found is null").isNotNull();
        });
    }

    @DisplayName("Check the functionality of updating pet in the store - negative case (invalid id)")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void updatingPetInvalidIdTest() {
        String body = """
                {
                  "id": !@#$%,
                    "category": {
                      "id": !@#$%,
                      "name": "dogs"
                    },
                    "name": "Charlie",
                    "photoUrls": [
                      "https://wildcard.codestuff.io/dog/250/250"
                    ],
                    "tags": [
                      {
                        "id": !@#$%,
                        "name": "cute"
                      }
                    ],
                    "status": "available"
                }
                """;

        var response = updatePet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(400);
            softly.assertThat(response.jsonPath().getString("message"))
                    .as("Field 'message' is different from expected").isEqualTo("bad input");
        });
    }
}
