package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.fail;

public class NotificationMessage {

    private final SelenideElement container = $x("//div[@class='example']");
    private final By href = By.xpath(".//a[@href]");


    public NotificationMessage() {
        Selenide.open("https://the-internet.herokuapp.com/notification_message_rendered");
        container.should(visible, Duration.ofSeconds(30));
    }

    @Step("Click here")
    public NotificationMessage clickHere() {
        container.find(href).click();
        return this;
    }

    @Step("Check for message: {expectMassage}")
    public NotificationMessage checkForMessage(String expectMassage) {
        String actualMessage = new FlashMessages().getMessages();
        Assertions.assertTrue(actualMessage.contains(expectMassage), "Message should match");
        return this;
    }

}
