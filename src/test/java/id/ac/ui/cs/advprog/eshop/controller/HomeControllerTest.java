package id.ac.ui.cs.advprog.eshop.controller;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest { // Or whatever your controller class is named

    @Test
    void landingPage_shouldReturnLandingPageView() {
        // Arrange
        HomeController controller = new HomeController(); // Instantiate your controller

        // Act
        String viewName = controller.landingPage();

        // Assert
        assertEquals("LandingPage", viewName); // Check the returned view name
    }
}