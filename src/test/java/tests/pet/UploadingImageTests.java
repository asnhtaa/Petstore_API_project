package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.pet.PetSteps.uploadImage;

public class UploadingImageTests extends AbstractTest {

    @DisplayName("Check the functionality of uploading pet images")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void uploadingPetImageTest() {
        var response = uploadImage(spec, "2");

        assertThat(response.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);
    }

    @DisplayName("Check the functionality of uploading pet images - negative case (id not found)")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void uploadingPetImageIdNotFoundTest() {
        var response = uploadImage(spec, "1234567654312345432121");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(404);
            softly.assertThat(response.jsonPath().getString("type"))
                    .as("Field 'type' is different from expected").isEqualTo("unknown");
        });
    }
}
