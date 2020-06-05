/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hw.labs.harianbei.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {

    public static List<DownloadFile> getDownloadList(String lastFileName) throws IOException {
        List<DownloadFile> result = new ArrayList<>();
        String url = "http://www.dataharianbei.com/";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href*=/download.php]");
        print("Populated Links: (%d)", links.size());
        for (int i=links.size() - 1; i >= 0; i--) {
            Element link = links.get(i);
            if (link.text().trim().compareTo(lastFileName) > 0) {
                result.add(new DownloadFile(link.attr("href"), trim(link.text(), 35)));
            } else {
                break;
            }
        }
        if (result.size() > 0)
            print("Filtered Links: (%d)", result.size());
        else
            print("DATA BEI EOD is uptodate");
        return result;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
}
