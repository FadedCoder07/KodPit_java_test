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

public class TestDil{
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
    public void testDil() throws IOException {
        driver.get(Constants.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdownLanguageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='dropdownLanguage' and contains(@class, 'dropdown-menu-toggle')]")));
        dropdownLanguageButton.click();

        WebElement englishOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'English')]")));
        englishOption.click();

        WebElement languageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='dropdownLanguage']")));
        String actualText = languageText.getText();
        String expectedText = "English";

        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testDil");
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

