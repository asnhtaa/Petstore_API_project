package tests.pet;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static steps.pet.PetSteps.addPet;

public class AddingPetTests extends AbstractTest {

    @DisplayName("Check the functionality of adding new pet to the store")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void addingNewPetTest() {
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

        var response = addPet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("id"))
                    .as("Field 'id' is null").isNotNull();
        });
    }

    @DisplayName("Check the functionality of adding new pet to the store - negative case (invalid id)")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void addingNewPetInvalidIdTest() {
        String body = """
                {
                  "id": !@#@%!^&,
                  "category": {
                    "id": !@#@%!^&,
                    "name": "kittens"
                  },
                  "name": "Emma",
                  "photoUrls": ["https://www.freecatphotoapp.com/"],
                  "tags": [
                    {
                      "id": !@#@%!^&,
                      "name": "cute"
                    }
                  ],
                  "status": "available"
                }
                """;

        var response = addPet(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(400);
            softly.assertThat(response.jsonPath().getString("message"))
                    .as("Field 'message' is different from expected").isEqualTo("bad input");
        });
    }

}
