package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.findPetById;

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
                    .as("Field 'name' is not found is null").isNotNull();
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
}
