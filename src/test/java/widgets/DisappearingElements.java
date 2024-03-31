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

public class DisappearingElements {

    private final SelenideElement elements = $x("//div[@class='example']/ul");

    public DisappearingElements() {
        Selenide.open("https://the-internet.herokuapp.com/disappearing_elements");
        elements.shouldBe(visible, Duration.ofSeconds(30));
    }

    @Step("Check number of elements: {numberElements}")
    public void checkNumberOfElements(int numberElements) {
        Assertions.assertEquals(elements.findAll(By.tagName("li")).size(), numberElements);
    }
    
}
