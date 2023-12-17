package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.addPet;
import static steps.pet.PetSteps.deletePet;

public class DeletePetTests extends AbstractTest {
    @DisplayName("Checks the functionality of deleting a pet")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void deletingPetTest() {
        String body = """
                {
                  "id": 24,
                    "category": {
                      "id": 24,
                      "name": "cats"
                    },
                    "name": "Nick",
                    "photoUrls": ["https://www.freecatphotoapp.com/"],
                    "tags": [
                      {
                        "id": 24,
                        "name": "cat"
                      }
                    ],
                    "status": "available"
                }
                """;

        var creatingPet = addPet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(creatingPet.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(creatingPet.jsonPath().getString("id"))
                    .as("Field 'id' is not found is null").isNotNull();
        });

        String petId = creatingPet.jsonPath().getString("id");

        var deletingPet = deletePet(spec, petId);

        assertThat(deletingPet.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }

    @DisplayName("Checks the functionality of deleting a pet twice- negative case")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void deletingPetTwiceTest() {
        String body = """
                {
                  "id": 31,
                    "category": {
                      "id": 31,
                      "name": "dog"
                    },
                    "name": "Bobby",
                    "photoUrls": ["https://wildcard.codestuff.io/dog/250/250"],
                    "tags": [
                      {
                        "id": 31,
                        "name": "cute"
                      }
                    ],
                    "status": "available"
                }
                """;

        var creatingPet = addPet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(creatingPet.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(creatingPet.jsonPath().getString("id"))
                    .as("Field 'id' is not found is null").isNotNull();
        });

        String petId = creatingPet.jsonPath().getString("id");

        var deletingPet = deletePet(spec, petId);

        assertThat(deletingPet.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);

        var deletingPetTwice = deletePet(spec, petId);

        assertThat(deletingPetTwice.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(404);
    }
}
