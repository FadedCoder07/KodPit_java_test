package com.qa;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtils {
    private WebDriver driver;
    private PDDocument document;
    private File pdfFile;

    public TestUtils(WebDriver driver, PDDocument document, File pdfFile) {
        this.driver = driver;
        this.document = document;
        this.pdfFile = pdfFile;
    }

    public void takeScreenshot(String testName) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long pageHeightLong = (Long) js.executeScript("return document.body.scrollHeight");
        int pageHeight = pageHeightLong.intValue();
        driver.manage().window().setSize(new Dimension(1920, pageHeight));

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(Constants.SCREENSHOTS_DIR + File.separator + testName + ".png");
        org.openqa.selenium.io.FileHandler.copy(srcFile, destFile);

        PDPage page = new PDPage();
        document.addPage(page);

        BufferedImage img = ImageIO.read(destFile);
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        float pdfPageWidth = page.getMediaBox().getWidth();
        float pdfPageHeight = page.getMediaBox().getHeight();

        float scaleX = pdfPageWidth / imgWidth;
        float scaleY = pdfPageHeight / imgHeight;
        float scale = Math.min(scaleX, scaleY);

        PDImageXObject pdImage = PDImageXObject.createFromFile(destFile.getAbsolutePath(), document);
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.OVERWRITE, true)) {
            contentStream.drawImage(pdImage, 0, 0, imgWidth * scale, imgHeight * scale);
        }

        
        document.save(pdfFile);
    }
}