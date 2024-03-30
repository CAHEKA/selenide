import org.junit.jupiter.api.Test;
import widgets.*;


public class TestUI extends BaseTest {

    @Test
    public void testCheckbox() {
        new Checkboxes()
                .selectCheckbox("checkbox 1")
                .selectCheckbox("checkbox 2");
    }

    @Test
    public void testDropdown() {
        new Dropdown()
                .selectDropdownByNum("1")
                .selectDropdownByNum("2");
    }

    @Test
    public void testDisappearingElements() {
        new DisappearingElements()
                .waitForNumberOfElementsAfterAttempts(5, 10);
    }

    @Test
    public void testInputs() {
        new Inputs().enterRandomNumber();
    }

    @Test
    public void testHovers() {
        new Hovers()
                .getAllFigure()
                .highlightFigure();
    }

    @Test
    public void testFlashMessages() {
        new NotificationMessage()
                .clickHere()
                .waitForMessageAfterAttempts("Action successful", 10);
    }

    @Test
    public void testAddRemoveElements() {
        new AddRemoveElements()
                .addElements(5)
                .deleteElementFromIndex(4)
                .deleteElementFromIndex(2)
                .deleteElementFromIndex(0);
    }

    @Test
    public void testStatusCodes200() {
        new StatusCodes()
                .clickOnStatusCode("200")
                .checkContainsUrl("200");
    }
    
    @Test
    public void testStatusCodes301() {
        new StatusCodes()
                .clickOnStatusCode("301")
                .checkContainsUrl("301");
    }

    @Test
    public void testStatusCodes404() {
        new StatusCodes()
                .clickOnStatusCode("404")
                .checkContainsUrl("404");
    }

    @Test
    public void testStatusCodes500() {
        new StatusCodes()
                .clickOnStatusCode("500")
                .checkContainsUrl("500");
    }

}
