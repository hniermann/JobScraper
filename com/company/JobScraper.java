package com.company;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class JobScraper {

    public static void main(String[] args) {
//
            System.setProperty("webdriver.chrome.driver","C:\\Users\\henry\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.indeed.com");

            String title = driver.getTitle();
            System.out.println(title);

            WebElement searchBox = driver.findElement(By.id("text-input-what"));
            searchBox.sendKeys("Software Engineering Intern");
            WebElement searchClick = driver.findElement(By.tagName("button"));
            searchClick.click();

            String url = driver.getCurrentUrl();

            try{
                Document doc = Jsoup.connect(url).get();
                System.out.println("title: "+doc.title());

            Elements links = doc.select("a[target]");
            for(Element link : links){

                String linkHref = link.attr("href");
                String checker = linkHref.substring(0,2);
                if(checker.equals("/r") || checker.equals("/p")) {
                    System.out.println(link.text());
                    System.out.println("https://indeed.com" + linkHref);
                }
            }

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
