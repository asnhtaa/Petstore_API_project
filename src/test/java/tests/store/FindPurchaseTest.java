package tests.store;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.AbstractTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static steps.store.StoreSteps.findPurchase;

public class FindPurchaseTest extends AbstractTest{
    @DisplayName("Find purchase order by ID")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void findPurchaseById() {
        var response = findPurchase(spec, "2");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(200);
            softly.assertThat(response.body())
                    .as("Response body is null").isNotNull();
            softly.assertThat(response.jsonPath().getString("id"))
                    .as("Purchase id is different from expected").isEqualTo("2");
        });
    }

    @DisplayName("Find purchase order by ID - negative (invalid id)")
    @Link(name = "Specification", url = "https://petstore.swagger.io/#/")
    @Test
    public void findPurchaseInvalidId() {
        var response = findPurchase(spec, "!@*");

        assertThat(response.getStatusCode())
                    .as("Status-code is different from expected")
                    .isEqualTo(404);
    }
}
