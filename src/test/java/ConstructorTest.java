import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.MainPage;
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
public class ConstructorTest {
    private WebDriver driver;
    private Browser browser;
    public ConstructorTest(Browser browser){
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
    public static Object[][] getData(){
        return new Object[][]{
                {Browser.Chrome},
                {Browser.Yandex}
        };
    }
    @Test
    @DisplayName("Переход к разделу Булки")
    public void constructorTransitionBunList(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        Assert.assertTrue(mainPage.bunButtonActive());
    }
    @Test
    @DisplayName("Переход к разделу Соусы")
    public void constructorTransitionSauceList(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        Assert.assertTrue(mainPage.sauceButtonActive());
    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void constructorTransitionFillingList(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        Assert.assertTrue(mainPage.fillingButtonActive());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
