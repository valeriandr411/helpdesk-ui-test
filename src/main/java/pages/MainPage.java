package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends AbstractPage {
    private WebElement login =  driver.findElement(By.id("userDropdown"));
    private WebElement newTicket =  driver.findElement(By.xpath("//a[@href='/tickets/submit/']"));

    public void clickBylogin(){
        driver.navigate().to("https://at-sandbox.workbench.lanit.ru/login/?next=/");
    }
    public void clickByNewTicket(){
        driver.navigate().to("https://at-sandbox.workbench.lanit.ru/tickets/submit/");
    }
}
