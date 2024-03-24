import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import widgets.*;

import static org.junit.jupiter.api.Assertions.fail;

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
                .waitForElementsAfterAttempts(5, 10);
    }

    @Test
    public void testInputs() {
        new Inputs().enterRandomNumber();
    }

    @Test
    public void testHovers() {
        Hovers hoversPage = new Hovers();

        for (SelenideElement figure : hoversPage.getAllFigure()) {
            hoversPage.highlightFigure(figure);
        }
    }

    @Test
    public void testFlashMessages() {
        FlashMessages messages = new FlashMessages();
        NotificationMessage notification = new NotificationMessage();

        for (int attempt = 0; attempt < 10; attempt++) {
            notification.clickHere();
            if (messages.contains("Action successful")) {
                return;
            }
        }
        fail("Action unsuccesful");
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
