package widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.url;

public class StatusCodes {

    private final SelenideElement container = $x("//div[@class='example']");
    private final By text = By.xpath("./p");

    public StatusCodes() {
        Selenide.open("https://the-internet.herokuapp.com/status_codes");
        container.should(visible, Duration.ofSeconds(30));
    }

    @Step("Click button {statusCodes}")
    public StatusCodes clickOnStatusCode(String statusCodes) {
        String xpath = String.format(".//a[text()= '%s']", statusCodes);
        container.find(By.xpath(xpath)).click();
        return this;
    }

    @Step("Checking URL contains {code}")
    public StatusCodes checkContainsUrl(String code) {
        container.should(visible, Duration.ofSeconds(30));
        Assert.isTrue(url().contains(code), 
                "The current URL does not contain the expected status code: " + code);
        System.out.println("Text after going to page: " + container.find(text).getText());
        return this;
    }
    
}