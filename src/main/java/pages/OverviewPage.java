package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OverviewPage extends AbstractPage{
    WebElement ticketTitle = driver.findElement(By.xpath("//div[@class='tickettitle']"));

    public WebElement getTicketTitle() {
        return ticketTitle;
    }
}
