/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hw.labs.harianbei.crawler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author HWAHYUDI
 */
public class ListFiles {

    public static Path getLastDownloadedFile(String directory) throws IOException {
            Stream<Path> walk = Files.walk(Paths.get(directory));
            Optional<Path> file = walk
                    .filter(f -> f.toString().endsWith(".txt"))
                    .sorted(Comparator.comparing(p -> p.toFile().getName(), Comparator.reverseOrder()))
                    .limit(1)
                    .findFirst();
            
            System.out.println("Most File Update:");
            if (file.isPresent()) System.out.println(file.get().getFileName());
            else System.out.println("-");
            
        return file.isPresent() ? file.get() : null;
    }
}
