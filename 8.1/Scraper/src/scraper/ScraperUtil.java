/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author fja2
 */
public class ScraperUtil {

    WebDriver driver;

    protected void getWebpage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fja2\\Downloads\\selenium-java-3.0.0-beta3\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://railroadbackdrops.com/category/all");

    }

    protected List<ScraperModel> scrapePage() {
        ScraperModel sMod = new ScraperModel();
        List<ScraperModel> scrapeList = new ArrayList();
        List<WebElement> products;
        int count = 0;
        String iUrl = "";

        products = driver.findElements(By.className("thumbt"));

        for (WebElement product : products) {
            sMod.setProductName(product.getAttribute("alt"));
            sMod.setDescription(product.getAttribute("title"));
            iUrl = product.getAttribute("src");
            //iUrl = iUrl.replaceAll("%20", " ");
            sMod.setImgUrl(iUrl);
            System.out.println(count + sMod.getImgUrl());
            scrapeList.add(sMod);
            count++;
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
            
            System.out.println("URLLLLLL "+ url );
            FileUtils.copyURLToFile(url,savedFile,60000,60000);
        }

    }

    protected void closeDriver() {
        driver.close();
    }

}
