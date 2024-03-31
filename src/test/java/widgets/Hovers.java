package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Hovers {

    private final SelenideElement container = $x("//div[@class='example']");
    private final By figure = By.xpath(".//div[@class='figure']");
    private final By text = By.xpath(".//h5[text()]");
    SelenideElement element;

    public Hovers() {
        Selenide.open("https://the-internet.herokuapp.com/hovers");
        container.should(visible, Duration.ofSeconds(30));
    }

    @Step("Highlight figure: {index}")
    public Hovers highlightFigure(Integer index) {
        element = container.findAll(figure).get(index).hover();
        return this;
    }

    @Step("Check text selected figure: {expectText}")
    public Hovers checkTextSelectedFigure(String expectText) {
        String actualText = element.find(text).getText();
        System.out.println("Figure text :" + actualText);
        Assertions.assertEquals(expectText, actualText, "Text should match");
        return this;
    }

}