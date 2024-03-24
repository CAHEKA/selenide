package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.fail;

public class DisappearingElements {

    private SelenideElement elements = $x("//div[@class='example']/ul");

    public DisappearingElements() {
        Selenide.open("https://the-internet.herokuapp.com/disappearing_elements");
        elements.shouldBe(visible, Duration.ofSeconds(30));
    }

    public void waitForElementsAfterAttempts(int expectedElements, int attempts) {
        for (int i = 0; i < attempts; i++) {
            if (elements.findAll(By.tagName("li")).size() == expectedElements) {
                return;
            }
            Selenide.refresh();
        }
        fail("Failed to display " + expectedElements + " elements after " + attempts + " attempts");
    }
    
}
