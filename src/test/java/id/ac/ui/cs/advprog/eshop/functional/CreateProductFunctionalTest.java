package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest { // Make the class public

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() { // Make setupTest public
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_andVerifyInList(ChromeDriver driver) { // Make the test method public
        // 1. Navigate to the product creation page
        driver.get(baseUrl + "/product/create");

        // 2. Wait for elements to be present (important for dynamic content)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed

        WebElement productNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nameInput")));
        WebElement productQuantityInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("quantityInput")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));


        // 3. Fill in the product details
        String productName = "Test Product Functional"; // More descriptive name
        String productQuantity = "25"; // Example quantity

        productNameInput.sendKeys(productName);
        productQuantityInput.sendKeys(productQuantity);

        // 4. Submit the form
        submitButton.click();

        // 5. Navigate to the product list page
        driver.get(baseUrl + "/product/list");

        // 6. Wait for the product to appear in the list (important!)
        WebElement productNameInList = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '" + productName + "')]")));
        WebElement productQuantityInList = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '" + productQuantity + "')]")));


        // 7. Verify the new product is in the list
        assertEquals(productName, productNameInList.getText(), "Product name should match");
        assertEquals(productQuantity, productQuantityInList.getText(), "Product quantity should match");
    }
}

