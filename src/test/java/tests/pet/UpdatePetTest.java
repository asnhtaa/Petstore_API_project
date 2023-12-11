package tests.pet;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.updatePetFormData;

public class UpdatePetTest extends AbstractTest {

    @DisplayName("PLace an order for a pet")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkOrderingPet() {
        var response = updatePetFormData(spec, "2", "cat", "available");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
