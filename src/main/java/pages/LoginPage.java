package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    // Обычный поиск элемента
    private WebElement user = driver.findElement(By.id("username"));

    // Поиск элемента через аннотацию
    //@FindBy(id = "password")
   // private WebElement password;
    private WebElement password = driver.findElement(By.id("password"));

    // todo: остальные элементы страницы

    public void login(String user, String password) {
        // todo
        this.user.sendKeys(user);
        this.password.sendKeys(password);
        driver.findElement(By.xpath("//input[@class='btn btn-lg btn-primary btn-block']")).click();
    }
}
