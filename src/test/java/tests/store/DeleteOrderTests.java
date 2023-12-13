package tests.store;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.store.StoreSteps.deletePurchase;
import static steps.store.StoreSteps.placePetOrder;

public class DeleteOrderTests extends AbstractTest {
    @DisplayName("Deletes a pet - positive case")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void checkDeletingOrder() {
        String body = """
                {
                  "id": 0,
                  "petId": 0,
                  "quantity": 0,
                  "shipDate": "2023-12-13T17:28:47.116Z",
                  "status": "placed",
                  "complete": true
                }
                """;

        var placingOrder = placePetOrder(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(placingOrder.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(placingOrder.jsonPath().getString("status"))
                    .as("Field 'status' is different from expected").isEqualTo("placed");
        });

        String orderId = placingOrder.jsonPath().getString("id");

        var deletingOrder = deletePurchase(spec, orderId);

        assertThat(deletingOrder.getStatusCode())
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
                  "petId": 0,
                  "quantity": 0,
                  "shipDate": "2023-12-13T17:28:47.116Z",
                  "status": "placed",
                  "complete": true
                }
                """;

        var placingOrder = placePetOrder(spec, body);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(placingOrder.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(placingOrder.jsonPath().getString("status"))
                    .as("Field 'status' is different from expected").isEqualTo("placed");
        });

        String orderId = placingOrder.jsonPath().getString("id");

        var deletingOrder = deletePurchase(spec, orderId);

        assertThat(deletingOrder.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(200);

        var deletingOrderTwice = deletePurchase(spec, orderId);

        assertThat(deletingOrderTwice.getStatusCode())
                .as("Status-code is different from expected")
                .isEqualTo(404);
    }
}
