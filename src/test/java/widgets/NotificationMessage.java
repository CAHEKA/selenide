package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.fail;

public class NotificationMessage {

    private final SelenideElement container = $x("//div[@class='example']");
    private final By href = By.xpath(".//a[@href]");
    private Runnable lastMethod;

    public NotificationMessage() {
        Selenide.open("https://the-internet.herokuapp.com/notification_message_rendered");
        container.should(visible, Duration.ofSeconds(30));
    }

    public NotificationMessage clickHere() {
        container.find(href).click();
        lastMethod = this::clickHere;
        return this;
    }

    public NotificationMessage waitForMessageAfterAttempts(String massage, Integer attempts) {
        FlashMessages messages = new FlashMessages();
        for (int i = 0; i < attempts; i++) {
            if (lastMethod != null) {
                lastMethod.run();
            }
            if (messages.contains(massage)) {
                return this;
            }
            messages.close();
        }
        fail("Action unsuccesful");
        return this;
    }

}
