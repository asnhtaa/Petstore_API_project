package tests.pet;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.uploadImage;

public class UploadingImageTests extends AbstractTest {

    @DisplayName("Uploads an image for a pet")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkUploadingPetImage() {
        var response = uploadImage(spec, "2");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }
}
