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
        driver.findElement(By.xpath("//*[@id=\"rec349377690\"]/div[1]/div/div[1]/div/div[1]/div[4]/div[1]/div/input")).sendKeys(productName, Keys.RETURN);
//        driver.findElement(By.cssSelector(".js-store-parts-select-container.t-store__grid-cont.t-store__grid-cont_col-width_stretch.t-container > div > div.t-store__filter__controls-wrapper > div.t-store__filter__search-and-sort > div.t-store__filter__search.t-descr.t-descr_xxs > div > input")).sendKeys(productName, Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By xpath = By.xpath("//*[@id=\"rec349377690\"]/div[1]/div/div[1]/div/div[2]/div[1]/div[1]");
        By element = By.xpath("//*[@id=\"rec349377690\"]/div[1]/div/div[3]/div/a/div[2]/div[1]");
//        By element = By.cssSelector("#rec349377690 > div.t786 > div > div.js-store-grid-cont.t-store__grid-cont.t-store__grid-cont_col-width_stretch.t-container.t-store__mobile-two-columns.t-store__grid-cont_mobile-grid > div > a > div.t-store__card__textwrapper > div.js-store-prod-name.js-product-name.t-store__card__title.t-name.t-name_xs");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));

        String actualProductName = driver.findElement(element).getText();
        String actualPrice = driver.findElement(By.xpath("//*[@id=\"rec349377690\"]/div[1]/div/div[3]/div/a/div[2]/div[3]/div[1]/div[1]")).getText();
//        String actualPrice = driver.findElement(By.cssSelector("#rec349377690 > div.t786 > div > div.js-store-grid-cont.t-store__grid-cont.t-store__grid-cont_col-width_stretch.t-container.t-store__mobile-two-columns.t-store__grid-cont_mobile-grid > div > a > div.t-store__card__textwrapper > div.js-store-price-wrapper.t-store__card__price-wrapper > div.t-store__card__price.t-store__card__price-item.t-name.t-name_xs > div.js-product-price.js-store-prod-price-val.t-store__card__price-value.notranslate")).getText();
        String actualAmountOnThePage = driver.findElement(By.xpath("//*[@id=\"rec349377690\"]/div[1]/div/div[1]/div/div[2]/div[2]")).getText();
//        String actualAmountOnThePage = driver.findElement(By.cssSelector("#rec349377690 > div.t786 > div > div.js-store-parts-select-container.t-store__grid-cont.t-store__grid-cont_col-width_stretch.t-container > div > div.t-store__filter__chosen-bar > div.t-store__filter__prods-number.js-store-filters-prodsnumber-wrap.t-descr.t-descr_xxs")).getText();

        Assert.assertEquals(productName, actualProductName);
        Assert.assertEquals(expectedPrice, actualPrice);
        Assert.assertEquals(expectedAmountOnThePage, actualAmountOnThePage);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
