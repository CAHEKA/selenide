package widgets;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class FlashMessages {
    private final SelenideElement container = $x("//div[@id='flash-messages']");
    private final By text = By.xpath(".//div[text()]");
    private final By close = By.xpath(".//a[@class='close']");


    public String getMessages() {
        container.isDisplayed();
        return container.find(text).getText();
    }

    public FlashMessages close() {
        container.find(close).click();
        return this;
    }

}
