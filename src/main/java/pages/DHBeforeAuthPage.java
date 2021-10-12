package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Карточка тикета до авторизации
public class DHBeforeAuthPage extends AbstractDHPage {
    public DHBeforeAuthPage(){
        super.setQueue(driver.findElement(By.xpath("//thead/tr[th[@colspan='2']]/th")));
        super.setTitle(driver.findElement(By.cssSelector("caption")));
        super.setPriority(driver.findElement(By.xpath("//tr[th='Priority']//td")));
        super.setEmail(driver.findElement(By.xpath("//tr[th='Submitter E-Mail']//td")));
    }

    public String getTitleText() {
        //Регулярное выражение для поиска заголовка
        String regEx ="^\\[DH-(\\d+)\\] (\\..+) \\[[A-Za-z]+\\]$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(super.getTitle().getText());
        //Формируем заголовок, убирая лишние атрибуты
        if(matcher.find()){
            return matcher.group(1)+matcher.group(2);
        }
        return null;
    }

    public WebElement getTitle() {
        return super.getTitle();
    }

    public WebElement getQueue() {
        return super.getQueue();
    }

    public WebElement getPriority() {
        return super.getPriority();
    }

    public WebElement getEmail() {
        return super.getEmail();
    }
}
