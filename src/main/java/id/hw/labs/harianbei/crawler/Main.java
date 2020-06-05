/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hw.labs.harianbei.crawler;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HWAHYUDI
 */
public class Main {
    
   public static void main(String[] args) {
    List<DownloadFile> listUrl;
    Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String currentDir = path.toString().substring(0, path.toString().length() - 1);
    System.out.println("Current relative path is: " + currentDir);
        
    try {
        Path lastFileDownlaoded = ListFiles.getLastDownloadedFile(currentDir);
        String filterStr = lastFileDownlaoded != null ? lastFileDownlaoded.getFileName().toString() : "MS000000.txt";
        listUrl = ListLinks.getDownloadList(filterStr);
        listUrl.forEach(System.out::println);
     
        if (!listUrl.isEmpty()) {
            ExecutorService pool = Executors.newFixedThreadPool(10);
            listUrl.forEach((dFile) -> {
                pool.submit(new DownloadTask(dFile.getUrl(), currentDir + dFile.getFileName()));
            });
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            
            System.out.println("Download succeeded");
            System.out.println(listUrl.size() + " file(s) saved in directory: " + currentDir);
        }
    } catch (IOException | InterruptedException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    }
}
