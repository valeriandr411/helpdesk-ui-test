import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import tickets.Ticket;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;

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

    @Test
    public void createTicketTest() throws IOException {
        // Читаем конфигурационный файл в System.properties
        driver.get(System.getProperty("site.url"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        MainPage mainPage = new MainPage();
        //Кликаем на кнопку new Ticket
        mainPage.clickByNewTicket();
        //Страница тикета для создания
        TicketsPage ticketsPage = new TicketsPage();
        //Заполняем поля нового тикета
        ticketsPage.setQueue("Django Helpdesk");
        //Гарантирует уникальность заголовка
        String newTitle = UUID.randomUUID().toString();
        ticketsPage.setTitle(newTitle);
        ticketsPage.setPriority("2. High");
        ticketsPage.setSubmitterEmail("koko@home.wew");
        //Кликаем на кнопку для создания тикета
        ticketsPage.submitTicket();

        //Карточка тикета до авторизации
        DHBeforeAuthPage pageDHBefore = new DHBeforeAuthPage();

        //Ожидаемый тикет
        Ticket expectedTicket = new Ticket();
        //Задаем ожидаемые значения для созданного тикета
        expectedTicket.setTitle(pageDHBefore.getTitleText());
        expectedTicket.setSubmitterEmail(pageDHBefore.getEmail().getText());
        expectedTicket.setPriority(pageDHBefore.getPriority().getText());
        expectedTicket.setQueue(pageDHBefore.getQueueText());

        //Кликаем на кнопку login
        mainPage.clickBylogin();
        //Страница для авторизации
        LoginPage loginPage = new LoginPage();
        //Чтение данных учетной записи пользователя из user.properties
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        //Главная страница после авторизации
        MainPageAfterAuth mainPageAfterAuth = new MainPageAfterAuth();
        //Ищем созданный тикет
        mainPageAfterAuth.setSearchQuery(newTitle);
        mainPageAfterAuth.clickBySubmit();

        OverviewPage overviewPage = new OverviewPage();
        //Кликаем по найденному тикету
        overviewPage.getTicketTitle().click();

        //Карточка тикета после авторизации
        DHAfterAuthPage pageDHAfterAuth = new DHAfterAuthPage();
        //Найденный тикет
        Ticket searchedTicket = new Ticket();
        //Заполняем найденный тикет
        searchedTicket.setTitle(pageDHAfterAuth.getTitleText());
        searchedTicket.setPriority(pageDHAfterAuth.getPriority().getText());
        searchedTicket.setSubmitterEmail(pageDHAfterAuth.getEmail().getText());
        searchedTicket.setQueue(pageDHAfterAuth.getQueueText());

        //Закрываем текущее окно браузера
        driver.close();
        Assert.assertEquals(expectedTicket, searchedTicket);


    }
}
