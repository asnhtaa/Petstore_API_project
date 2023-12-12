package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.*;

public class UpdatePetTest extends AbstractTest {

    @DisplayName("Updates a pet in the store with form data")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkUpdatingPetFormData() {
        var response = updatePetFormData(spec, "2", "cat", "available");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }

    @DisplayName("Updates pet in the store")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkUpdatingPet() {
        String body = """
                {
                  "id": 0,
                    "category": {
                      "id": 0,
                      "name": "string"
                    },
                    "name": "doggie",
                    "photoUrls": [
                      "string"
                    ],
                    "tags": [
                      {
                        "id": 0,
                        "name": "string"
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
}
