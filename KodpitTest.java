package com.qa;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class KodpitTest extends MyDriver {
    private TestUtils testUtils;
    private PDDocument document;
    private File pdfFile;

    @BeforeMethod
    @Override
    public void setUp() throws IOException {
        super.setUp();
        pdfFile = new File(Constants.SCREENSHOTS_DIR + "TestResults.pdf");
        if (pdfFile.exists()) {
            document = PDDocument.load(pdfFile);
        } else {
            document = new PDDocument();
        }
        testUtils = new TestUtils(driver, document,pdfFile);
    }

    @Test
    public void testKurumsal() throws IOException {
        driver.get(Constants.BASE_URL);

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement kurumsalLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Kurumsal']")));
        kurumsalLink.click();

        WebElement ekibimizElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = ekibimizElement.getText();

        String expectedText = "Ekibimiz";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testKurumsal");
    }

    @Test
    public void testBasin() throws InterruptedException, IOException {
        driver.get(Constants.BASE_URL);
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement basinLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Basında Kodpit']")));
        basinLink.click();

        Thread.sleep(5000);
        WebElement basinElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = basinElement.getText();

        String expectedText = "Basında Kodpit";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testBasin");
    }

    @Test
    public void testInsanKaynaklari() throws InterruptedException, IOException {
        driver.get(Constants.BASE_URL);
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement kaynakLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='İnsan Kaynakları']")));
        kaynakLink.click();

        WebElement kaynakElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = kaynakElement.getText();

        String expectedText = "İnsan Kaynakları";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testInsanKaynaklari");
    }

    @Test
    public void testStaj() throws InterruptedException, IOException {
        driver.get(Constants.BASE_URL);
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement stajLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Staj İmkanları']")));
        stajLink.click();

        WebElement stajElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = stajElement.getText();

        String expectedText = "Staj İmkanları";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testStaj");
    }

    @Test
    public void testTeknoloji() throws InterruptedException, IOException {
        driver.get(Constants.BASE_URL);
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement teknolojiLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Teknolojimiz']")));
        teknolojiLink.click();

        WebElement teknolojiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = teknolojiElement.getText();

        String expectedText = "Teknolojimiz";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testTeknoloji");
    }

    @Test
    public void testKullaniciSozlesme() throws InterruptedException, IOException {
        driver.get(Constants.BASE_URL);
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement sozlesmeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Sözleşmeler']")));
        sozlesmeLink.click();

        WebElement kullaniciSozlesmesiLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Kullanım Sözleşmesi']")));
        kullaniciSozlesmesiLink.click();

        WebElement kullaniciSozlesmesiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = kullaniciSozlesmesiElement.getText();

        String expectedText = "Kullanım Sözleşmesi";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testTeknoloji");
    }

    @Test
    public void testGizlilikSözlesme() throws InterruptedException, IOException 
    {
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

    @Test
    public void testIadeSözlesme() throws InterruptedException, IOException 
    {
        driver.get(Constants.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.dropdown > a.dropdown-toggle")));
        dropdown.click();
       
        WebElement IadesözlesmeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='Sözleşmeler']")));
        IadesözlesmeLink.click();

        WebElement IadeSozlesmesiLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'dropdown-menu')]//a[text()='İptal ve İade Koşulları']")));
        IadeSozlesmesiLink.click();

        WebElement IadesözlesmeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-md-12']/h1")));
        String actualText = IadesözlesmeElement.getText();

        String expectedText = "İptal ve İade Koşulları";
        Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

        testUtils.takeScreenshot("testIadeSözlesme");

    }

    @Test
    public void testDil() throws InterruptedException, IOException 
    {
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
    @Test
    public void testYoutube () throws InterruptedException, IOException
    {
        
        driver.get(Constants.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement youtubeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Youtube']")));
        youtubeButton.click();

        String originalHandle = driver.getWindowHandle(); 

        WebDriverWait newTabWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Set<String> handles = driver.getWindowHandles();
        String newTabHandle = null;

        for (String handle : handles) 
        {
            if (!handle.equals(originalHandle))
            {
                newTabHandle = handle;
                break;
            }
        }

        if (newTabHandle != null) {
            driver.switchTo().window(newTabHandle);  

        
            newTabWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Kodpit Teknoloji A.Ş.']")));

        
            WebElement YoutubeElement = driver.findElement(By.xpath("//span[text()='Kodpit Teknoloji A.Ş.']"));
            String actualText = YoutubeElement.getText();

            String expectedText = "Kodpit Teknoloji A.Ş";
            Assert.assertTrue(actualText.contains(expectedText), "Text does not match!");

            testUtils.takeScreenshot("testYoutube");
        }

    }
}

