package widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementsCollectionWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Hovers {

    private final SelenideElement container = $x("//div[@class='example']");
    private final By figure = By.xpath(".//div[@class='figure']");
    private final By text = By.xpath(".//h5[text()]");
    private ElementsCollection lastCollection;

    public Hovers() {
        Selenide.open("https://the-internet.herokuapp.com/hovers");
        container.should(visible, Duration.ofSeconds(30));
    }

    public Hovers getAllFigure() {
        lastCollection = container.findAll(figure);
        return this;
    }

    public void highlightFigure() {
        if (lastCollection != null) {
            for (SelenideElement element : lastCollection) {
                element.hover();
                System.out.println("Figure text :" + element.find(text).getText());
            }
            lastCollection = null;
        }
    }
}