/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scraper;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author fja2
 */
public class WalthersScraper {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        WalthersScraperUtil scrape = new WalthersScraperUtil();
       
        //Go to Webpage
        scrape.getWebpage("https://www.walthers.com/catalogsearch/result/?q=peco+track");
        
        //Scrape All Products
       // List<ScraperModel> scrapeList = scrape.scrapePage();
        
        //Save Images
        //scrape.saveImages(scrapeList);
        
        //Print Tab Del to Screen
        
        //Close Driver
        //scrape.closeDriver();
    }

}
