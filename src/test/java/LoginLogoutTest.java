import api.User;
import api.UserClient;
import api.UserGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.*;
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
public class LoginLogoutTest {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String accessToken;
    private String email;
    private String password;
    private final Browser browser;

    public LoginLogoutTest(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{index} : Browser = {0}")
    public static Object[][] getData() {
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginThroughMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        PersonalCabPage personalCabPage = new PersonalCabPage(driver);
        Assert.assertTrue(personalCabPage.isProfileDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginThroughPersonalCabButton() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        header.clickPesonalCabButton();
        PersonalCabPage personalCabPage = new PersonalCabPage(driver);
        Assert.assertTrue(personalCabPage.isProfileDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginThroughRegistrationFormButton() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();
        loginPage.fillLoginForm(email, password);
        header.clickPesonalCabButton();
        PersonalCabPage personalCabPage = new PersonalCabPage(driver);
        Assert.assertTrue(personalCabPage.isProfileDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginThroughRecoveryPasswordForm() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoveryPassButton();
        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.clickLoginButton();
        loginPage.fillLoginForm(email, password);
        header.clickPesonalCabButton();
        PersonalCabPage personalCabPage = new PersonalCabPage(driver);
        Assert.assertTrue(personalCabPage.isProfileDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке в личном кабинете")
    public void logoutFromPesonalCab() {
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(email, password);
        header.clickPesonalCabButton();
        PersonalCabPage personalCabPage = new PersonalCabPage(driver);
        personalCabPage.clickLogoutButton();
        Assert.assertTrue(loginPage.isHeaderDisplayed());
    }

    @After
    public void cleanUp() {
        driver.quit();
        userClient.delete(accessToken);
    }
}
