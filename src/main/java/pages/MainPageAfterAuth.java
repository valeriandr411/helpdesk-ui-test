package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPageAfterAuth extends MainPage{
    private WebElement searchQuery = driver.findElement(By.id("search_query"));
    private WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));

    public void setSearchQuery(String searchQuery) {
        this.searchQuery.sendKeys(searchQuery);
    }

    public String getSearchQueryText() {
        return searchQuery.getText();
    }

    public  void clickBySubmit(){submit.click();}
}
