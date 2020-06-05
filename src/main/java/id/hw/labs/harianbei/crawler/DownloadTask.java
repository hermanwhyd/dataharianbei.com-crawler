/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hw.labs.harianbei.crawler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HWAHYUDI
 */
public class DownloadTask implements Runnable{
    private final String url;    
    private final String toPath;

    public DownloadTask(String url, String toPath) {
        this.url = url;
        this.toPath = toPath;
    }

    @Override
    public void run() {
        try {
            // surround with try-catch if downloadFile() throws something
            HttpUtils.download(url, toPath);
        } catch (Exception ex) {
            Logger.getLogger(DownloadTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
