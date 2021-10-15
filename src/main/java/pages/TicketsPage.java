package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TicketsPage extends AbstractPage {
    private Select queue = new Select(driver.findElement(By.id("id_queue")));
    private WebElement title = driver.findElement(By.id("id_title"));
    private Select priority = new Select(driver.findElement(By.id("id_priority")));
    private WebElement submitterEmail = driver.findElement(By.id("id_submitter_email"));
    private WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block']"));

    public void setPriority(String value) {
        this.priority.selectByVisibleText(value);
    }

    public void setQueue(String value) {
        queue.selectByVisibleText(value);
    }

    public void setSubmitterEmail(String submitterEmail) {
        this.submitterEmail.sendKeys(submitterEmail);
    }

    public void setTitle(String title) {
        this.title.sendKeys(title);
    }

    public Select getPriority() {
        return priority;
    }

    public Select getQueue() {
        return queue;
    }

    public WebElement getSubmitterEmail() {
        return submitterEmail;
    }

    public WebElement getTitle() {
        return title;
    }

    public void submitTicket() {
        submit.click();
    }
}
