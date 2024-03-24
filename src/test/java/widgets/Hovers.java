package widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Hovers {

    private SelenideElement container = $x("//div[@class='example']");
    private By figure = By.xpath(".//div[@class='figure']");
    private By text = By.xpath(".//h5[text()]");
    
    public Hovers() {
        Selenide.open("https://the-internet.herokuapp.com/hovers");
        container.should(visible, Duration.ofSeconds(30));
    }
    
    public ElementsCollection getAllFigure() {
        return container.findAll(figure);
    }
    
    public void highlightFigure(SelenideElement figureElement){
        figureElement.hover();
        System.out.println("Figure text :" + figureElement.find(text).getText());
    }
}