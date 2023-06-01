import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.Header;
import model.LoginPage;
import model.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
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
public class RegistrationTest {
    private Faker faker = new Faker();
    private WebDriver driver;
    private final String name = faker.rickAndMorty().character();;
    private final String email = faker.name().firstName() + "@ya.ya";
    private final String password = RandomStringUtils.randomAlphabetic(6,12);
    private final String incorrectPassword = RandomStringUtils.randomAlphabetic(1,5);
    private Browser browser;
    public RegistrationTest(Browser browser){
        this.browser = browser;
    }
    @Before
    public void setUp(){
        switch (browser){
            case Chrome:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case Yandex:
                System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
                options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                driver = new ChromeDriver(options);
                break;
        }
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Parameterized.Parameters
    public static Object[][] getBrowser(){
        return new Object[][] {
                {Browser.Chrome},
                {Browser.Yandex}
        };
    }
    @Test
    @DisplayName("Регистрация пользователя")
    public void registrationWithCorrectData(){
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.scrollToRegistrationButton();
        loginPage.clickRegistrationButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitPageLoad();
        registerPage.fillRegistrationPage(name, email, password);
        registerPage.clickRegistrationButton();
        Assert.assertTrue(loginPage.isHeaderDisplayed());
    }
    @Test
    @DisplayName("Регистрация пользователя с некорректной длинной пароля")
    public void registrationWithIncorrectPassword(){
        Header header = new Header(driver);
        header.clickPesonalCabButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.scrollToRegistrationButton();
        loginPage.clickRegistrationButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitPageLoad();
        registerPage.fillRegistrationPage(name, email, incorrectPassword);
        registerPage.clickRegistrationButton();
        Assert.assertTrue(registerPage.isPasswordFieldhighlighted());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
