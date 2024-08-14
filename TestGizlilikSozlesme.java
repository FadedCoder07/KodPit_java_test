package com.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;

public class TestGizlilikSozlesme{
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

        driver = new ChromeDriver(); 
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        testUtils = new TestUtils(driver, document, pdfFile);
    }

    @Test
    public void testGizlilikSozlesme() throws IOException {
        driver.get(Constants.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement GizliliksözlesmeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Sözleşmeler']")));
        GizliliksözlesmeLink.click();

        WebElement GizlilikSozlesmesiLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Gizlilik ve Güvenlik']")));
        GizlilikSozlesmesiLink.click();

        WebElement GizliliksözlesmeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = GizliliksözlesmeElement.getText();

        String expectedText = "Gizlilik ve Güvenlik";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testGizlilikSözlesme");
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
