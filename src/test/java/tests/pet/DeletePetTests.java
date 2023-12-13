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
    @DisplayName("Deletes a pet - positive case")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkDeletingPet() {
        String body = """
                {
                  "id": 0,
                    "category": {
                      "id": 0,
                      "name": "string"
                    },
                    "name": "doggie",
                    "photoUrls": ["string"],
                    "tags": [
                      {
                        "id": 0,
                        "name": "string"
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

    @DisplayName("Delete a pet twice- negative case")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkDeletingPetTwice() {
        String body = """
                {
                  "id": 0,
                    "category": {
                      "id": 0,
                      "name": "string"
                    },
                    "name": "doggie",
                    "photoUrls": ["string"],
                    "tags": [
                      {
                        "id": 0,
                        "name": "string"
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
