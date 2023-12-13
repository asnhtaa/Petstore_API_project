package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.*;

public class FindPetTests extends AbstractTest {

    @DisplayName("Find pet by id")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkFindingPetId() {

        var response = findPetById(spec, "1");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("name"))
                    .as("Field 'name' is not found").isNotNull();
        });
    }

    @DisplayName("Find pet by id - negative case")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkFindingPetInvalidId() {

        var response = findPetById(spec, "123568993");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(404);
    }

    @DisplayName("Find Pet By Status 'available'")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkFindingPetStatusAvailable() {
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

        var response = findPetByStatus(spec, body);

        assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
    }

    @DisplayName("Find Pet By Status 'pending'")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkFindingPetStatusPending() {
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
                  "status": "pending"
                }
                """;

        var response = findPetByStatus(spec, body);

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }

    @DisplayName("Find Pet By Status 'sold'")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkFindingPetStatusSold() {
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
                  "status": "sold"
                }
                """;

        var response = findPetByStatus(spec, body);

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
