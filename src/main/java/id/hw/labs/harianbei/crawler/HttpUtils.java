/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hw.labs.harianbei.crawler;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class HttpUtils {

    public static void download(String fromUrl, String toFile) throws Exception {
        System.out.println("Download : " + fromUrl);
        URL website = new URL(fromUrl);
        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream()); FileOutputStream fos = new FileOutputStream(toFile)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }

    }
}