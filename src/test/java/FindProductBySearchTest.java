import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindProductBySearchTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.driver.chrome", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://homebrandofficial.ru/wear");
    }

    @Test
    public void checkingFindProductBySearchBar() {
        String productName = "ФУТБОЛКА ПОЛО ЧЕРНАЯ (М)";
        String expectedPrice = "2 800";
        String expectedAmountOnThePage = "Найдено: 1";
        driver.findElement(By.xpath("//input[@class='t-store__filter__input js-store-filter-search']")).sendKeys(productName, Keys.RETURN);
//        driver.findElement(By.cssSelector("input.t-store__filter__input.js-store-filter-search")).sendKeys(productName, Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By footerLoad = By.xpath("//div[@id='t-footer']");
//        By footerLoad = By.cssSelector("div.t-footer");
        By element = By.xpath("//div[@class='js-store-prod-name js-product-name t-store__card__title t-name t-name_xs']");
//        By element = By.cssSelector("div.js-store-prod-name.js-product-name.t-store__card__title.t-name.t-name_xs");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(footerLoad));

        String actualProductName = driver.findElement(element).getText();
        String actualPrice = driver.findElement(By.xpath("//div[@class='js-product-price js-store-prod-price-val t-store__card__price-value notranslate']")).getText();
//        String actualPrice = driver.findElement(By.cssSelector("div.js-product-price.js-store-prod-price-val.t-store__card__price-value.notranslate")).getText();
        String actualAmountOnThePage = driver.findElement(By.xpath("//div[@class='t-store__filter__prods-number js-store-filters-prodsnumber-wrap t-descr t-descr_xxs']")).getText();
//        String actualAmountOnThePage = driver.findElement(By.cssSelector("div.t-store__filter__prods-number.js-store-filters-prodsnumber-wrap.t-descr.t-descr_xxs")).getText();

        Assert.assertEquals(productName, actualProductName);
        Assert.assertEquals(expectedPrice, actualPrice);
        Assert.assertEquals(expectedAmountOnThePage, actualAmountOnThePage);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
