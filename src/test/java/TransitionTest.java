import api.User;
import api.UserClient;
import api.UserGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Header;
import model.LoginPage;
import model.MainPage;
import model.PersonalCabPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Parameterized.class)
public class TransitionTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String email;
    private String password;
    private String accessToken;
    private final Browser browser;

    public TransitionTest(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{index} : Browser = {0}")
    public static Object[][] getBrowser() {
        return new Object[][]{
                {Browser.Chrome},
                {Browser.Yandex}
        };
    }

    @Before
    public void setUp() {
        switch (browser) {
            case Chrome:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case Yandex:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                driver = new ChromeDriver(options);
                break;
        }
        driver.get("https://stellarburgers.nomoreparties.site/");
        user = UserGenerator.getUserData();
        userClient = new UserClient();
        email = user.getEmail();
        password = user.getPassword();
        ValidatableResponse userCreate = userClient.create(user);
        accessToken = userCreate.extract().path("accessToken");
    }

    @Test
    @DisplayName("Переход на страницу личного кабинета")
    public void personalCabTransitionWithAuth() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        header.clickPesonalCabButton();
        PersonalCabPage personalCabPage = new PersonalCabPage(driver);
        Assert.assertTrue(personalCabPage.isProfileDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета на страницу конструктора")
    public void constructorTransitionFromPersonalCab() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        header.clickConstructorButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals(mainPage.constructorButtonActive(), mainPage.constructorMenuDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу по клику на лого")
    public void transitionFromPersonalCabToMainPage() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        header.clickLogo();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.mainPageDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
        userClient.delete(accessToken);
    }
}
