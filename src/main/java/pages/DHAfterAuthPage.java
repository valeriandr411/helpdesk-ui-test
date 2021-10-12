package pages;

import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Карточка тикета после авторизации
public class DHAfterAuthPage extends AbstractDHPage {
  // private WebElement title = driver.findElement(By.cssSelector("h3"));
    public DHAfterAuthPage(){
        super.setTitle(driver.findElement(By.cssSelector("h3")));
        super.setPriority(driver.findElement(By.xpath("//tr[th[@class='table-active']]/td[@class]")));
        super.setEmail(driver.findElement(By.xpath("//tr[th='Submitter E-Mail']/td[2]")));
        super.setQueue(driver.findElement(By.xpath("//tr/th[@colspan='4']")));
    }

    public String getTitleText() {
        //Возвращает без "DH-" в начале строки и " [Open]" в конце строки
        String regEx = "^DH-(.+) \\[[A-Za-z]+\\]$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(super.getTitle().getText());

        if(matcher.find()){
            return matcher.group(1);
        }
        return super.getTitle().getText();
    }

}