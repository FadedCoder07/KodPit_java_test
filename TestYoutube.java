package com.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.apache.pdfbox.pdmodel.PDDocument;

public class TestYoutube {
    private WebDriver driver;
    private WebDriverWait wait;
    private static TestUtils testUtils;
    private static PDDocument document;
    private static File pdfFile;

    @BeforeClass
    public void setUpClass() throws IOException {
        pdfFile = new File(Constants.SCREENSHOTS_DIR + "TestResults.pdf");
        if (pdfFile.exists()) {
            document = PDDocument.load(pdfFile);
        } else {
            document = new PDDocument();
        }

        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");  
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        testUtils = new TestUtils(driver, document, pdfFile);
    }

    @Test
    public void testYoutube() throws IOException {
        driver.get(Constants.BASE_URL);

        WebElement youtubeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Youtube']")));
        youtubeButton.click();

        String originalHandle = driver.getWindowHandle(); 

        Set<String> handles = driver.getWindowHandles();
        String newTabHandle = null;

        for (String handle : handles) {
            if (!handle.equals(originalHandle)) {
                newTabHandle = handle;
                break;
            }
        }

        if (newTabHandle != null) {
            driver.switchTo().window(newTabHandle);

            wait.until(ExpectedConditions.titleContains("YouTube"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Kodpit Teknoloji A.Ş.']")));

            testUtils.takeScreenshot("testYoutube");
        } else {
            Assert.fail("YouTube tab was not found.");
        }
    }

    @AfterClass
    public void tearDownClass() throws IOException {
        if (document != null) {
            document.close();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}