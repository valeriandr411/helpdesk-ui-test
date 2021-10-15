
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import tickets.Ticket;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;
    private MainPage mainPage;
    private String newTitle;
    private Ticket expectedTicket;
    private Ticket foundTicket;
    private LoginPage loginPage;
    private MainPageAfterAuth mainPageAfterAuth;
    private TicketsPage ticketsPage;

    @Before
    public void setup() throws IOException {
        // Читаем конфигурационный файл в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void createTicketTest() throws IOException {
        // Читаем конфигурационный файл в System.properties
        driver.get(System.getProperty("site.url"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        openMainPage();
        openTicketsPage();
        createNewTicket();
        pushSubmitTicket();
        openLoginPage();
        enterLoginAndPassword();
        pushLogin();
        enterInSearch();
        pushSubmit();
        openFoundTicket();
        //Закрываем текущее окно браузера
        driver.close();
        Assert.assertEquals(expectedTicket, foundTicket);

    }

    @Step("1 - открыть главную страницу")
    public void openMainPage(){
        mainPage = new MainPage();
        saveAllureScreenshot();
    }
    @Step("2 - открыть страницу Create Ticket")
    public void openTicketsPage(){
        //Кликаем на кнопку new Ticket
        mainPage.clickByNewTicket();
        saveAllureScreenshot();
    }
    @Step("3 - заполнить поля тикета")
    public void createNewTicket(){
        //Страница тикета для создания
        ticketsPage = new TicketsPage();
        //Заполняем поля нового тикета
        ticketsPage.setQueue("Django Helpdesk");
        //Гарантирует уникальность заголовка
        newTitle = UUID.randomUUID().toString();
        ticketsPage.setTitle(newTitle);
        ticketsPage.setPriority("2. High");
        ticketsPage.setSubmitterEmail("koko@home.wew");
        saveAllureScreenshot();
    }
    @Step("4 - нажать на кнопку Submit Ticket ")
    public void pushSubmitTicket(){
        //Кликаем на кнопку для создания тикета
        ticketsPage.submitTicket();
        saveAllureScreenshot();
    }

    @Step("5 - открыть страницу авторизации")
    public void openLoginPage(){
        //Карточка тикета до авторизации
        DHBeforeAuthPage pageDHBefore = new DHBeforeAuthPage();
        //Ожидаемый тикет
        expectedTicket = new Ticket();
        //Задаем ожидаемые значения для созданного тикета
        expectedTicket.setTitle(pageDHBefore.getTitleText());
        expectedTicket.setSubmitterEmail(pageDHBefore.getEmail().getText());
        expectedTicket.setPriority(pageDHBefore.getPriority().getText());
        expectedTicket.setQueue(pageDHBefore.getQueueText());
        //Кликаем на кнопку login
        mainPage.clickBylogin();
        saveAllureScreenshot();
    }

    @Step("6 - ввести логин и пароль")
    public void enterLoginAndPassword(){
        //Страница для авторизации
        loginPage = new LoginPage();
        //Чтение данных учетной записи пользователя из user.properties
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        saveAllureScreenshot();
    }

    @Step("7 - нажать на кнопку login")
    public void pushLogin(){
        loginPage.getLoginButton().click();
        //Главная страница после авторизации
        saveAllureScreenshot();
    }

    @Step("8 - ввести в поле поиска название созданного тикет")
    public void enterInSearch(){
        mainPageAfterAuth = new MainPageAfterAuth();
        //Ввести в поисковое поле название тикета
        mainPageAfterAuth.setSearchQuery(newTitle);
        saveAllureScreenshot();
    }

    @Step("9 - нажать на кнопку поиска")
    public void pushSubmit(){
        //Кликаем на кнопку авторизации
        mainPageAfterAuth.clickBySubmit();
        saveAllureScreenshot();
    }

    @Step("10 - нажать на найденный тикет")
    public void openFoundTicket(){
        OverviewPage overviewPage = new OverviewPage();
        //Кликаем по найденному тикету
        overviewPage.getTicketTitle().click();

        //Карточка тикета после авторизации
        DHAfterAuthPage pageDHAfterAuth = new DHAfterAuthPage();
        //Найденный тикет
        foundTicket = new Ticket();
        //Заполняем найденный тикет
        foundTicket.setTitle(pageDHAfterAuth.getTitleText());
        foundTicket.setPriority(pageDHAfterAuth.getPriority().getText());
        foundTicket.setSubmitterEmail(pageDHAfterAuth.getEmail().getText());
        foundTicket.setQueue(pageDHAfterAuth.getQueueText());
        saveAllureScreenshot();
    }

}
