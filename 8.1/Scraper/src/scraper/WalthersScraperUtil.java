/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author fja2
 */
public class WalthersScraperUtil {

    WebDriver driver;

    protected void getWebpage(String url) throws InterruptedException {
        System.out.println("0");
        //System.setProperty("webdriver.chrome.driver", "/Users/Whip/data/jars/chromedriver");
        System.out.println("11111");
        WebDriver driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        System.out.println("Going to: " + url);
        driver.get(url);

    }

    protected List<WalthersModel> scrapePage() {
        WalthersModel sMod = new WalthersModel();
        List<WalthersModel> scrapeList = new ArrayList();
        List<WebElement> products;
        WebElement pictureClass,
                productClass;
        int count = 0;
        String iUrl = "";

        while (driver.findElements( By.className("next i-next") ).size() != 0) {
            products = driver.findElements(By.className("item-inner"));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement product : products) {
                driver.findElement(By.className(product.getAttribute("item-inner"))).click();

                //Get picture class   
                pictureClass = driver.findElement(By.className("zoom-img"));

                sMod.setImgUrl(pictureClass.getAttribute("src"));

//            
//            sMod.setProductName(parentClass.getAttribute(iUrl));
//            sMod.setProductName(iUrl);
//            sMod.setProductName(parentClass.getAttribute("title"));
//            iUrl = product.getAttribute("src");
//            //iUrl = iUrl.replaceAll("%20", " ");
//            sMod.setImgUrl(iUrl);
                //pring URL and Count
                System.out.println(count + sMod.getImgUrl());
                scrapeList.add(sMod);
                count++;

                //Go back 1 page
                js.executeScript("window.history.go(-1)");
            }
        }
        return scrapeList;

    }

    protected void saveImages(List<ScraperModel> scrapeList) throws MalformedURLException, IOException {
        String saveName;
        for (ScraperModel sMod : scrapeList) {
            URL url1 = new URL(sMod.getImgUrl());
            URL url = new URL("http://railroadbackdrops.com/images/PIXSKY/Blue%20Sky%2004%20No%20Clouds%20Continuous.png");

            saveName = new File(sMod.getImgUrl()).getName();
            File savedFile = new File("C:\\Users\\fja2\\Desktop\\James\\" + saveName);

            System.out.println("URLLLLLL " + url);
            FileUtils.copyURLToFile(url, savedFile, 60000, 60000);
        }

    }

    protected void closeDriver() {
        driver.close();
    }

}
