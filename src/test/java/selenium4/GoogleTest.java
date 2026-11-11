
package selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @Test
    public void testTitle() {
        Assert.assertTrue(driver.getTitle().contains("Google"));
        System.out.println("TC1: Title Test Passed");
    }

    @Test
    public void testURL() {
        Assert.assertTrue(driver.getCurrentUrl().contains("google.com"));
        System.out.println("TC2: URL Test Passed");
    }

    @Test
    public void testSearchBox() {
        Assert.assertTrue(
            driver.findElement(By.name("q")).isDisplayed()
        );
        System.out.println("TC3: Search Box Test Passed");
    }

    @Test
    public void testSearch() {
        driver.findElement(By.name("q"))
              .sendKeys("Selenium WebDriver");

        driver.findElement(By.name("q")).submit();

        // Wait until search results are loaded
        org.openqa.selenium.support.ui.WebDriverWait wait =
            new org.openqa.selenium.support.ui.WebDriverWait(
                driver, java.time.Duration.ofSeconds(10));

        wait.until(d -> d.getTitle().contains("Selenium"));

        Assert.assertTrue(driver.getTitle().contains("Selenium"));
        System.out.println("TC4: Search Test Passed");
    }

    @Test
    public void testPageOpened() {
        Assert.assertFalse(driver.getTitle().isEmpty());
        System.out.println("TC5: Page Open Test Passed");
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
