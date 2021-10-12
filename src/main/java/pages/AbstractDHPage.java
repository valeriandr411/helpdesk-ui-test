package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDHPage extends AbstractPage{
    private WebElement title;
    private WebElement email;
    private WebElement priority;
    private WebElement queue;

    void setTitle(WebElement title) {
        this.title = title;
    }

   void setPriority(WebElement priority) {
        this.priority = priority;
    }

    void setEmail(WebElement email) {
        this.email = email;
    }

    void setQueue(WebElement queue) {
        this.queue = queue;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getPriority() {
        return priority;
    }

    public WebElement getQueue() {
        return queue;
    }
    public String getQueueText() {
        String regEx = "Queue: (.+)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(getQueue().getText());
        if(matcher.find()){ return matcher.group(1);}
        return null;
    }
}
