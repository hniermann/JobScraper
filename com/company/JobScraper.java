package com.company;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobScraper {

    public static void main(String[] args) {
        try {
            // fetch the document over HTTP
            Document doc = Jsoup.connect("https://www.indeed.com/q-Software-Engineer-jobs.html").get();

            // get the page title
            String title = doc.title();
            System.out.println("title: " + title);


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
            e.printStackTrace();
        }
    }
}
