package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.*;

public class FindPetTests extends AbstractTest {

    @DisplayName("Check the functionality of finding pet by id")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void gettingPetByIdTest() {
        String body = """
                {
                  "id": 2,
                  "category": {
                    "id": 2,
                    "name": "kittens"
                  },
                  "name": "Fluffy",
                  "photoUrls": ["https://www.freecatphotoapp.com/"],
                  "tags": [
                    {
                      "id": 2,
                      "name": "cute"
                    }
                  ],
                  "status": "available"
                }
                """;

        var addingPet = addPet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(addingPet.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(addingPet.jsonPath().getString("id"))
                    .as("Field 'id' is null").isNotNull();
        });

        String petId = addingPet.jsonPath().getString("id");

        var response = findPetById(spec, petId);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("id"))
                    .as("Field 'id' is not found").isNotNull();
        });
    }

    @DisplayName("Check the functionality of finding pet by id - negative case (id not found)")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void gettingPetIdNotFoundTest() {

        var response = findPetById(spec, "123568993");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(404);
    }

    @DisplayName("Check the functionality of finding pet by status 'available'")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void findingPetStatusAvailableTest() {
        String body = """
                {
                "id": 45,
                  "category": {
                    "id": 45,
                    "name": "cats"
                  },
                  "name": "Katie",
                  "photoUrls": [
                    "https://www.freecatphotoapp.com/"
                  ],
                  "tags": [
                    {
                      "id": 45,
                      "name": "cats"
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

    @DisplayName("Check the functionality of finding pet by status 'pending'")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void findingPetStatusPendingTest() {
        String body = """
                {
                "id": 22,
                  "category": {
                    "id": 22,
                    "name": "cats"
                  },
                  "name": "Milo",
                  "photoUrls": [
                    "https://www.freecatphotoapp.com/"
                  ],
                  "tags": [
                    {
                      "id": 22,
                      "name": "cute"
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

    @DisplayName("Check the functionality of finding pet by status 'sold'")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void findingPetStatusSoldTest() {
        String body = """
                {
                "id": 89,
                  "category": {
                    "id": 89,
                    "name": "cats"
                  },
                  "name": "Simba",
                  "photoUrls": [
                    "https://www.freecatphotoapp.com/"
                  ],
                  "tags": [
                    {
                      "id": 89,
                      "name": "cutie"
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
